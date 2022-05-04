package Control;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import Interface.UI;
import Interface.graphical.TerminalUI;

public class PotentialMoves {
    
    final UI ui;
    BlokusDuo game;

    public PotentialMoves(UI ui, BlokusDuo game) {
        this.game = game;
        this.ui = ui;
    }

    /* Removes all instances of * from the board */
    public void boardCleaner() {
        for (int y = game.board.length - 1; y >= 0; y--) {
            for (int x = 0; x < game.board[0].length; x++) {
                if (game.getSquare(x, y) == '*') {
                    game.setSquare(x, y, '.');
                }
            }
        }
        game.currentPlayer.availableLocationsCounter = 0;
    }

    /* We check to see the moves available */
    public void availableMovePrinter() {
        // This function only runs if each player has made at least 1 move
        if (!(game.getSquare(9, 4) == '*' || game.getSquare(4, 9) == '*')) {
            boardCleaner();
            boolean topLeftTaken;
            boolean topRightTaken;
            boolean bottomLeftTaken;
            boolean bottomRightTaken;
            boolean current;
            boolean beside;

            for (int y = 0; y < game.board.length; y++) {
                for (int x = 0; x < game.board[0].length; x++) {

                    current = (game.getSquare(x, y) == game.currentPlayer.symbol);

                    if (x == 13 && y == 13 || x == 13 && y == 0 || x == 0 && y == 13 || x == 0 && y == 0) {
                        topLeftTaken = true;
                        topRightTaken = true;
                        bottomLeftTaken = true;
                        bottomRightTaken = true;
                    } else if (x == 13) {
                        topLeftTaken = game.getSquare(x - 1, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y + 1) == game.currentPlayer.getNextPlayer().symbol;
                        bottomLeftTaken = game.getSquare(x - 1, y - 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y - 1) == game.currentPlayer.getNextPlayer().symbol;
                        topRightTaken = true;
                        bottomRightTaken = true;
                    } else if (y == 13) {
                        bottomLeftTaken = game.getSquare(x - 1, y - 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y - 1) == game.currentPlayer.getNextPlayer().symbol;
                        bottomRightTaken = game.getSquare(x + 1, y - 1) == game.currentPlayer.symbol
                                || game.getSquare(x + 1, y - 1) == game.currentPlayer.getNextPlayer().symbol;
                        topLeftTaken = true;
                        topRightTaken = true;
                    } else if (x == 0) {
                        topRightTaken = game.getSquare(x + 1, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x + 1, y + 1) == game.currentPlayer.getNextPlayer().symbol;
                        bottomRightTaken = game.getSquare(x + 1, y - 1) == game.currentPlayer.symbol
                                || game.getSquare(x + 1, y - 1) == game.currentPlayer.getNextPlayer().symbol;
                        topLeftTaken = true;
                        bottomLeftTaken = true;
                    } else if (y == 0) {
                        topLeftTaken = game.getSquare(x - 1, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y + 1) == game.currentPlayer.getNextPlayer().symbol;
                        topRightTaken = game.getSquare(x + 1, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x + 1, y + 1) == game.currentPlayer.getNextPlayer().symbol;
                        bottomLeftTaken = true;
                        bottomRightTaken = true;
                    } else {
                        topLeftTaken = game.getSquare(x - 1, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y + 1) == game.currentPlayer.getNextPlayer().symbol;
                        topRightTaken = game.getSquare(x + 1, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x + 1, y + 1) == game.currentPlayer.getNextPlayer().symbol;
                        bottomLeftTaken = game.getSquare(x - 1, y - 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y - 1) == game.currentPlayer.getNextPlayer().symbol;
                        bottomRightTaken = game.getSquare(x + 1, y - 1) == game.currentPlayer.symbol
                                || game.getSquare(x + 1, y - 1) == game.currentPlayer.getNextPlayer().symbol;
                    }

                    if (current && !topLeftTaken) {
                        game.setSquare(x - 1, y + 1, '*');
                    }
                    if (current && !topRightTaken) {
                        game.setSquare(x + 1, y + 1, '*');
                    }
                    if (current && !bottomLeftTaken) {
                        game.setSquare(x - 1, y - 1, '*');
                    }
                    if (current && !bottomRightTaken) {
                        game.setSquare(x + 1, y - 1, '*');
                    }
                }
            }

            for (int y = 0; y < game.board.length; y++) {
                for (int x = 0; x < game.board[0].length; x++) {
                    if (x == 0 && y == 0) {
                        beside = game.getSquare(x, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x + 1, y) == game.currentPlayer.symbol;
                    } else if (x == 0 && y == 13) {
                        beside = game.getSquare(x, y - 1) == game.currentPlayer.symbol
                                || game.getSquare(x + 1, y) == game.currentPlayer.symbol;
                    } else if (x == 13 && y == 0) {
                        beside = game.getSquare(x, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y) == game.currentPlayer.symbol;
                    } else if (x == 13 && y == 13) {
                        beside = game.getSquare(x, y - 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y) == game.currentPlayer.symbol;
                    } else if (y == 0) {
                        beside = game.getSquare(x + 1, y) == game.currentPlayer.symbol
                                || game.getSquare(x, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y) == game.currentPlayer.symbol;
                    } else if (y == 13) {
                        beside = game.getSquare(x + 1, y) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y) == game.currentPlayer.symbol
                                || game.getSquare(x, y - 1) == game.currentPlayer.symbol;
                    } else if (x == 0) {
                        beside = game.getSquare(x + 1, y) == game.currentPlayer.symbol
                                || game.getSquare(x, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x, y - 1) == game.currentPlayer.symbol;
                    } else if (x == 13) {
                        beside = game.getSquare(x, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y) == game.currentPlayer.symbol
                                || game.getSquare(x, y - 1) == game.currentPlayer.symbol;
                    } else {
                        beside = game.getSquare(x + 1, y) == game.currentPlayer.symbol
                                || game.getSquare(x, y + 1) == game.currentPlayer.symbol
                                || game.getSquare(x - 1, y) == game.currentPlayer.symbol
                                || game.getSquare(x, y - 1) == game.currentPlayer.symbol;
                    }

                    if (game.getSquare(x, y) == '*' && beside) {
                        game.setSquare(x, y, '.');
                    }

                    else if(game.getSquare(x, y) == '*' && !aPieceCanBePlaced(x, y)){
                        game.setSquare(x, y, '.');
                    }

                    if (game.getSquare(x, y) == '*') {
                        game.currentPlayer.availableLocationsCounter++;
                    }
                }
            }
        }
    }

    public boolean aPieceCanBePlaced(int x, int y) {
        UI.Input move;

        for (Piece shape : game.currentPlayer.piecesAvailable) {
            move = new UI.Input(x, y, shape);

            if (!game.isPositionBlocked(move, false)) {
                return true;
            } else {
                for (int i = 0; i < 4; i++) {
                    shape.Rotate();

                    move = new UI.Input(x, y, shape);

                    if (!game.isPositionBlocked(move, false)) {
                        return true;
                    }
                }
                shape.Flip();

                move = new UI.Input(x, y, shape);

                if (!game.isPositionBlocked(move, false)) {
                    return true;
                } else {
                    for (int i = 0; i < 3; i++) {
                        shape.Rotate();

                        move = new UI.Input(x, y, shape);

                        if (!game.isPositionBlocked(move, false)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}