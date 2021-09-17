package com.ESSBG.app.Render.GameObjects;
import com.ESSBG.app.Render.Renderable;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Card implements Renderable{
    private final Texture texture;

    private int x = 10;
    private int y = 20;

    public Card(AssetManager assetManager, String texture) {
        this.texture = assetManager.get(texture, Texture.class);
    }
    
    @Override
    public void render(SpriteBatch batch, float widthScale, float heightScale) {
        batch.draw(texture, x, y);
    }
    
}
