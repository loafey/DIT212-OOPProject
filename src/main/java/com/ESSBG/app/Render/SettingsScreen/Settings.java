package com.ESSBG.app.Render.SettingsScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Settings {

    private static final String SETTINGS_SAVE_LOCATION = "DIT212_7_Wonders/Settings";
    private final Preferences preferences = Gdx.app.getPreferences(SETTINGS_SAVE_LOCATION);
    private UUID uuid;


    public Settings() {
        settingsSetup();
    }

    public void clearSaveFile() {
        preferences.clear();
        preferences.flush();
    }

    public void applySettings() {
        Gdx.graphics.setVSync(getVSync());
        Gdx.graphics.setForegroundFPS(getFPSLimit());
    }

    public void saveSettings() {
        // Save location:
        // Windows   %UserProfile%/.prefs/
        // Linux     ~/.prefs/
        preferences.flush();
    }

    // ------------GETTERS--------------
    public String getUniquePlayerName() {
        return preferences.getString(SettingsNames.PLAYER_NAME);
    }

    public String getPlayerName() {
        String playerName = preferences.getString(SettingsNames.PLAYER_NAME);
        Pattern uuidPattern = Pattern.compile("\\{\\{[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}}}"); // Regex for a UUID
        Matcher matcher = uuidPattern.matcher(playerName);
        return matcher.replaceFirst("");
    }

    public int getFPSLimit() {
        return preferences.getInteger(SettingsNames.FPS_LIMIT);
    }

    public boolean getVSync() {
        return preferences.getBoolean(SettingsNames.V_SYNC);
    }


    // ------------SETTERS--------------
    public void setPlayerName(String newPlayerName) {
        preferences.putString(SettingsNames.PLAYER_NAME, newPlayerName + "{{" + uuid.toString() + "}}");
    }

    public void setFPSLimit(int fpsLimit) {
        preferences.putInteger(SettingsNames.FPS_LIMIT, fpsLimit);
    }

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
