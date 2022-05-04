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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.lwjgl.opengl.GL20;

public class StartScreen extends ScreenAdapter {

    StartGame startGame;
    Stage stage;
    Skin skin;
    GUI gui;

    //Initiates the screen
    public StartScreen(StartGame startGame, GUI gui) {

        // Set up the scene
        this.gui = gui;
        this.startGame = startGame;
        this.stage = startGame.stage;
        this.skin = startGame.skin;
    }

    void showDialog() {
        float halfStageWidth = stage.getWidth() / 2;
        Label title = new Label("Blokus Duo", skin, "Blocky", SettingsScreen.textColor);
        title.setPosition(halfStageWidth - (title.getWidth() / 2), stage.getHeight() - (stage.getHeight() / 3));
        stage.addActor(title);

        Label labelPlayer1 = new Label("Player 1 Name", skin, "Arcade", SettingsScreen.textColor);
        labelPlayer1.setPosition(halfStageWidth - (labelPlayer1.getWidth() / 2), title.getY() - 100);
        stage.addActor(labelPlayer1);

        // This text-field will contain the 1st players name
        TextField textFieldPlayer1 = new TextField(null, skin);
        textFieldPlayer1.setMessageText("Mary");
        textFieldPlayer1.setPosition(halfStageWidth - (textFieldPlayer1.getWidth() / 2), labelPlayer1.getY() - 61);
        stage.addActor(textFieldPlayer1);

        Label labelPlayer2 = new Label("Player 2 Name", skin, "Arcade", SettingsScreen.textColor);
        labelPlayer2.setPosition(halfStageWidth - (labelPlayer2.getWidth() / 2), textFieldPlayer1.getY() - 60);
        stage.addActor(labelPlayer2);

        // This text-field will contain the 2nd players name
        TextField textFieldPlayer2 = new TextField(null, skin);
        textFieldPlayer2.setMessageText("James");
        textFieldPlayer2.setPosition(halfStageWidth - (textFieldPlayer2.getWidth() / 2), labelPlayer2.getY() - 61);
        stage.addActor(textFieldPlayer2);

        //Creates button and when it is pressed it will check certain conditions are met below, if met, proceeds to the next page
        TextButton startButton = new TextButton("Play", skin, "TESTING1");
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                String player1name = textFieldPlayer1.getText();
                String player2name = textFieldPlayer2.getText();

                if (!(player1name.matches("^[a-zA-Z]+$")) && !(player2name.matches("^[a-zA-Z]+$"))) {
                    labelPlayer1.setText("Error enter Alphabetical letters");
                    labelPlayer2.setText("Error enter Alphabetical letters");
                    labelPlayer1.setPosition(halfStageWidth - (labelPlayer1.getWidth() / 2) - 235, title.getY() - 100);
                    labelPlayer2.setPosition(halfStageWidth - (labelPlayer2.getWidth() / 2) - 235, textFieldPlayer1.getY() - 60);
                    textFieldPlayer1.setText("");
                    textFieldPlayer2.setText("");
                } else if (!(player1name.matches("^[a-zA-Z]+$"))) {
                    labelPlayer2.setText("Player 2 Name");
                    labelPlayer1.setText("Error enter Alphabetical letters");
                    labelPlayer1.setPosition(halfStageWidth - (labelPlayer1.getWidth() / 2) - 235, title.getY() - 100);
                    labelPlayer2.setPosition(halfStageWidth - (labelPlayer2.getWidth() / 2), textFieldPlayer1.getY() - 60);
                    textFieldPlayer1.setText("");
                } else if (!(player2name.matches("^[a-zA-Z]+$"))) {
                    labelPlayer1.setText("Player 1 Name");
                    labelPlayer2.setText("Error enter Alphabetical letters");
                    labelPlayer1.setPosition(halfStageWidth - (labelPlayer1.getWidth() / 2), title.getY() - 100);
                    labelPlayer2.setPosition(halfStageWidth - (labelPlayer2.getWidth() / 2) - 235, textFieldPlayer1.getY() - 60);
                    textFieldPlayer2.setText("");
                } else {
                    startGame.uiStream.println("names");
                    startGame.uiStream.println(player1name);// prints to the OUTPUT BUT INPUT IS LISTENING
                    startGame.uiStream.println(player2name);
                }

            }
        });
        startButton.setPosition(halfStageWidth - (startButton.getWidth() / 2), textFieldPlayer2.getY() - 80);
        stage.addActor(startButton);

        TextButton settingsButton = new TextButton("Settings", skin, "TESTING1");
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGame.activateSettingsScreen();
            }
        });
        settingsButton.setPosition(10, 10);
        stage.addActor(settingsButton);
    }

    public void show() {
        super.show();
        showDialog();
    }

    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(SettingsScreen.backgroundColor.r, SettingsScreen.backgroundColor.g, SettingsScreen.backgroundColor.b, SettingsScreen.backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    public void hide() {
        super.hide();
        stage.clear();
    }

    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }
}
