package com.ESSBG.app.Render.GameScene;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class PauseMenu extends Window {
    private Button resumeButton;
    private Button exitButton;

    public PauseMenu(Skin skin) {
        super("yo", skin);
        this.center();
        this.add(new Label("7 Wonders", skin));
        this.row();
        
        resumeButton = new Button(skin);
        resumeButton.add(new Label("Resume", skin));
        this.add(resumeButton);
        this.row();

        exitButton = new Button(skin);
        exitButton.add(new Label("Exit", skin));
        this.add(exitButton);
    }   
}
