package Interface.graphical;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import Control.BlokusDuo;
import Control.Piece;
import Interface.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

/* This class will have the chance to create input and output stream using pipe and will get the game running */
public class GUI implements UI {

    public final PipedOutputStream Output;
    StartGame startGame = null;
    Scanner scanner ;
    public Piece piece;

    private BlokusDuo game;

    //We set up the input
    public GUI() throws IOException{
        this.Output = new PipedOutputStream();
        PipedInputStream input = new PipedInputStream(Output);
        this.scanner = new Scanner(input);
    }

    public void getGame(StartGame startGame) {
        this.startGame = startGame;
    }

    @Override
    public void displayUserTurn() {
        startGame.postRunnable(() -> startGame.activatePlayScreen());
    }

    public void displayHowToPlayGame(){
        startGame.postRunnable(() -> startGame.activateHowToPlayScreen());
    }

    @Override
    public int coordinateInput(char axis) {
        Vector3 world = startGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (axis == 'x') {
            return (int) world.x / startGame.tileWidth - startGame.boardOriginX;
        } else if (axis == 'y') {
            return (int) (world.y / startGame.tileHeight - startGame.boardOffsetY);
        } else {
            return -1;
        }
    }

    @Override
    public void printBoard() {

    }

    @Override
    public void printPieces() {

    }

    /* Takes in the user's inputs and stores them as variables */
    public String getInput() {
        return scanner.next();
    }

    // Prints out the winner by comparing the scores of the currentPlayer and the
    // next player
    public void printWinner() {
        // Checks if the game ended as a draw
        if (game.currentPlayer.points == game.currentPlayer.getNextPlayer().points) {
            System.out.println("Game ended as a draw!");
        }
        // Checks if the current player's score is greater than the other player's score
        else if (game.currentPlayer.points > game.currentPlayer.getNextPlayer().points) {
            System.out.println(game.currentPlayer.name + " is the WINNER!");
        }
        // Else the other player's score is greater than the current player@'s score
        else {
            System.out.println(game.currentPlayer.getNextPlayer().name + " is the WINNER!");
        }
    }
}

