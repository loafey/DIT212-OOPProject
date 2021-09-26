package com.ESSBG.app.Render.GameScene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;

import org.json.JSONObject;

public class GameScene implements Screen {
    private Game game;
    private Stage stage;
    private Skin skin;
    private Table sceneTable;
    private Table handCardContainer;

    private RenderablePlayer rPlayer = new RenderablePlayer();

    public GameScene(Game game) {
        this.game = game;
    }

    public void update(JSONObject data) {
        rPlayer.updatePlayer(data, skin, handCardContainer);
    }

    @Override
    public void show() {
        Viewport vp = new ScreenViewport();
        stage = new Stage(vp);
        sceneTable = new Table();
        handCardContainer = new Table();
        skin = new Skin(Gdx.files.internal("Assets/Skins/GameScene/GameSceneSkin.json"));

        Gdx.input.setInputProcessor(stage);

        sceneTable.setDebug(true);
        sceneTable.setFillParent(true);
        sceneTable.add(handCardContainer);
        sceneTable.bottom();

        stage.addActor(sceneTable);

        // If I have pushed this it was by mistake!
        // -- oggleboa
        JSONObject j = new JSONObject(
                "{\"msgNum\":0,\"handCards\":[{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":213,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"action\":{\"type\":\"sale\",\"types\":[\"wood\",\"silk\"],\"direction\":[\"left\"]}}],\"placedCards\":[{\"color\":{\"r\":21,\"g\":213,\"b\":124,\"a\":1},\"cost\":[{\"wood\":1}],\"resource\":{\"type\":\"silk\",\"amount\":1}}],\"resources\":{\"coins\":125,\"war\":12},\"monument\":{\"cards\":[{\"resource\":{\"type\":\"silk\",\"amount\":1}},{\"resource\":{\"type\":\"war\",\"amount\":3}},{\"resource\":{\"type\":\"wood\",\"amount\":75}}],\"unlocked\":1},\"leftNeighbour\":{\"resources\":[]},\"rightNeighbour\":{\"resources\":[]}}");
        update(j);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
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
