package com.ESSBG.app.Render.LobbyScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LobbyScreen implements Screen{
    private Stage stage;
    private Skin skin;
    private Table sceneTable;

    private TextField ipField;
    private Button joinButton;


    @Override
    public void show() {
        Viewport vp = new ScreenViewport();
        stage = new Stage(vp);
        skin = new Skin(Gdx.files.internal("Assets/Skins/LobbyScreen/LobbySceneSkin.json"));
        
        sceneTable = new Table();
        sceneTable.center();
        sceneTable.setFillParent(true);
        stage.addActor(sceneTable);

        Gdx.input.setInputProcessor(stage);
        
        sceneTable.add(new Label("Join Server:",skin));
        sceneTable.row();
        joinButton = new Button(skin);
        joinButton.add(new Label("Connect:", skin));
        sceneTable.add(joinButton);

        ipField = new TextField("", skin);
        sceneTable.add(ipField);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
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
