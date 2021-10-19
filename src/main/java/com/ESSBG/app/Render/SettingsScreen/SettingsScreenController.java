package com.ESSBG.app.Render.SettingsScreen;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Gabriel Hagström
 * Interface to implement the Controller for SettingsScreen.
 * <p>
 * The Controller should implement this if the buttons should do different things depending on where we are in the program.
 */
public interface SettingsScreenController {

    /**
     * Adds all the listeners to the {@link SettingsScreen}
     */
    void addListeners();

    /**
     * Returns the clickListener that handles what happen when the user press the apply button in {@link SettingsScreen}.
     *
     * @return the clickListener for the apply button in {@link SettingsScreen}
     */
    ClickListener applyButton();

    /**
     * Returns the clickListener that handles what happen when the user press the back button in {@link SettingsScreen}.
     *
     * @return the clickListener for the back button in {@link SettingsScreen}
     */
    ClickListener backButton();
}
