package com.ESSBG.app.Render;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class RenderableCard implements Renderable{
    private final Texture texture;
    private final String data;
    private final Color color;

    private int x;
    private int y;

    public RenderableCard(AssetManager assetManager, String texture,String data,Color color, int x, int y) {
        this.texture = assetManager.get(texture, Texture.class);
        this.x = x;
        this.y = y;
        this.data = data;
        this.color = color;
    }
    
    @Override
    public void render(SpriteBatch batch, BitmapFont font, float widthScale, float heightScale) {
        batch.setColor(color);
        batch.draw(texture, x, y);
        font.draw(batch, data, x+32, y+32);
        batch.setColor(Color.WHITE);
    }
    
}
