package com.ESSBG.app.Render.GameScene;

import java.io.InterruptedIOException;

import com.ESSBG.app.Network.Client;
import com.ESSBG.app.Render.GameScene.Elements.DrawableBoard;
import com.ESSBG.app.Render.GameScene.Elements.DrawableMonument;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameScene implements Screen {
    private Stage stage;
    private Skin skin;
    private Table sceneTable;
    private Table handCardContainer;
    private Table placedCardContainer;
    private Table monument;
    private Button pauseButton;

    private Table playerTable;

    private GameController gameController;

    private DrawableBoard board;

    private Table pauseMenu;

    private Client client;

    public GameScene(Client client) {
        this.client = client;
    }

    public void displayScores(JSONObject data) {
        JSONArray scoreList = data.getJSONArray("scores");
        sceneTable.clear();
        
        String scoreText = "Name: \t| Score:\n";
        for (Object so : scoreList) {
            JSONObject sdata = (JSONObject)so;
            scoreText += sdata.getString("name") + "\t" + sdata.getInt("score") + "\n";
        }

        sceneTable.center();
        sceneTable.add(new Label(scoreText, skin));
    }

    /**
     * Takes in JSON data to be displayed by the view.
     * @param data The data in question
     */
    public void update(JSONObject data) {
        board.updateBoard(data);
    }

    @Override
    public void show() {
        Viewport vp = new ScreenViewport();
        stage = new Stage(vp);
        skin = new Skin(Gdx.files.internal("Assets/Skins/GameScene/GameSceneSkin.json"));

        sceneTable = new Table();
        handCardContainer = new Table();
        placedCardContainer = new Table();
        placedCardContainer.bottom();
        playerTable = new Table();
        monument = new DrawableMonument(skin);

        //pauseMenu = new PauseMenu(skin);
        //pauseMenu.setFillParent(true);

        Gdx.input.setInputProcessor(stage);

        sceneTable.setFillParent(true);

        playerTable.add(placedCardContainer);
        playerTable.row();
        playerTable.add(handCardContainer);
        playerTable.bottom();

        sceneTable.add(playerTable);
        sceneTable.row();

        sceneTable.add(monument).width(480).height(160);
        sceneTable.bottom();

        pauseButton = new Button(skin);
        pauseButton.add(new Label("Pause", skin));

        pauseButton.setPosition(0, 0);
    
        sceneTable.add(pauseButton);
        sceneTable.setVisible(true);


        gameController = new GameController(client, skin);
        board = new DrawableBoard(gameController, skin, handCardContainer, placedCardContainer, monument);

        stage.addActor(sceneTable);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        pollClient();
    }

    // TODO should really be moved to GameController, 
    // and should preferably use observer pattern instead.
    private void pollClient() {
        if (client != null) {
            if (client.getMsgQueue().size() > 0){
                try {
                    JSONObject msg = client.getMsgQueue().take().getJSONObject("data");
                    if (msg.has("reply") && msg.getBoolean("reply")){
                        board.hideHandCards();
                    }
                    if (msg.has("placedCards")) {
                        update(msg);
                    } 
                    if (msg.has("scores")){
                        displayScores(msg);
                    }
                } catch (InterruptedException e){}
            }
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {

    }

}
