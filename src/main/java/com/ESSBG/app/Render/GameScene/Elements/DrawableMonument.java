package com.ESSBG.app.Render.GameScene.Elements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DrawableMonument extends Table {
    public DrawableMonument(Skin skin){
        super();
        this.setSkin(skin);
        setTransform(true);
        setSize(480, 128);
        add(new Label("monument", skin));
    }
}
