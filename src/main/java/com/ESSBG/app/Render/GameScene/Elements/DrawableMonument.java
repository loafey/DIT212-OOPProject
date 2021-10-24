package com.ESSBG.app.Render.GameScene.Elements;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * @author: Samuel Hammersberg
 */
public class DrawableMonument extends Table {
    /**
     * A Monument
     * @param skin
     */
    public DrawableMonument(Skin skin){
        super();
        this.setSkin(skin);
        setTransform(true);
        setSize(480, 128);
    }
}
