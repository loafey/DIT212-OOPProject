package com.ESSBG.app.Render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {
    void render(SpriteBatch batch, float widthScale, float heightScale);
}
