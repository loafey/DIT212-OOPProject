package com.ESSBG.app.Render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * ScreenManager is a singleton that have the ability to change the current screen or get the instance of the current screen.
 */
public class ScreenManager {

    private Screen current;
    private Screen last;

    private ScreenManager() {}

    // Helper class that is the holder of this instance
    private static class ScreenManagerHolder {
        public static final ScreenManager instance = new ScreenManager();
    }

    /**
     * Returns the only instance of the {@link ScreenManager}.
     *
     * @return the instance of {@link ScreenManager}
     */
    public static ScreenManager getInstance() {
        return ScreenManagerHolder.instance;
    }


    /**
     * Switches the screen back to the previous {@link Screen}.
     */
    public void goBackScreen() {
        if (last != null) {
            setScreen(last);
        }
    }


    /**
     * Sets the screen if screen is not null.
     *
     * @param screen the screen that will be displayed
     */
    public void setScreen(Screen screen) {
        if (current != null) {
            current.hide();
        }
        if (screen != null) {
            last = current;
            current = screen;
            current.show();
            current.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }


    /**
     * Return the current screen if a screen have been set before, else return null.
     *
     * @return the current {@link Screen}
     */
    public Screen getScreen() {
        return current;
    }
}
