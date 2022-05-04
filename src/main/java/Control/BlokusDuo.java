package Control;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import Interface.UI;
import Interface.graphical.StartGame;
import Interface.graphical.TerminalUI;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* This program/class basically does what the game is meant to do */
public class BlokusDuo implements Runnable {

    public UI ui;
    public char[][] board = new char[14][14];
    public Player player1;
    public Player player2;
    public Player currentPlayer;
    public Piece selectedNow;
    public static char firstPlayer = 'R';
    boolean useGui;
    public int turnCount = 1;
    public StartGame startGame;
    public Queue<int[]> boardUpdate = new LinkedList<>();

    public BlokusDuo(UI ui, char firstPlayerSymbol, boolean useGui) {
        firstPlayer = firstPlayerSymbol;
        this.useGui = useGui;
        this.ui = ui;

        for (char[] row : this.board) {
            Arrays.fill(row, '.');
        }
    }

    public char getSquare(int x, int y) {
        if (x > this.board.length - 1 || x < 0 || y > this.board.length - 1 || y < 0) {
            return '#';
        } else
            return this.board[x][y];
    }

    public boolean setSquare(int x, int y, char newValue) {
        if (x < 0 || x > this.board.length - 1 || y < 0 || y > this.board.length - 1)
            return false;
        else if (getSquare(x, y) == this.player2.symbol || getSquare(x, y) == this.player1.symbol) {
            return false;
        } else {
            this.board[x][y] = newValue;
            if (useGui) boardUpdate.add(new int[] {x, y, newValue});
            return true;
        }
    }

    private void initPlayers(String player1name, String player2name) {
        player1 = new Player(player1name, 'X');
        player2 = new Player(player2name, 'O');
        player1.setNextPlayer(player2);
        player2.setNextPlayer(player1);
        if (firstPlayer == 'R')
            firstPlayer = Math.random() < 0.5 ? 'X' : 'O';
        if (firstPlayer == 'X') {
            this.currentPlayer = this.player1;
            setSquare(4,9,'*');
        } else {
            this.currentPlayer = this.player2;
            setSquare(9,4,'*');
        }
    }

    public void setSelectedNow(String name) {
        this.selectedNow = new Piece(name);
    }

    public int changePlayer() {
        currentPlayer = currentPlayer.getNextPlayer();
        turnCount++;
        if (turnCount == 2 && firstPlayer == 'X') {
            this.setSquare(9, 4, '*');
        } else if (turnCount == 2 && firstPlayer == 'O') {
            this.setSquare(4, 9, '*');
        }

        /* Here we create new potential moves for the current player */
        PotentialMoves move = new PotentialMoves(ui, this);
        move.availableMovePrinter();

        if (currentPlayer.availableLocationsCounter == 0) {
            currentPlayer = currentPlayer.getNextPlayer();
            move = new PotentialMoves(ui, this);
            move.availableMovePrinter();
        }

        return currentPlayer == player1 ? 1 : 2;
    }

    /* We get the names of the player, and we want to display the users turn*/
    @Override
    public void run() {

        String data = ui.getInput();
        switch (data) {
            case "names":
                String player1name = ui.getInput();//Get the name of Player 1
                String player2name = ui.getInput();//Get the name of Player 2
                initPlayers(player1name, player2name);
                ui.displayHowToPlayGame();
                break;
            case "play":
                ui.displayUserTurn();
            case "flip":
                selectedNow.Flip();
                break;
            case "rotate":
                selectedNow.Rotate();
                break;
            case "place":
                int x = Integer.parseInt(ui.getInput());
                int y = Integer.parseInt(ui.getInput());
                this.placeMove(x, y);
                break;
            case "drop":
                selectedNow = null;
                break;
            default:
                System.out.println("Incorrect codeword.");
                break;
        }
    }

    void consoleRun() {
        initPlayers(((TerminalUI) ui).getName(1), ((TerminalUI) ui).getName(2));
        ui.displayHowToPlayGame();//Display the rules of the game

        while (!(currentPlayer.availableLocationsCounter == 0 &&
                currentPlayer.getNextPlayer().availableLocationsCounter == 0)) {
            ui.printBoard();
            ui.displayUserTurn();

            UI.Input input;
            do {
                input = ((TerminalUI) ui).getMove(currentPlayer);
                if (isPositionBlocked(input, true)) {
                    System.out.println("Invalid move!\n");
                }
            } while (isPositionBlocked(input, true));

            selectedNow = input.piece();
            placeMove(input.x(), input.y());

            changePlayer();
        }
        ui.printBoard();
        ui.printPieces();
        Score.pointCounter(player1);
        System.out.println(player1.name + "'s score = " + player1.points);
        Score.pointCounter(player2);
        System.out.println(player2.name + "'s score = " + player2.points);
        ui.printWinner();
    }

    public boolean isPositionBlocked(int x, int y) {
        return isPositionBlocked(new UI.Input(x, y, selectedNow), true);
    }
    /* Checks if the chosen coordinate is a valid spot */
    public boolean isPositionBlocked(UI.Input move, boolean placeMode) {
        Piece shape = move.piece();

        // if there is any friendly block on the diagonal position
        boolean anyDiagonalFriend = (this.getSquare(move.x(), move.y()) == '*');

        //Runs only when the method is called in the BlokusDuo class
        if(placeMode){
            int x = move.x();
            int y = move.y();
            for (int i = 0; i < shape.sequence.length; i += 2) {
                x = x + shape.sequence[i];
                y = y + shape.sequence[i + 1];

                if (x < 0 || x > 13 || y < 0 || y > 13) {
                    return true;
                }
                // Checking around the current input to see if there is any other piece placed
                if ((this.getSquare(x + 1, y) == this.currentPlayer.symbol) ||
                        (this.getSquare(x - 1, y) == this.currentPlayer.symbol) ||
                        (this.getSquare(x, y + 1) == this.currentPlayer.symbol) ||
                        (this.getSquare(x, y - 1) == this.currentPlayer.symbol)){
                    return true;
                }

                // Check if there is a friendly block next to you diagonally around the current
                if ((this.getSquare(x + 1, y + 1) == this.currentPlayer.symbol) ||
                        (this.getSquare(x + 1, y - 1) == this.currentPlayer.symbol) ||
                        (this.getSquare(x - 1, y + 1) == this.currentPlayer.symbol) ||
                        (this.getSquare(x - 1, y - 1) == this.currentPlayer.symbol)){
                    anyDiagonalFriend = true;
                }

                // Check if this square is already taken
                if (this.getSquare(x, y) == this.currentPlayer.getNextPlayer().symbol){
                    return true;
                }
                if (this.getSquare(x, y) == '*'){
                    anyDiagonalFriend = true;
                }
            }
            return !anyDiagonalFriend;
        }
        //Runs only when the method is called in the PotentialMoves class
        else{
            boolean currentRotationCannotBePlaced;

            //Loops through the whole sequence of the piece to see if the piece can be placed at any of the positions that the piece covers
            for(int j = 0; j < shape.sequence.length;j += 2){
                //Only runs if it's the first loop call or the sequence has useful data
                if(j == 0 || shape.sequence[j] != 0 || shape.sequence[j + 1] != 0){
                    //Only runs if j is smaller than the total length of the sequence by at least 4
                    if(j < shape.sequence.length - 4){
                        //Only runs at the first loop call or if the current value at index j or j + 1 is different from the
                        //value found at index j + 2 or j + 3 respectively.
                        //This means that if the piece has an up() and then a down() call, it won't check the piece at the up()
                        //method, but it will check it at the down() method. This allows the code to not check the same
                        //position multiple times unnecessarily.
                        if(j == 0 || shape.sequence[j] != -shape.sequence[j + 2] || shape.sequence[j + 1] != -shape.sequence[j + 3]){
                            //Reinitialise all the variables with information on the new piece
                            currentRotationCannotBePlaced = false;
                            int x = move.x() + shape.sequence[j];
                            int y = move.y() + shape.sequence[j + 1];
                            anyDiagonalFriend = (this.getSquare(x, y) == '*');

                            for (int i = 0; i < shape.sequence.length; i += 2) {
                                x = x + shape.sequence[i];
                                y = y + shape.sequence[i + 1];

                                if (x < 0 || x > 13 || y < 0 || y > 13) {
                                    currentRotationCannotBePlaced = true;
                                    break;
                                }
                                // Checking around the current input to see if there is any other piece placed
                                if ((this.getSquare(x + 1, y) == this.currentPlayer.symbol) ||
                                        (this.getSquare(x - 1, y) == this.currentPlayer.symbol) ||
                                        (this.getSquare(x, y + 1) == this.currentPlayer.symbol) ||
                                        (this.getSquare(x, y - 1) == this.currentPlayer.symbol)){
                                    currentRotationCannotBePlaced = true;
                                    break;
                                }

                                // Check if there is a friendly block next to you diagonally around the current
                                if ((this.getSquare(x + 1, y + 1) == this.currentPlayer.symbol) ||
                                        (this.getSquare(x + 1, y - 1) == this.currentPlayer.symbol) ||
                                        (this.getSquare(x - 1, y + 1) == this.currentPlayer.symbol) ||
                                        (this.getSquare(x - 1, y - 1) == this.currentPlayer.symbol)){
                                    anyDiagonalFriend = true;
                                }

                                // Check if this square is already taken
                                if (this.getSquare(x, y) == this.currentPlayer.getNextPlayer().symbol){
                                    currentRotationCannotBePlaced = true;
                                    break;
                                }
                                if (this.getSquare(x, y) == '*'){
                                    anyDiagonalFriend = true;
                                }
                            }
                            if(!currentRotationCannotBePlaced){
                                return !anyDiagonalFriend;
                            }
                        }
                    }
                    //else call of the if statement for difference between j and sequence length is greater than 4
                    else{
                        currentRotationCannotBePlaced = false;
                        int x = move.x() + shape.sequence[j];
                        int y = move.y() + shape.sequence[j + 1];
                        anyDiagonalFriend = (this.getSquare(x, y) == '*');

                        for (int i = 0; i < shape.sequence.length; i += 2) {
                            x = x + shape.sequence[i];
                            y = y + shape.sequence[i + 1];

                            if (x < 0 || x > 13 || y < 0 || y > 13) {
                                currentRotationCannotBePlaced = true;
                                break;
                            }
                            // Checking around the current input to see if there is any other piece placed
                            if ((this.getSquare(x + 1, y) == this.currentPlayer.symbol) ||
                                    (this.getSquare(x - 1, y) == this.currentPlayer.symbol) ||
                                    (this.getSquare(x, y + 1) == this.currentPlayer.symbol) ||
                                    (this.getSquare(x, y - 1) == this.currentPlayer.symbol)){
                                currentRotationCannotBePlaced = true;
                                break;
                            }

                            // Check if there is a friendly block next to you diagonally around the current
                            if ((this.getSquare(x + 1, y + 1) == this.currentPlayer.symbol) ||
                                    (this.getSquare(x + 1, y - 1) == this.currentPlayer.symbol) ||
                                    (this.getSquare(x - 1, y + 1) == this.currentPlayer.symbol) ||
                                    (this.getSquare(x - 1, y - 1) == this.currentPlayer.symbol)){
                                anyDiagonalFriend = true;
                            }

                            // Check if this square is already taken
                            if (this.getSquare(x, y) == this.currentPlayer.getNextPlayer().symbol){
                                currentRotationCannotBePlaced = true;
                                break;
                            }
                            if (this.getSquare(x, y) == '*'){
                                anyDiagonalFriend = true;
                            }
                        }
                        if(!currentRotationCannotBePlaced){
                            return !anyDiagonalFriend;
                        }
                    }
                }
            }
            return true;
        }
    }

    /* Places the piece on the board */
    public void placeMove(int x, int y) {
        this.setSquare(x, y, this.currentPlayer.symbol);

        for (int i = 0; i < selectedNow.sequence.length; i += 2) {
            x = x + selectedNow.sequence[i];
            y = y + selectedNow.sequence[i + 1];

            this.setSquare(x, y, this.currentPlayer.symbol);
        }
        this.currentPlayer.piecesAvailable.removeIf(shape -> shape.getType().equals(selectedNow.getType()));
        this.currentPlayer.lastPlaced = selectedNow.getType();
        selectedNow = null;
    }
}