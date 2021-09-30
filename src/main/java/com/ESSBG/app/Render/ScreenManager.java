package com.ESSBG.app.Render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class ScreenManager {

    private Screen current;
    private Screen last;

    private ScreenManager() {}

    private static class ScreenManagerHolder {
        public static final ScreenManager instance = new ScreenManager();
    }

    public static ScreenManager getInstance() {
        return ScreenManagerHolder.instance;
    }


    public void goBackScreen() {
        if (last != null) {
            setScreen(last);
        }
    }

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

    public Screen getScreen() {
        return current;
    }
}
