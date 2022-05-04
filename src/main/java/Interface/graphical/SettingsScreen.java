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
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.lwjgl.opengl.GL20;

/* This class/program will create the settings screen */
public class SettingsScreen extends ScreenAdapter {

    StartGame startGame;
    Stage stage;
    Skin skin;

    static Color backgroundColor = new Color(Color.WHITE);
    static Color textColor = new Color(Color.BLACK);
    static Color player1PieceColor = new Color(Color.BLACK);
    static Color player2PieceColor = new Color(Color.WHITE);
    static boolean musicOn = false;

    Music[] songs ={Gdx.audio.newMusic(Gdx.files.internal("RickSong.mp3")),
            Gdx.audio.newMusic(Gdx.files.internal("minecraft-song1.mp3")),
            Gdx.audio.newMusic(Gdx.files.internal("minecraft-song2.mp3")),
            Gdx.audio.newMusic(Gdx.files.internal("minecraft-song3.mp3")),
            Gdx.audio.newMusic(Gdx.files.internal("tetris-song.mp3")),
            Gdx.audio.newMusic(Gdx.files.internal("mario-song.mp3")),
            Gdx.audio.newMusic(Gdx.files.internal("lofi-song.mp3")),
            Gdx.audio.newMusic(Gdx.files.internal("piano-song1.mp3")),
            Gdx.audio.newMusic(Gdx.files.internal("piano-song2.mp3")),
            Gdx.audio.newMusic(Gdx.files.internal("guitar-song.mp3"))
    };
    int currentSongNumber = 1;
    Music currentSong = songs[currentSongNumber];
    float musicVolume;

    //Initiates the screen
    public SettingsScreen(StartGame startGame) {

        // Set up the scene
        this.startGame = startGame;
        this.stage = startGame.stage;
        this.skin = startGame.skin;
    }

    void showDialog() {
        Label piece1Description = new Label("Player 1 Piece Color", skin,"Child", textColor);
        piece1Description.setPosition(290,432);

        // This text-field will contain the color of the 1st player's pieces
        TextField piece1 = new TextField(null,skin);
        piece1.setSize(50, 50);

        // Three sliders in a column - one for each type of rgb system
        VerticalGroup pickers1 = new VerticalGroup();
        Slider red1 = new Slider(0, 255, 1, false, skin);
        red1.setValue(player1PieceColor.r * 255);
        Slider green1 = new Slider(0, 255, 1, false, skin);
        green1.setValue(player1PieceColor.g * 255);
        Slider blue1 = new Slider(0, 255, 1, false, skin);
        blue1.setValue(player1PieceColor.b * 255);

        piece1.setColor(red1.getVisualPercent(), green1.getVisualPercent(), blue1.getVisualPercent(), 1.0f);
        player1PieceColor.set(red1.getVisualPercent(), green1.getVisualPercent(), blue1.getVisualPercent(), 1.0f);

        // On change, they will change the piece1 color
        ChangeListener colorsence1 = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                piece1.setColor(red1.getVisualPercent(), green1.getVisualPercent(), blue1.getVisualPercent(), 1.0f);
            }
        };
        red1.addListener(colorsence1);
        green1.addListener(colorsence1);
        blue1.addListener(colorsence1); // give each them that listener

        pickers1.addActor(red1);
        pickers1.addActor(green1);
        pickers1.addActor(blue1); // add them in a column

        pickers1.setPosition(piece1Description.getX() + piece1Description.getWidth() + 94,481);

        piece1.setPosition(pickers1.getX() + 90, 420);

        Label piece2Description = new Label("Player 2 Piece Color", skin,"Child", textColor);
        piece2Description.setPosition(piece1Description.getX(),piece1Description.getY() - 90);

        // This text-field will contain the color of the 2nd player's pieces
        TextField piece2 = new TextField(null,skin);
        piece2.setSize(50, 50);

        // They need to be different in order not to collide with action -- seeking more elegant solution
        VerticalGroup pickers2 = new VerticalGroup();
        Slider red2 = new Slider(0, 255, 1, false, skin);
        red2.setValue(player2PieceColor.r * 255);
        Slider green2 = new Slider(0, 255, 1, false, skin);
        green2.setValue(player2PieceColor.g * 255);
        Slider blue2 = new Slider(0, 255, 1, false, skin);
        blue2.setValue(player2PieceColor.b * 255);

        piece2.setColor(red2.getVisualPercent(), green2.getVisualPercent(), blue2.getVisualPercent(), 1.0f);
        player2PieceColor.set(red2.getVisualPercent(), green2.getVisualPercent(), blue2.getVisualPercent(), 1.0f);

        // This guy does the same with the other text-box
        ChangeListener colorsence2 = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                piece2.setColor(red2.getVisualPercent(), green2.getVisualPercent(), blue2.getVisualPercent(), 1.0f);
            }
        };
        red2.addListener(colorsence2);
        green2.addListener(colorsence2);
        blue2.addListener(colorsence2); // give them all listener

        pickers2.addActor(red2);
        pickers2.addActor(green2);
        pickers2.addActor(blue2); // add them to a group

        pickers2.setPosition(piece2Description.getX() + piece2Description.getWidth() + 90,391);

        piece2.setPosition(pickers2.getX() + 90, 330);

        TextButton closeButton = new TextButton("Close", skin, "TESTING1");
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player1PieceColor.set(red1.getVisualPercent(), green1.getVisualPercent(), blue1.getVisualPercent(), 1.0f);
                player2PieceColor.set(red2.getVisualPercent(), green2.getVisualPercent(), blue2.getVisualPercent(), 1.0f);
                startGame.activateStartScreen(startGame);
            }
        });
        closeButton.setPosition(10,10);

        //We want to change the background when the button is pressed
        TextButton colorButton = new TextButton("Change Game Colour", skin, "TESTING1");
        colorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(SettingsScreen.backgroundColor.equals(new Color(Color.WHITE))){
                    SettingsScreen.textColor.set(Color.NAVY);
                    SettingsScreen.backgroundColor.set(250 / 255f, 255 / 255f, 217 / 255f, 1.0f);
                }
                else if(SettingsScreen.backgroundColor.equals(new Color(250 / 255f, 255 / 255f, 217 / 255f, 1.0f))){
                    SettingsScreen.textColor.set(Color.YELLOW);
                    SettingsScreen.backgroundColor.set(21/255f, 133/255f, 249/255f, 1.0f);
                }
                else if(SettingsScreen.backgroundColor.equals(new Color(21/255f, 133/255f, 249/255f, 1.0f))){
                    SettingsScreen.textColor.set(Color.BLUE);
                    SettingsScreen.backgroundColor.set(255/255f, 53/255f, 36/255f, 1.0f);
                }
                else if(SettingsScreen.backgroundColor.equals(new Color(255/255f, 53/255f, 36/255f, 1.0f))){
                    SettingsScreen.textColor.set(Color.LIME);
                    SettingsScreen.backgroundColor.set(21/255f, 21/255f, 21/255f, 1.0f);
                }
                else{
                    SettingsScreen.textColor.set(Color.BLACK);
                    SettingsScreen.backgroundColor.set(Color.WHITE);
                }
            }
        } );
        colorButton.setPosition(stage.getWidth() - colorButton.getWidth() - 10, 10); //Places the button in the bottom right corner

        TextButton changeSongButton = new TextButton("Change Song", skin, "TESTING1");

        Slider musicVolumeSlider = new Slider(0, 255, 1, false, skin);

        Label musicVolumeDescription = new Label("Volume", skin,"Child", textColor);
        musicVolumeDescription.setPosition(piece1Description.getX(), piece2Description.getY() - 100);
        musicVolumeDescription.setVisible(musicOn);

        TextButton musicButton = new TextButton("Music", skin, "TESTING1");
        if(musicOn){
            musicButton.setColor(Color.GREEN);
        }
        else{
            musicButton.setColor(Color.RED);
        }

        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (musicOn){
                    currentSong.pause();
                    musicButton.setColor(Color.RED);
                }
                else{
                    currentSong.play();
                    musicButton.setColor(Color.GREEN);
                }
                changeSongButton.setVisible(!musicOn);
                musicVolumeSlider.setVisible(!musicOn);
                musicVolumeDescription.setVisible(!musicOn);
                musicOn = !musicOn;
            }
        });

        musicButton.setPosition(closeButton.getX() + closeButton.getWidth() + 20,10);

        changeSongButton.setPosition(musicButton.getX() + musicButton.getWidth() + 20, 10);
        changeSongButton.setVisible(musicOn);

        currentSong.setVolume(musicVolume);
        musicVolumeSlider.setValue(musicVolume * 255);

        ChangeListener volumeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicVolume = musicVolumeSlider.getVisualPercent();
                currentSong.setVolume(musicVolume);
            }
        };
        musicVolumeSlider.addListener(volumeListener);
        musicVolumeSlider.setPosition(musicVolumeDescription.getX() + musicVolumeDescription.getWidth() + 20, musicVolumeDescription.getY());
        musicVolumeSlider.setVisible(musicOn);

        changeSongButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentSongNumber ++;
                currentSong.stop();
                currentSong = songs[currentSongNumber % 10];
                currentSong.play();
                currentSong.setLooping(true);
                currentSong.setVolume(musicVolumeSlider.getVisualPercent());
            }
        });

        stage.addActor(piece1Description);
        stage.addActor(pickers1);
        stage.addActor(piece1);
        stage.addActor(piece2Description);
        stage.addActor(pickers2);
        stage.addActor(piece2);
        stage.addActor(closeButton);
        stage.addActor(colorButton); //Places the button on the screen
        stage.addActor(musicButton);
        stage.addActor(changeSongButton);
        stage.addActor(musicVolumeDescription);
        stage.addActor(musicVolumeSlider);
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
        super.resize(width,height);
        stage.getViewport().update(width, height, true);
    }
}