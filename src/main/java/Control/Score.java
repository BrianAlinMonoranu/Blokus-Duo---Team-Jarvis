package Control;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

public abstract class Score {

    // Takes in src.main.java.Control.Player as input and calculates the corresponding player's score
    public static void pointCounter(Player currPlayer) {
        for (Piece shape : currPlayer.piecesAvailable) {
            currPlayer.points -= shape.getValue();
        }

        if (currPlayer.piecesAvailable.size() == 0) {
            currPlayer.points = 15;
        }

        if (currPlayer.lastPlaced.equals("I1")) {
            currPlayer.points += 5;
        }
    }
}