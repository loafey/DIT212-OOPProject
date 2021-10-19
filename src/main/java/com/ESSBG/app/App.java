package com.ESSBG.app;

import com.ESSBG.app.Render.MainScreen;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


/**
 * @author Gabriel Hagström
 */
public class App {
    public static void main( String[] args ) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setMaximized(true);
        new Lwjgl3Application(new MainScreen(), config);
    }
}