package com.ESSBG.app.Render;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {
    void render(SpriteBatch batch,BitmapFont font, float widthScale, float heightScale);
}
