package com.ESSBG.app.Render.SettingsScreen;

import com.ESSBG.app.Render.ScreenManager;
import com.ESSBG.app.Render.StartMenu.StartMenu;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * @author Gabriel Hagström
 * SettingsController is the controller between {@link SettingsScreen} and {@link Settings} when we are in the {@link StartMenu}
 */
public class SettingsController implements SettingsScreenController {

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
     * {@inheritDoc}
     * <p>
     * Adds applyButtonListener and backButtonListener to the screen buttons.
     */
    @Override
    public void addListeners() {
        screen.addApplyButtonListener(applyButton());
        screen.addBackButtonListener(backButton());
    }

    /**
     * {@inheritDoc}
     * <p>
     * The listener changes screen to {@link StartMenu}.
     */
    @Override
    public ClickListener backButton() {
        return new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                ScreenManager.getInstance().setScreen(new StartMenu());
            }
        };
    }

    /**
     * {@inheritDoc}
     * <p>
     * The listener retrieves data from {@link SettingsScreen} and saves it to the settings file.
     */
    @Override
    public ClickListener applyButton() {
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
