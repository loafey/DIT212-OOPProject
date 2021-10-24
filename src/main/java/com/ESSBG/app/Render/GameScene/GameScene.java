package com.ESSBG.app.Render.GameScene;


import com.ESSBG.app.Network.IClient;
import com.ESSBG.app.Render.GameScene.Elements.DrawableBoard;
import com.ESSBG.app.Render.GameScene.Elements.DrawableMonument;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
/**
 * @author: Samuel Hammersberg
 */
public class GameScene implements Screen {
    private Stage stage;
    private Skin skin;
    private Table sceneTable;
    private Table handCardContainer;
    private Table placedCardContainer;
    private Table monument;
    private Button pauseButton;

    private Table playerTable;

    private GameController gameController;

    private DrawableBoard board;

    public GameScene(IClient client) {
        Viewport vp = new ScreenViewport();
        stage = new Stage(vp);
        skin = new Skin(Gdx.files.internal("Assets/Skins/GameScene/GameSceneSkin.json"));

        sceneTable = new Table();
        handCardContainer = new Table();
        placedCardContainer = new Table();
        placedCardContainer.bottom();
        playerTable = new Table();
        monument = new DrawableMonument(skin);

        Gdx.input.setInputProcessor(stage);

                //pauseMenu = new PauseMenu(skin);
        //pauseMenu.setFillParent(true);

        sceneTable.setFillParent(true);

        playerTable.add(placedCardContainer);
        playerTable.row();
        playerTable.add(handCardContainer);
        playerTable.bottom();

        sceneTable.add(playerTable);
        sceneTable.row();

        sceneTable.add(monument).width(480).height(160);
        sceneTable.bottom();

        pauseButton = new Button(skin);
        pauseButton.add(new Label("Pause", skin));

        pauseButton.setPosition(0, 0);
    
        //sceneTable.add(pauseButton);
        sceneTable.setVisible(true);


        board = new DrawableBoard(skin, handCardContainer, placedCardContainer, monument);
        gameController = new GameController(client, skin, board, sceneTable);
    }

    @Override
    public void show() {
        stage.addActor(sceneTable);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        gameController.pollClient();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {

    }

}
