package com.ESSBG.app.Render;


import java.util.ArrayList;

import com.ESSBG.app.AssetFinder;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class Render extends Game {
    private SpriteBatch batch;
    private BitmapFont mainFont;
    private AssetManager assetManager;

    private float widthScale = 1;
    private float heightScale = 1;
    private float originalWidth;
    private float originalHeight; 

    private ArrayList<Renderable> renderables = new ArrayList<>();

    public Render(int width, int height) {
        super();
        originalWidth = width;
        originalHeight = height;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        mainFont = new BitmapFont();

        assetManager = new AssetManager(new InternalFileHandleResolver());
        AssetFinder.findAssets(assetManager, "Assets/");
        assetManager.finishLoading();

        renderables.add(
            new Card(assetManager,"Assets/Textures/Cards/coin.png")
        );
    }

    @Override
    public void resize(int width, int height) {      
        widthScale = originalWidth / width;
        heightScale = originalHeight / height;
        System.out.println(widthScale + " " + heightScale);  
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for(int i = 0; i < renderables.size(); i ++) {
            renderables.get(i).render(batch, widthScale, heightScale);
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
    public void dispose() {
        batch.dispose();
        mainFont.dispose();
    }
    
}
