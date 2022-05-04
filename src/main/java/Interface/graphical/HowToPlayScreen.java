package Interface.graphical;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import org.lwjgl.opengl.GL20;

import java.util.concurrent.TimeUnit;

public class HowToPlayScreen extends ScreenAdapter {

    StartGame startGame;
    Stage stage;
    Skin skin;
    private long startTime;
    private Button startGameButton;
    private Label loadingGame;

    //Initiates the screen
    public HowToPlayScreen(StartGame startGame) {

        // Set up the scene
        this.startGame = startGame;
        this.stage = startGame.stage;
        this.skin = startGame.skin;
    }

    void showDialog() {
        startTime = System.currentTimeMillis();
        String howToPlay =
                """
                        Click the piece you would like to place
                        Press f on your keyboard to flip the piece
                        Press r to rotate the piece
                        Click the area on the board where you would like to place the piece
                        When placing the piece player 1 must cover at least 1 black dot and player 2
                        must cover at least 1 white dot
                        The piece must also not touch any other of the players pieces side by side
                        """;

        String scoring =
                """
                        Each player begins with 0 points
                        At the end of the game if the player places all of their pieces they receive 15 points
                        Otherwise the value of each piece is taken away from the players points
                        If the last piece placed by the player is the smallest piece they receive 5 points
                        The smallest piece is the single square
                        The value of pieces is calculated based on the number of squares it covers
                        """;

        Label howToPlayTitle = new Label("How to Play", skin, "Arcade", SettingsScreen.textColor);
        howToPlayTitle.setPosition((stage.getWidth() - howToPlayTitle.getWidth()) / 2, stage.getHeight() - stage.getHeight() / 5);
        stage.addActor(howToPlayTitle);

        Label howToPlayDescription = new Label(howToPlay, skin, "Child", SettingsScreen.textColor);
        howToPlayDescription.setAlignment(Align.center);
        howToPlayDescription.setPosition((stage.getWidth() - howToPlayDescription.getWidth()) / 2, howToPlayTitle.getY() - 216);
        stage.addActor(howToPlayDescription);

        Label scoringTitle = new Label("Scoring", skin, "Arcade", SettingsScreen.textColor);
        scoringTitle.setPosition((stage.getWidth() - scoringTitle.getWidth()) / 2, stage.getHeight() / 2.5f);
        stage.addActor(scoringTitle);

        Label scoringDescription = new Label(scoring, skin,"Child", SettingsScreen.textColor);
        scoringDescription.setAlignment(Align.center);
        scoringDescription.setPosition((stage.getWidth() - scoringDescription.getWidth()) / 2, scoringTitle.getY() - 188);
        stage.addActor(scoringDescription);

        loadingGame = new Label("Loading game", skin,"Child", SettingsScreen.textColor);
        loadingGame.setPosition((stage.getWidth() - loadingGame.getWidth()) / 2, 20);
        stage.addActor(loadingGame);

        startGameButton = new TextButton("Start Game", skin, "TESTING1");
        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGame.activatePlayScreen();
            }
        });
        startGameButton.setPosition(stage.getWidth() - startGameButton.getWidth() - 10, 10);
        startGameButton.setVisible(false);
        stage.addActor(startGameButton);
    }

    public void show() {
        super.show();
        showDialog();
    }

    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(SettingsScreen.backgroundColor.r, SettingsScreen.backgroundColor.g, SettingsScreen.backgroundColor.b, SettingsScreen.backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //The button to start the game takes 5 seconds to appear
        if (System.currentTimeMillis() - startTime > TimeUnit.SECONDS.toMillis(5)) {
            loadingGame.setVisible(false);
            startGameButton.setVisible(true);
        }

        stage.act();
        stage.draw();
    }

    public void hide() {
        super.hide();
        stage.clear();
    }

    public void resize(int width, int height) {
        super.resize(width,height);
        stage.getViewport().update(width, height, true);
    }
}