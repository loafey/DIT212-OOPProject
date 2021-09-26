package com.ESSBG.app.Render;


import java.util.ArrayList;

import com.ESSBG.app.AssetFinder;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.json.JSONObject;

import com.badlogic.gdx.graphics.Texture;

public class Render implements Screen {
    private SpriteBatch batch;
    private BitmapFont mainFont;
    private AssetManager assetManager;
    private Game game;

    private float widthScale = 1;
    private float heightScale = 1;
    private float originalWidth;
    private float originalHeight; 

    private ArrayList<Renderable> currentScene = new ArrayList<>();
    private RenderablePlayer rPlayer = new RenderablePlayer();

    public Render(float width, float height, Game game) {
        this.game = game;
        originalWidth = width;
        originalHeight = height;
    }

    public void update(JSONObject data){
        rPlayer.updatePlayer(data, assetManager);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        mainFont = new BitmapFont();

        assetManager = new AssetManager(new InternalFileHandleResolver());
        AssetFinder.findAssets(assetManager, "Assets/");
        assetManager.finishLoading();
        currentScene.add(rPlayer);

        // If I have pushed this it was by mistake!
        // -- oggleboa
        JSONObject j = new JSONObject("{\"msgNum\":0,\"handCards\":[{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":250,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"resource\":{\"type\":\"tree\",\"amount\":3}},{\"color\":{\"r\":213,\"g\":21,\"b\":124,\"a\":1},\"cost\":[{\"tree\":5},{\"stone\":1}],\"action\":{\"type\":\"sale\",\"types\":[\"wood\",\"silk\"],\"direction\":[\"left\"]}}],\"placedCards\":[{\"color\":{\"r\":21,\"g\":213,\"b\":124,\"a\":1},\"cost\":[{\"wood\":1}],\"resource\":{\"type\":\"silk\",\"amount\":1}}],\"resources\":{\"coins\":125,\"war\":12},\"monument\":{\"cards\":[{\"resource\":{\"type\":\"silk\",\"amount\":1}},{\"resource\":{\"type\":\"war\",\"amount\":3}},{\"resource\":{\"type\":\"wood\",\"amount\":75}}],\"unlocked\":1},\"leftNeighbour\":{\"resources\":[]},\"rightNeighbour\":{\"resources\":[]}}");
        update(j);
    }

    @Override
    public void resize(int width, int height) {      
        widthScale = originalWidth / width;
        heightScale = originalHeight / height;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for(Renderable r : currentScene) {
            r.render(batch, mainFont, widthScale, heightScale);
        }
        batch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        batch.dispose();
        mainFont.dispose();
    }

    @Override
    public void dispose() {

    }

}
