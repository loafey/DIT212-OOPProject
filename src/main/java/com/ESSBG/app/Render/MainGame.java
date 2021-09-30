package com.ESSBG.app.Render;

import com.ESSBG.app.Render.GameScene.GameScene;
import com.ESSBG.app.Render.SettingsScreen.Settings;
import com.ESSBG.app.Render.SettingsScreen.SettingsController;
import com.ESSBG.app.Render.SettingsScreen.SettingsScreen;
import com.ESSBG.app.Render.StartMenu.StartMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MainGame extends Game {

    @Override
    public void create() {
        new Settings().applySettings(); // apply all settings that the user have set.
        Screen renderer = new GameScene(this);
        Screen startMenu = new StartMenu(this);
//        Settings settings = new Settings();
//        SettingsScreen settingsScreen = new SettingsScreen(settings);
//        new SettingsController(this, settings, settingsScreen);

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
