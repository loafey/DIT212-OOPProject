package com.ESSBG.app.Render.SettingsScreen;

import com.ESSBG.app.Render.ScreenManager;
import com.ESSBG.app.Render.StartMenu.StartMenu;
import com.ESSBG.app.Render.StartMenu.StartMenuController;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * @author Gabriel Hagström
 * SettingsController is the controller between {@link SettingsScreen} and {@link Settings} when we are in the {@link SettingsScreen}
 */
public class SettingsController {

    private final Settings settings;
    private final SettingsScreen screen;


    /**
     * Setting up the ClickListeners for the SettingsScreen.
     *
     * @param settings the class with the logic on how to interact with the settings file
     * @param screen the screen where the applyButton and backButton is
     */
    public SettingsController(Settings settings, SettingsScreen screen) {
        this.settings = settings;
        this.screen = screen;
        addListeners();
    }

    /**
     * Adds all the listeners to the {@link SettingsScreen}
     */
    private void addListeners() {
        screen.addApplyButtonListener(applyButton());
        screen.addBackButtonListener(backButton());
    }

    /**
     * Returns the clickListener that handles what happen when the user press the back button in {@link SettingsScreen}.
     *
     * @return the clickListener for the back button in {@link SettingsScreen}
     */
    private ClickListener backButton() {
        return new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                StartMenu startMenu = new StartMenu();
                new StartMenuController(startMenu);
                ScreenManager.getInstance().setScreen(startMenu);
            }
        };
    }

    /**
     * Returns the clickListener that handles what happen when the user press the apply button in {@link SettingsScreen}.
     *
     * @return the clickListener for the apply button in {@link SettingsScreen}
     */
    private ClickListener applyButton() {
        return new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                settings.setPlayerName(screen.playerNameInput.getTextFieldText());
                settings.setFPSLimit(Integer.parseInt(screen.fpsLimit.getTextFieldText()));
                settings.setVSync(screen.vSyncCheckBox.isChecked());
                settings.saveSettings();
                settings.applySettings();
            }
        };
    }
}
