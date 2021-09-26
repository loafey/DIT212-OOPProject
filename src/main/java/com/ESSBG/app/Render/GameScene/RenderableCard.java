package com.ESSBG.app.Render.GameScene;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class RenderableCard implements Renderable {
    private final Texture texture;
    private final String data;
    private final Color color;

    private float x;
    private float y;
    private float rotation;

    public RenderableCard(AssetManager assetManager, String texture, String data, Color color, float x, float y,
            float rotation) {
        this.texture = assetManager.get(texture, Texture.class);
        this.x = x;
        this.y = y;
        this.data = data;
        this.color = color;
        this.rotation = rotation;
    }

    @Override
    public void render(SpriteBatch batch, BitmapFont font, float widthScale, float heightScale) {
        batch.setColor(color);
        batch.draw(texture, x, y, 42, 64, 84, 128, 1, 1, rotation, 0, 0, 84, 128, false, false);
        font.draw(batch, data, x + 32, y + 32);
        batch.setColor(Color.WHITE);
        // this.rotation += 1;
    }

}
