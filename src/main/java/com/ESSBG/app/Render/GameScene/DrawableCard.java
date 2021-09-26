package com.ESSBG.app.Render.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DrawableCard extends Button {
    private float scaleGoal = 1f;
    private float scaleCurrent = scaleGoal;

    private float rotationOriginal;
    private float rotationGoal;
    private float rotationCurrent;

    public DrawableCard(Skin skin, float rotation, Color color) {
        super(skin);

        super.setTransform(true);

        rotationOriginal = rotation;
        rotationGoal = rotation;
        rotationCurrent = rotation;

        setColor(color);

        super.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                scaleGoal = 1.1f;
                rotationGoal = 0;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                scaleGoal = 1f;
                rotationGoal = rotationOriginal;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        scaleCurrent = MathUtils.lerp(scaleCurrent, scaleGoal, Gdx.graphics.getDeltaTime() * 20);
        rotationCurrent = MathUtils.lerp(rotationCurrent, rotationGoal, Gdx.graphics.getDeltaTime() * 20);

        setScale(scaleCurrent, scaleCurrent);
        setRotation(rotationCurrent);
    }
}
