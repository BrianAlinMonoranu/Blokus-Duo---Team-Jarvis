package Interface;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import Control.Piece;

/*  This will be used to store the functions   */
public interface UI {

    void printBoard();

    void printPieces();

    record Input(int x, int y, Piece piece){

        public int x(){
            return x;
        }

        public int y(){
            return y;
        }

        public Piece piece(){
            return piece;
        }
    }

    String getInput();

    void displayUserTurn();

    void displayHowToPlayGame();

    int coordinateInput(char axis);

    void printWinner();
}
