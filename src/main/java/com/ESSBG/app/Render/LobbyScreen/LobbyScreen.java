package com.ESSBG.app.Render.LobbyScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LobbyScreen implements Screen{
    private Game game;
    private Stage stage;
    private Skin skin;
    private Table sceneTable;

    public LobbyScreen(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        Viewport vp = new ScreenViewport();
        stage = new Stage(vp);
        skin = new Skin(Gdx.files.internal("Assets/Skins/LobbyScreen/LobbyScreenSkin.json"));
        
        sceneTable = new Table();
        stage.addActor(sceneTable);
    }
    @Override
    public void render(float delta) {
    }
    @Override
    public void resize(int width, int height) {
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
    }
    
}
