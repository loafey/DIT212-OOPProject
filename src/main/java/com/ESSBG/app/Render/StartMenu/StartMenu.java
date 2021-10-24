package com.ESSBG.app.Render.StartMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;

/**
 * @author Gabriel Hagström
 */
public class StartMenu implements Screen {

    private Stage stage;
    private Skin skin;
    private Table table;

    // Buttons
    private TextButton btnStart;
    private TextButton btnSettings;
    private TextButton btnQuit;

    public StartMenu() {
        Viewport vp = new ScreenViewport();
        stage = new Stage(vp);
        table = new Table();
        skin = new Skin(Gdx.files.internal("Assets/Skins/MainMenu/StartMenuSkin.json"));

        Gdx.input.setInputProcessor(stage);

        // Label setup
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("title");
        Label titleName = new Label("7 Wonders", labelStyle);
        // Button setup

        btnStart = generateTextButton("Start Game", skin);
        btnSettings = generateTextButton("Settings", skin);
        btnQuit = generateTextButton("Quit", skin);

        // Table setup
        table.setFillParent(true);
        addToTableRowPadBottom(titleName, 30);
        addToTableRowPadBottom(btnStart, 10);
        addToTableRowPadBottom(btnSettings, 10);
        addToTableRowPadBottom(btnQuit, 10);
    }

    void addStartGameButtonListener(EventListener e) {
        btnStart.addListener(e);
    }

    void addSettingsButtonListener(EventListener e) {
        btnSettings.addListener(e);
    }

    void addQuitButtonListener(EventListener e) {
        btnQuit.addListener(e);
    }


    private TextButton generateTextButton(String text, Skin skin) {
        TextButton btn = new TextButton(text, skin);
        btn.getLabelCell().size(200, btn.getHeight());
        return btn;
    }

    private void addToTableRowPadBottom(Actor actor, float bottomPadding) {
        addToTableRow(actor, 0, 0, 0, bottomPadding);
    }

    private void addToTableRow(Actor actor, float topPadding, float rightPadding, float leftPadding, float bottomPadding) {
        table.row();
        Cell<Actor> cell = table.add(actor);
        cell.padTop(topPadding);
        cell.padRight(rightPadding);
        cell.padLeft(leftPadding);
        cell.padBottom(bottomPadding);
    }

    @Override
    public void show() {
        // Stage setup
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // this is not needed in this view, but must be implemented
    }

    @Override
    public void resume() {
        // this is not needed in this view, but must be implemented
    }

    @Override
    public void hide() {
        skin.dispose();
        stage.dispose();
    }

    @Override
    public void dispose() {
        // this is not needed in this view, but must be implemented
    }

}
