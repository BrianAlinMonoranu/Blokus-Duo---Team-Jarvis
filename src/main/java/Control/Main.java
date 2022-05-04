package Control;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import Interface.UI;
import Interface.graphical.GUI;
import Interface.graphical.StartGame;
import Interface.graphical.TerminalUI;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static Thread gameControlThread;
    public static BlokusDuo game;
    public static UI ui;
    public static char firstPlayer = 'R';

    // We start the game in here
    public static void main(String[] args) {

        boolean useGUI = false;
        String inputSrc = "console";
        for (String arg : args) {
            if (arg.equals("-gui")) {
                useGUI = true;
            } else if (arg.contains(".txt")) {
                inputSrc = arg;
            } else if (arg.equals("-X")) {
                firstPlayer = 'X';
            } else if (arg.equals("-O")) {
                firstPlayer = 'O';
            } else {
                System.out.println("Unknown argument.");
            }
        }

        if (!useGUI) {
            try {
                ui = new TerminalUI(inputSrc);//Using the terminal
            } catch (FileNotFoundException e) {
                System.out.println("Unable to process file. Switching to manual");
                ui = new TerminalUI();
            }
            game = new BlokusDuo(ui, firstPlayer, false);
            game.consoleRun();
        } else {
            try {
                ui = new GUI();//creating a scanner function basically
                game = new BlokusDuo(ui, firstPlayer, true);

                gameControlThread = new Thread(game);//we create a thread which will start running the game, and it will start with the run method
                gameControlThread.start();

                Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                int height = StartGame.HEIGHT;
                int width = StartGame.WIDTH;
                config.setWindowSizeLimits(width, height, width, height);//sets limit to the window
                new Lwjgl3Application(new StartGame(ui, game), config);//starts the game and displays everything eventually on the screen

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
