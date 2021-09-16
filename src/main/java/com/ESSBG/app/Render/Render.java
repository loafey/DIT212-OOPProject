package com.ESSBG.app.Render;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Render extends Game {
    private SpriteBatch batch;
    private BitmapFont mainFont;

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

        for (int i = 0; i < 256; i+= 16){
            final int x = i;
            renderables.add((SpriteBatch batch, float widthScale, float heightScale) -> {
                new BitmapFont().draw(batch, "" + x, x, x);
            }); 
        }
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
