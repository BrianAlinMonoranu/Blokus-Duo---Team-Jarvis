package Interface.graphical;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import Control.BlokusDuo;
import Interface.UI;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.io.PrintStream;

public class StartGame extends Game {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    Thread gameControlThread;
    PrintStream uiStream;
    BlokusDuo game;
    String CurrentPlayer;

    OrthographicCamera camera;
    Stage stage;
    Skin skin;

    Screen startScreen;
    Screen playScreen;
    Screen settingsScreen;
    Screen howToPlayScreen;

    int boardOriginX;
    int boardOriginY;
    int boardOffsetY;
    int tileWidth;
    int tileHeight;

    GUI gui;

    /* This will connect the StartGame to the GUI */
    public StartGame( UI ui, BlokusDuo game) {
        gui = (GUI)ui;

        gui.getGame(this);

        this.uiStream = new PrintStream(gui.Output) ;  // for sending text to control
        this.game = game;
    }

    public void ActivatingTheStartScreen(){
       activateStartScreen(this);
    }

    public void create() {
        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.position.set(WIDTH  * 0.5f, HEIGHT * 0.5f, 0.0f);
        stage = new Stage(new FitViewport(WIDTH,HEIGHT,camera));
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("Blokus_Duo_1.json"));

        startScreen = new StartScreen(this, gui);//start screen will be this
        playScreen = new PlayScreen(this);
        settingsScreen = new SettingsScreen(this);
        howToPlayScreen = new HowToPlayScreen(this);

        ActivatingTheStartScreen();
    }
   //This will set the current screen to the start screen
    public void activateStartScreen(StartGame startGame) {
        this.game.startGame = startGame;
        setScreen(startScreen);
    }
    //This will set the current screen to the current player screen
    public void activatePlayScreen() {
        setScreen(playScreen);
    }
    //This will set the current screen to the settings screen
    public void activateSettingsScreen() {
        setScreen(settingsScreen);
    }

    public void activateHowToPlayScreen() {
        setScreen(howToPlayScreen);
    }

    public void postRunnable(Runnable r) {
        Gdx.app.postRunnable(r);
    }

    //Creates the 2 players
    void createPlayers(){
        this.CurrentPlayer = game.currentPlayer.name;
    }

    @Override
    public void dispose() {
        super.dispose();
        if (startScreen != null) startScreen.dispose();
        if (playScreen != null) playScreen.dispose();
        if (settingsScreen != null) settingsScreen.dispose();
        if (howToPlayScreen != null) howToPlayScreen.dispose();
        if (skin != null) skin.dispose();
        if (stage != null) stage.dispose();
        if (gameControlThread != null) gameControlThread.stop();
    }
}
