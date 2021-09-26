package com.ESSBG.app.Render;

import com.ESSBG.app.Render.GameScene.GameScene;
import com.ESSBG.app.Render.StartMenu.StartMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MainGame extends Game {

    @Override
    public void create() {
        Screen renderer = new GameScene(this);
        Screen startMenu = new StartMenu(this);
        setScreen(startMenu);
    }

    @Override
    public void render() {
        super.render();
        int mousePosX = Gdx.input.getX();
        int mousePosY = Gdx.input.getY();
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond() + "    Mouse position (x = " + mousePosX
                + ", y = " + mousePosY + ")");
    }
}
