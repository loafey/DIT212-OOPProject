package com.ESSBG.app;

import com.ESSBG.app.Render.Render;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args ) {
        System.out.println("Hello World!");
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
//        config.setIdleFPS(60);
//        config.useVsync(true);
        config.setTitle("Hello World!");
        config.setWindowedMode(800, 600);
        new Lwjgl3Application(new Render(800,600), config);
    }
}