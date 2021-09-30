package com.ESSBG.app.Render.SettingsScreen;

import com.ESSBG.app.Render.StartMenu.StartMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class SettingsController {

    private final Game screenHandler;
    private final Settings settings;
    private final SettingsScreen screen;


    public SettingsController(Game screenHandler, Settings settings, SettingsScreen screen) {
        this.screenHandler = screenHandler;
        this.settings = settings;
        this.screen = screen;
        addListeners();
    }

    private void addListeners() {
        screen.addApplyButtonListener(applyButton());
        screen.addBackButtonListener(backButton());
    }

    public ClickListener backButton() {
        return new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                screenHandler.setScreen(new StartMenu(screenHandler));
            }
        };
    }

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
