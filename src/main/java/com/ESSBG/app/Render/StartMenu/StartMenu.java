package com.ESSBG.app.Render.StartMenu;

import com.ESSBG.app.Render.LobbyScreen.LobbyScreen;
import com.ESSBG.app.Render.ScreenManager;
import com.ESSBG.app.Render.SettingsScreen.Settings;
import com.ESSBG.app.Render.SettingsScreen.SettingsController;
import com.ESSBG.app.Render.SettingsScreen.SettingsScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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



    private TextButton generateTextButton(String text, Skin skin, ClickListener clickListener) {
        TextButton btn = new TextButton(text, skin);
        btn.getLabelCell().size(200, btn.getHeight());
        btn.addListener(clickListener);
        return btn;
    }

    private void addToTableRowPadTop(Actor actor, float topPadding) {
        addToTableRow(actor, topPadding, 0, 0, 0);
    }

    private void addToTableRowPadRight(Actor actor, float rightPadding) {
        addToTableRow(actor, 0, rightPadding, 0, 0);
    }

    private void addToTableRowPadLeft(Actor actor, float leftPadding) {
        addToTableRow(actor, 0, 0, leftPadding, 0);
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
        setupButtons();

        // Table setup
        table.setFillParent(true);
        addToTableRowPadBottom(titleName, 30);
        addToTableRowPadBottom(btnStart, 10);
        addToTableRowPadBottom(btnSettings, 10);
        addToTableRowPadBottom(btnQuit, 10);

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
        stage.dispose();
    }

    @Override
    public void dispose() {
        // this is not needed in this view, but must be implemented
    }

    private void setupButtons() {
        btnStart = generateTextButton("Start Game", skin, new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                ScreenManager.getInstance().setScreen(new LobbyScreen());
            }
        });

        btnSettings = generateTextButton("Settings", skin, new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                Settings settings = new Settings();
                SettingsScreen settingsScreen = new SettingsScreen(settings);
                new SettingsController(settings, settingsScreen);
                ScreenManager.getInstance().setScreen(settingsScreen);
            }
        });

        btnQuit = generateTextButton("Quit", skin, new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                Gdx.app.exit();
            }
        });
    }
}
