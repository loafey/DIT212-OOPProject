package com.ESSBG.app.Render.SettingsScreen;

import com.ESSBG.app.Render.ScreenManager;
import com.ESSBG.app.Render.StartMenu.StartMenu;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * SettingsController is the controller between {@link SettingsScreen} and {@link Settings} when we are in the {@link StartMenu}
 */
public class SettingsController implements SettingsScreenController {

    private final Settings settings;
    private final SettingsScreen screen;


    /**
     * @param settings
     * @param screen
     */
    public SettingsController(Settings settings, SettingsScreen screen) {
        this.settings = settings;
        this.screen = screen;
        addListeners();
    }

    @Override
    public void addListeners() {
        screen.addApplyButtonListener(applyButton());
        screen.addBackButtonListener(backButton());
    }

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
