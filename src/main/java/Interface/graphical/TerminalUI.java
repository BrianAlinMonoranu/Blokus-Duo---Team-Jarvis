package Interface.graphical;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import Control.*;
import Interface.UI;

import java.io.*;
import java.util.Scanner;

import static Control.Main.game;

public class TerminalUI implements UI {

    PrintStream output = System.out;
    Scanner scanner;

    public char[][] display_piece_board = new char[11][11];//This will display the current piece

    public TerminalUI(String sampleFileName) throws FileNotFoundException {
        this.scanner = new Scanner(new File(sampleFileName));
    }

    public TerminalUI() {
        this.scanner = new Scanner(System.in);
    }

    public String getName(int playerNumber) {
        output.print("Enter the name of Player " + playerNumber + ": ");
        String player_name = scanner.nextLine();
        while (!(player_name.matches("[a-zA-Z]+"))) {
            output.print("Names must contain letters only, try again: ");
            player_name = scanner.nextLine();
        }
        return player_name;
    }

    public void displayUserTurn() {
        output.println("\nTurn number: " + game.turnCount);
        output.println("-------------------------");
        this.printPieces();
        output.println("-------------------------");
        output.println("Number of available locations: " + game.currentPlayer.availableLocationsCounter);
        output.println("\nPlayer " + game.currentPlayer.name + "'s turn");
    }

    @Override
    public void displayHowToPlayGame() {
        String howToPlay =
                """
                        \n
                        Select the piece you would like to place
                        Press f on your keyboard to flip the piece
                        Press r to rotate the piece and p to lock in the orientation
                        Finally type the coordinates on the board where you would like to place the piece
                        The game won't let you place the piece illegally
                        """;

        String scoring =
                """
                        Each player begins with 0 points
                        At the end of the game if the last piece placed by the player is i1
                        they receive 5 points
                        If the player places all their pieces they receive 15 points
                        Otherwise the value of each piece is deducted from the players points
                        The value of pieces is calculated based on the number of squares it covers
                        \n
                        
                        """;

        output.println(howToPlay);
        output.println();
        output.println(scoring);
    }

    /* Takes in the user's inputs and stores them as variables */
    public String getInput() {
        return scanner.next();
    }

    public Input getMove(Player currentPlayer) {
        output.print("Enter the piece you would like to play: ");
        String input = this.getInput();

        while (currentPlayer.isPieceUsed(input)) {
            output.print("Invalid piece, try again: ");
            input = this.getInput();
        }

        Piece piece = new Piece(input);

        this.displayPiece(piece);

        do {
            output.print("Enter 'r' to rotate, 'f' to flip, or 'p' to place the game piece: ");
            input = this.getInput();
            switch (input) {
                case "r" -> {
                    piece.Rotate();
                    this.displayPiece(piece);
                }
                case "f" -> {
                    piece.Flip();
                    this.displayPiece(piece);
                }
            }
        } while (!(input.equals("p")));

        int x = coordinateInput('x');
        int y = coordinateInput('y');
        output.println();

        return new UI.Input(x, y, piece);
    }

    /*
     * Here we use this method to check if the coordinates given are integer and if
     * not we ask for input again
     */
    public int coordinateInput(char axis) {

        if (axis != 'b') {
            int coordinate;
            output.print("Enter the coordinate " + axis + ": ");
            do {
                while (!scanner.hasNextInt()) {
                    output.print(axis + " must be an integer, please try again: ");
                    scanner.next();
                }
                coordinate = scanner.nextInt();
                if (coordinate < 0 || coordinate > 13) {
                    output.print(axis + " must be between 0 and 13 inclusive, please try again: ");
                }
            } while (coordinate < 0 || coordinate > 13);

            return coordinate;
        }
        return -1;
    }

    public void displayPiece(Piece shape) {

        for (int i = 0; i < display_piece_board.length; i++) {
            for (int j = 0; j < display_piece_board.length; j++) {
                display_piece_board[i][j] = '-';
            }
        }

        int x = 4;
        int y = 5;

        output.println("CURRENT PIECE: ");

        display_piece_board[x][y] = game.currentPlayer.symbol;

        for (int i = 0; i < shape.sequence.length; i++) {
            int j = shape.sequence[i];

            if (i % 2 == 1) {
                x = x - j;
            } else {
                y = y + j;
            }
            display_piece_board[x][y] = game.currentPlayer.symbol;
        }
        for (int i = 0; i < display_piece_board.length - 2; i++) {
            for (int j = 0; j < display_piece_board.length; j++) {
                output.printf(" %s ", display_piece_board[i][j]);
            }
            output.println();
        }

    }

    // Prints out the winner by comparing the scores of the currentPlayer and the next player
    public void printWinner() {
        // Checks if the game ended as a draw
        if (game.currentPlayer.points == game.currentPlayer.getNextPlayer().points) {
            output.println("Game ended as a draw!");
        }
        // Checks if the current player's score is greater than the other player's score
        else if (game.currentPlayer.points > game.currentPlayer.getNextPlayer().points) {
            output.println(game.currentPlayer.name + " is the WINNER!");
        }
        // Else the other player's score is greater than the current player@'s score
        else {
            output.println(game.currentPlayer.getNextPlayer().name + " is the WINNER!");
        }
    }

    /* Function to print out the board */
    public void printBoard() {
        // Printing out the board itself that include the '.' and '*'
        for (int y = game.board.length - 1; y >= 0; y--) {
            output.printf("%2d ", y);
            for (int x = 0; x < game.board[0].length; x++) {
                output.print(" " + game.getSquare(x, y) + " ");
            }
            output.println();
        }

        // Printing out the numbers for the board x-axis
        output.print("x> ");
        for (int x = 0; x < game.board.length; x++) {
            output.printf("%2d ", x);
        }
        output.println();
    }

    /* We print out the available pieces for both players */
    public void printPieces() {
        output.print("Player " + game.player1.name + " (" + game.player1.symbol + ") available pieces:");

        // Prints everything found in the piecesAvailable arrayList by printing the type
        // of the piece
        for (Piece shape : game.player1.piecesAvailable) {
            output.print(" " + shape.getType());
        }

        output.print("\nPlayer " + game.player2.name + " (" + game.player2.symbol + ") available pieces:");

        for (Piece shape : game.player2.piecesAvailable) {
            output.print(" " + shape.getType());
        }
        System.out.println();
    }
}