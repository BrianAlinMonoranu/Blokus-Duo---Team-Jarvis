package Control;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import java.util.ArrayList;

public class Player {

    public final String name;
    public int points;
    public String lastPlaced = "";
    public int availableLocationsCounter = 1;

    private Player nextPlayer;

    /*
     * Here we insert the pieces the current Player should have , basically all the
     * pieces
     */
    public ArrayList<Piece> piecesAvailable = new ArrayList<>();
    public final char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        points = 0;

        piecesAvailable.add(new Piece("I1"));
        piecesAvailable.add(new Piece("I2"));
        piecesAvailable.add(new Piece("I3"));
        piecesAvailable.add(new Piece("I4"));
        piecesAvailable.add(new Piece("I5"));
        piecesAvailable.add(new Piece("V3"));
        piecesAvailable.add(new Piece("L4"));
        piecesAvailable.add(new Piece("Z4"));
        piecesAvailable.add(new Piece("Z5"));
        piecesAvailable.add(new Piece("O4"));
        piecesAvailable.add(new Piece("L5"));
        piecesAvailable.add(new Piece("T4"));
        piecesAvailable.add(new Piece("T5"));
        piecesAvailable.add(new Piece("V5"));
        piecesAvailable.add(new Piece("N"));
        piecesAvailable.add(new Piece("W"));
        piecesAvailable.add(new Piece("U"));
        piecesAvailable.add(new Piece("P"));
        piecesAvailable.add(new Piece("F"));
        piecesAvailable.add(new Piece("X"));
        piecesAvailable.add(new Piece("Y"));
    }

    /* We use this to get the next player when playing the game */
    public Player getNextPlayer() {
        return nextPlayer;
    }

    /* Here we are setting our next player */
    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    /* Checks if the chosen piece is available for the current user */
    public boolean isPieceUsed(String type) {
        for (Piece shape : this.piecesAvailable) {
            if (type.equals(shape.getType())) {
                return false;
            }
        }
        return true;
    }
}