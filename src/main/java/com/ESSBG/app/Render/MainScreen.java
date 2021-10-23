package com.ESSBG.app.Render;

import com.ESSBG.app.Render.SettingsScreen.Settings;
import com.ESSBG.app.Render.StartMenu.StartMenu;
import com.ESSBG.app.Render.StartMenu.StartMenuController;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

/**
 * @author Gabriel Hagström, Samuel Hammersberg
 */
public class MainScreen implements ApplicationListener {


    @Override
    public void create() {
        new Settings().applySettings(); // apply all settings that the user have set.
        StartMenu startMenu = new StartMenu();
        new StartMenuController(startMenu);
        ScreenManager.getInstance().setScreen(startMenu);
    }

    @Override
    public void resize(int width, int height) {
        ScreenManager.getInstance().getScreen().resize(width, height);
    }

    @Override
    public void render() {
        ScreenManager.getInstance().getScreen().render(Gdx.graphics.getDeltaTime());
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond() + "    Mouse position (x = " + Gdx.input.getX()
                + ", y = " + Gdx.input.getY() + ")");
    }

    @Override
    public void pause() {
        ScreenManager.getInstance().getScreen().pause();
    }

    @Override
    public void resume() {
        ScreenManager.getInstance().getScreen().resume();
    }

    @Override
    public void dispose() {
        ScreenManager.getInstance().getScreen().hide();
    }
}
