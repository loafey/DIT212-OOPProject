package com.ESSBG.app.Render.SettingsScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Gabriel Hagström
 * The Settings class represent the settings file, all interaction with the settings file should be through this class.
 */
public class Settings {

    private static final String SETTINGS_SAVE_LOCATION = "DIT212_7_Wonders/Settings";
    private final Preferences preferences = Gdx.app.getPreferences(SETTINGS_SAVE_LOCATION);
    private UUID uuid;


    /**
     * Checks if the settings file is created else create it.
     * If any values is missing add it with a default value.
     */
    public Settings() {
        settingsSetup();
    }

    /**
     * Clears the settings file and saves the cleared settings file.
     */
    public void clearSaveFile() {
        preferences.clear();
        preferences.flush();
    }

    /**
     * Applies all the settings that can be taken in effect immediately.
     */
    public void applySettings() {
        Gdx.graphics.setVSync(getVSync());
        Gdx.graphics.setForegroundFPS(getFPSLimit());
    }

    /**
     * Saves the settings file.
     * <p>
     * Save location in {@value #SETTINGS_SAVE_LOCATION} at:
     * <ul>
     * <li>Windows: %UserProfile%/.prefs/
     * <li>Linux and OS X: ~/.prefs/
     * </ul>
     */
    public void saveSettings() {
        preferences.flush();
    }

    // ------------GETTERS--------------
    /**
     * Returns PlayerName that is Obtained from the settings file.
     * <p>
     * The PlayerName have two parts:
     * <ol type="1">
     *     <li> The name the User have chosen.
     *     <li> A random UUID surrounded by two curly brackets.
     * </ol>
     *
     * @return the a unique PlayerName
     */
    public String getUniquePlayerName() {
        return preferences.getString(SettingsNames.PLAYER_NAME);
    }

    /**
     * Returns the PlayerName that the user have set.
     * <p>
     * Obtains the PlayerName from settings file and deletes the UUID.
     * If the user have not set a PlayerName this method returns an empty string.
     *
     * @return the {@link Settings#getUniquePlayerName()} without the UUID, might return empty string
     */
    public String getPlayerName() {
        String playerName = preferences.getString(SettingsNames.PLAYER_NAME);
        Pattern uuidPattern = Pattern.compile("\\{\\{[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}}}"); // Regex for a UUID
        Matcher matcher = uuidPattern.matcher(playerName);
        return matcher.replaceFirst("");
    }

    /**
     * Returns the FPS limit obtained from settings file.
     * <p>
     * The FPS limit is for the current application.
     *
     * @return the FPS limit.
     */
    public int getFPSLimit() {
        return preferences.getInteger(SettingsNames.FPS_LIMIT);
    }

    /**
     * Returns the V-Sync boolean obtained from settings file.
     * <p>
     * The value of V-Sync is for the current application.
     *
     * @return true if V-Sync is on, false otherwise
     */
    public boolean getVSync() {
        return preferences.getBoolean(SettingsNames.V_SYNC);
    }



    // ------------SETTERS--------------

    /**
     * Sets the PlayerName, but do not save.
     * <p>
     * To save the PlayerName, {@link Settings#saveSettings()} must be called.
     *
     * @param newPlayerName the new name that the player will be given upon saving
     */
    public void setPlayerName(String newPlayerName) {
        preferences.putString(SettingsNames.PLAYER_NAME, newPlayerName + "{{" + uuid.toString() + "}}");
    }

    /**
     * Sets the FPS limit, but do not apply or save.
     * <p>
     * To apply the FPS limit {@link Settings#applySettings()} must be called.
     * To save the FPS limit {@link Settings#saveSettings()} must be called.
     *
     * @param fpsLimit the new FPS limit to set, if less or equal to 0 disables the limit
     */
    public void setFPSLimit(int fpsLimit) {
        preferences.putInteger(SettingsNames.FPS_LIMIT, fpsLimit);
    }

    /**
     * Turns on/off V-Sync, depending on the given parameter.
     *
     * @param value if true V-Sync is turned on, else V-Sync is turned off
     */
    public void setVSync(boolean value) {
        preferences.putBoolean(SettingsNames.V_SYNC, value);
    }

    // --------PRIVATE METHODS----------
    private void settingsSetup() {
        if (!preferences.contains(SettingsNames.UUID)) {
            uuid = UUID.randomUUID();
            preferences.putString(SettingsNames.UUID, uuid.toString());
            setPlayerName("");
        }
        else {
            uuid = UUID.fromString(preferences.getString(SettingsNames.UUID));
        }

        if (!preferences.contains(SettingsNames.FPS_LIMIT)) {
            preferences.putInteger(SettingsNames.FPS_LIMIT, 60);
        }
        if (!preferences.contains(SettingsNames.V_SYNC)) {
            preferences.putBoolean(SettingsNames.V_SYNC, false);
        }
        saveSettings();
    }

    // This private class is just for not making spelling mistakes on the settings fields in the settings file
    private static class SettingsNames {
        static final String UUID = "UUID";
        static final String PLAYER_NAME = "PlayerName";
        static final String FPS_LIMIT = "FPS_Limit";
        static final String V_SYNC = "V-Sync";

    }
}
