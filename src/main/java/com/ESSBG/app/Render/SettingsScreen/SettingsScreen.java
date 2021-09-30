package com.ESSBG.app.Render.SettingsScreen;

import com.ESSBG.app.Render.SettingsScreen.Labels.LabelCheckBox;
import com.ESSBG.app.Render.SettingsScreen.Labels.LabelTextField;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SettingsScreen implements Screen {
    private static final boolean DEBUG = false; // TODO DISABLE THIS
    private final Stage stage;
    private final Settings settings;
    private final Skin skin;
    private final BitmapFont titleFont;
    private final Label.LabelStyle titleStyle;
    private final Table mainTable;
    private final Table leftSubTable;
    private final Table rightSubTable;
    private final Table middleSubTable;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private TextButton backButton;
    private TextButton applyButton;
    protected LabelTextField playerNameInput;
    protected LabelTextField fpsLimit;
    protected LabelCheckBox vSyncCheckBox;

    public SettingsScreen(Settings settings) {
        this.settings = settings;
        Viewport vp = new ScreenViewport();
        stage = new Stage(vp);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("Assets/Skins/SettingsScreen/SettingsSkin.json"));
        titleFont = skin.getFont("title");
        titleStyle = new Label.LabelStyle(titleFont, Color.BLACK);
        mainTable = new Table();
        leftSubTable = new Table();
        middleSubTable = new Table();
        rightSubTable = new Table();
        mainTableSetup(mainTable);
        leftTableSetup(leftSubTable);
        middleTableSetup(middleSubTable);
        rightTableSetup(rightSubTable);
    }

    @Override
    public void show() {
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        renderCellBorder(mainTable, 10);

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
        shapeRenderer.dispose();
        titleFont.dispose();
        skin.dispose();
        stage.dispose();
    }

    @Override
    public void dispose() {
        // this is not needed in this view, but must be implemented
    }

    private void renderCellBorder(Table table, float lineWidth) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        float yHeight = 0;
        float previousY = 0;
        float x;
        for (int rowIndex = table.getRows()-1; rowIndex >= 0; rowIndex--) {
            if (rowIndex + 1 < table.getRows()) {
                previousY += table.getRowHeight(rowIndex+1);
            }
            x = table.getX();
            yHeight += table.getRowHeight(rowIndex);
            for (int colIndex = 0; colIndex < table.getColumns()+1; colIndex++) {
                if (colIndex != 0) {
                    x += table.getColumnWidth(colIndex-1);
                }
                float xIncludingLineWidth = x - lineWidth/2;
                shapeRenderer.rect(xIncludingLineWidth, previousY, lineWidth, yHeight);
            }
        }
        shapeRenderer.end();
    }

    protected void addBackButtonListener(EventListener e) {
        backButton.addListener(e);
    }

    protected void addApplyButtonListener(EventListener e) {
        applyButton.addListener(e);
    }

    private void mainTableSetup(Table table) {
        table.setDebug(DEBUG);
        table.setFillParent(true);
        table.top();
        ScrollPane leftSubTableScroll = new ScrollPane(leftSubTable);
        ScrollPane middleSubTableScroll = new ScrollPane(middleSubTable);
        ScrollPane rightSubTableScroll = new ScrollPane(rightSubTable);
        table.add(leftSubTableScroll).top().expand();
        table.add(middleSubTableScroll).top().expand();
        table.add(rightSubTableScroll).top().expand();
        mainTable.row();
        backButton = new TextButton("Back", skin);
        applyButton = new TextButton("Apply settings", skin);
        mainTable.add(backButton).pad(10, 0, 10, 0);
        mainTable.add(applyButton).pad(10, 0, 10, 0);
        if (DEBUG) { //TODO delete this when done with settings
            TextButton deleteSaveFile = new TextButton("Clear Save File", skin);
            deleteSaveFile.addListener(new ClickListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                    settings.clearSaveFile();
                }
            });
            mainTable.add(deleteSaveFile);
        }

    }

    private void leftTableSetup(Table table) {
        table.setDebug(DEBUG);
        table.add(new Label("Gameplay", titleStyle)).height(200);
        table.row();
        playerNameInput = new LabelTextField("Player Name:", settings.getPlayerName(), skin);
        playerNameInput.setDebug(DEBUG);
        table.add(playerNameInput).left().pad(10, 0, 10, 0);
        playerNameInput.setTextFieldWidth(200);
    }

    private void middleTableSetup(Table table) {
        table.setDebug(DEBUG);
        table.add(new Label("Keybindings", titleStyle)).height(200);
    }

    private void rightTableSetup(Table table) {
        table.setDebug(DEBUG);
        table.add(new Label("Graphics", titleStyle)).height(200);
        table.row();
        vSyncCheckBox = new LabelCheckBox("V-Sync:", 1.5f, skin);
        vSyncCheckBox.setChecked(settings.getVSync());
        vSyncCheckBox.setDebug(DEBUG);
        fpsLimit = new LabelTextField("FPS limit:", String.valueOf(settings.getFPSLimit()), skin);
        fpsLimit.setInputToDigitsOnly();
        fpsLimit.setDebug(DEBUG);
        table.add(vSyncCheckBox).left().pad(10, 0, 10, 0);
        table.row();
        table.add(fpsLimit).left().pad(10, 0, 10, 0);
        table.row();
    }

}
