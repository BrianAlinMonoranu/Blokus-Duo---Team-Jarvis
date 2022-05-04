////package test;
//
//import org.junit.jupiter.api.Test;
//import Control.Blokus_duo;
//import Control.Piece;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class GameTest {
//
//    static Blokus_duo Xfirst;
//    static {
//        try {
//            Xfirst = new Blokus_duo('X',new Scanner(new File("assets/inpsample.txt")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    static Blokus_duo Ofirst;
//    static {
//        try {
//            Ofirst = new Blokus_duo('O',new Scanner(new File("assets/inpsample.txt")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    static Blokus_duo random;
//
//    static {
//        try {
//            random = new Blokus_duo('R',new Scanner(new File("assets/inpsample.txt")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void IsTheBoardEmptyAtTheStartPlayer_1(){
//
//        for(int i = 0; i < Xfirst.board.length; i++){
//            for(int j = 0; j < Xfirst.board.length; j++){
//
//                if(i == 4 && j == 9){
//                    assertEquals('*', Xfirst.board[i][j]);
//                }
//                else {
//                    assertEquals('.', Xfirst.board[i][j]);
//                }
//            }
//        }
//    }
//
//    @Test
//    public void IsTheBoardEmptyAtTheStartPlayer_2(){
//
//        for(int i =0; i < Ofirst.board.length; i++){
//            for(int j =0; j < Ofirst.board.length; j++){
//
//                if(i == 9 && j == 4){
//                    assertEquals('*', Ofirst.board[i][j]);
//                }
//                else {
//                    assertEquals('.', Ofirst.board[i][j]);
//                }
//            }
//        }
//    }
//
//    @Test
//    public void PlayerNameNotEmpty(){
//        assertNotEquals("", Xfirst.player_1.name);
//        assertNotEquals("" , Xfirst.player_2.name);
//    }
//
//
//
//    @Test
//    public void BoardAccessX() {
//
//        for (int x = 0; x < Xfirst.board.length; x++) {
//            for (int y = 0; y < Xfirst.board.length; y++) {
//                if (x == 4 && y == 9)
//                    assertEquals('*', Xfirst.getSquare(x,y));
//                else
//                    assertEquals('.', Xfirst.getSquare(x,y));
//            }
//        }
//    }
//
//    @Test
//    public void BoardAccessO() {
//
//        for (int x = 0; x < Ofirst.board.length; x++) {
//            for (int y = 0; y < Ofirst.board.length; y++) {
//                if (x == 9 && y == 4)
//                    assertEquals('*', Ofirst.getSquare(x,y));
//                else
//                    assertEquals('.', Ofirst.getSquare(x,y));
//            }
//        }
//    }
//
//    @Test
//    public void BoardEdit() {
//
//        random.setSquare(5, 5, '&');
//        random.setSquare(7, 3, 'G');
//        assertEquals('&', random.getSquare(5, 5));
//        assertEquals('G', random.getSquare(7,3));
//    }
//
//    @Test
//    public void pieceI1() {
//
//        char[] FunctionArray = new char[4];
//        char[] ManuallyArray = new char[4];
//
//        for (int i = 0; i < 4; i++) {
//            FunctionArray[i] = '-';
//            ManuallyArray[i] = '-';
//        }
//
//        Piece piece = new Piece("I1");
//
//        int x = 2;
//        int y = 2;
//
//        ManuallyArray[2] = '*';
//        for (int i = 0; i < piece.sequence.length; i++) {
//            int j = piece.sequence[i];
//            if (i % 2 == 0) {
//                y = y + j;
//            } else {
//                x = x + j;
//                FunctionArray[2] = '*';
//            }
//
//        }
//        assertEquals(ManuallyArray[2],FunctionArray[2]);
//    }
//
//
//    //TODO: create pieces and compare
//    //TODO: rotation and flip
//    //TODO: get next player
//    //TODO:
//}
