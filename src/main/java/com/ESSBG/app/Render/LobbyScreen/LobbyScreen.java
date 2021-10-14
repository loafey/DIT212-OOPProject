package com.ESSBG.app.Render.LobbyScreen;

import com.ESSBG.app.GameServer;
import com.ESSBG.app.Model.Game;
import com.ESSBG.app.Network.Client;
import com.ESSBG.app.Render.ScreenManager;
import com.ESSBG.app.Render.GameScene.GameScene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.json.JSONObject;

public class LobbyScreen implements Screen{
    private Stage stage;
    private Skin skin;
    private Table sceneTable;

    private TextField ipField;
    private Button joinButton;

    private Table lobbyTable;
    private Table playerTable;

    private Button startButton;
    private Button leaveButton;
    private Button hostButton;

    private Client client;


    @Override
    public void show() {
        Viewport vp = new ScreenViewport();
        stage = new Stage(vp);
        skin = new Skin(Gdx.files.internal("Assets/Skins/LobbyScreen/LobbySceneSkin.json"));
        
        sceneTable = new Table();
        sceneTable.top().left();
        sceneTable.setFillParent(true);
        sceneTable.setDebug(true);
        stage.addActor(sceneTable);

        Gdx.input.setInputProcessor(stage);
        
        Table connectContainer = new Table();
        connectContainer.pad(10);
        connectContainer.top().left();
        sceneTable.add(connectContainer).fill();

        Table connectTable = new Table();
        connectTable.top().left();
        connectTable.add(new Label("Join Server:",skin));
        connectTable.row();
        joinButton = new Button(skin);
        joinButton.add(new Label("Connect", skin));
        connectTable.add(joinButton).fillX();
        ipField = new TextField("127.0.0.1", skin);
        connectTable.add(ipField);
        hostButton = new Button(skin);
        hostButton.add("Host");
        connectTable.row();
        connectTable.add(hostButton).fill();
        
        connectContainer.add(connectTable).fill().expand();

        lobbyTable = new Table();
        lobbyTable.pad(10);
        lobbyTable.setDebug(true);
        lobbyTable.top().left();
        lobbyTable.add(new Label("Lobby info: ", skin)).fill();
        lobbyTable.row();

        playerTable = new Table();
        lobbyTable.add(playerTable).expandY().fill();
        playerTable.top().left();
        
        lobbyTable.row();
        
        startButton = new Button(skin);
        startButton.add(new Label("Start Game", skin));
        lobbyTable.add(startButton).fillX().expandX();

        leaveButton = new Button(skin);
        leaveButton.add(new Label("Leave lobby", skin));
        lobbyTable.add(leaveButton).fillX().expandX();

        sceneTable.add(lobbyTable).fill().expand();

        // TODO add host button
        hostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(new GameServer ()).start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                client = new Client();
                client.runClient();
            }
        });

        joinButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client = new Client();
                client.runClient(ipField.getText());
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                JSONObject data = new JSONObject("{\"start\": true}");
                data.put("msgNum", 0);
                client.sendData(data);
            }
        });
    }

    private void messageHandler(){
        if (client != null){
            if (client.getMsgQueue().size() > 0){
                try {
                    JSONObject message = client.getMsgQueue().take();
                    String reason = message.getString("reason");
                    if (reason.equals("net")){
                        if (message.getBoolean("data")){
                            System.out.println("Connected!");
                        } else {
                            System.out.println("Disconnected!");
                        }
                    } else if (reason.equals("game")) {
                        if (message.getJSONObject("data").getBoolean("start")){
                            ScreenManager.getInstance().setScreen(new GameScene(client));
                        }
                    }
                } catch (InterruptedException e) {}
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        messageHandler();
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
    
}
