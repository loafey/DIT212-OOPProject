package com.ESSBG.app.Render.StartMenu;

import com.ESSBG.app.Render.LobbyScreen.LobbyScreen;
import com.ESSBG.app.Render.ScreenManager;
import com.ESSBG.app.Render.SettingsScreen.Settings;
import com.ESSBG.app.Render.SettingsScreen.SettingsController;
import com.ESSBG.app.Render.SettingsScreen.SettingsScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * @author Gabriel Hagström
 * StartMenuController is the controller for {@link StartMenu}.
 */
public class StartMenuController {

    private final StartMenu startMenu;

    /**
     * Setting up the ClickListeners for the StartMenu.
     *
     * @param startMenu the screen where all the button are.
     */
    public StartMenuController(StartMenu startMenu) {
        this.startMenu = startMenu;
        addListeners();
    }

    private void addListeners() {
        startMenu.addStartGameButtonListener(startGameButton());
        startMenu.addSettingsButtonListener(settingsButton());
        startMenu.addQuitButtonListener(quitButton());
    }

    private ClickListener startGameButton() {
        return new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                ScreenManager.getInstance().setScreen(new LobbyScreen());
            }
        };
    }

    private ClickListener settingsButton() {
        return new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                Settings settings = new Settings();
                SettingsScreen settingsScreen = new SettingsScreen(settings);
                new SettingsController(settings, settingsScreen);
                ScreenManager.getInstance().setScreen(settingsScreen);
            }
        };
    }

    private ClickListener quitButton() {
        return new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                Gdx.app.exit();
            }
        };
    }
}
