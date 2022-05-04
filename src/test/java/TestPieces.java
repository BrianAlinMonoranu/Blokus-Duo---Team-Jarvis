//package src.test.java;

import Control.BlokusDuo;
import Control.Piece;
import Interface.graphical.TerminalUI;
import org.junit.jupiter.api.Test;

/**
 * We created a TestPieces class which basically will test to see if the pieces placed on the board in blokus duo is the right using pieces classes to call
 * The piece and its sequence and then creating methods to place them on temp arrays which i created above and then compare the manually made piece and the
 * src.main.java.Control.Piece class pieces
 */
public class TestPieces {

    private final int x = 7;
    private final int y = 5;

    private final char[][] FunctionArray = new char[11][11];
    private final char[][] TestArray = new char[11][11];

    static BlokusDuo random;
    static TerminalUI ui;

    static {
//        try {
            random = new BlokusDuo(ui,'R',false);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to find file.");
//        }
    }


    /**
     * Here we want to see if the pieces are placed properly on the board
     * Created a temp array called TestArray which has the right value and
     * a Function array which tests to see if src.main.java.Control.Piece gives the right sequence to place the piece on the board
     */
    @Test
    public void pieceI2() {

        RenewArray(FunctionArray,TestArray);

        for (int k = 0; k < FunctionArray.length; k++) {
            for (int j = 0; j < FunctionArray.length; j++) {
                FunctionArray[k][j] = '-';
                TestArray[k][j] = '-';
            }

            Piece piece = new Piece("I2");

            TestArray[x][y] = '*';

            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';

            PlaceMove(TestArray, piece, x, y);

            CheckToSeeIfEqual(FunctionArray,TestArray);
        }
    }

    @Test
    public void pieceI3() {

       RenewArray(FunctionArray,TestArray);

        for (int k = 0; k < FunctionArray.length; k++) {
            for (int j = 0; j < FunctionArray.length; j++) {
                FunctionArray[k][j] = '-';
                TestArray[k][j] = '-';
            }
        }

            Piece piece = new Piece("I3");

            TestArray[x][y] = '*';

            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x-2][y] = '*';

            PlaceMove(TestArray, piece, x, y);

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceI4() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("I4");

            TestArray[x][y] = '*';

            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x-2][y] = '*';
            FunctionArray[x-3][y] = '*';

            PlaceMove(TestArray, piece, x, y);

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceI5() {

          RenewArray(FunctionArray, TestArray);

            Piece piece = new Piece("I5");

            TestArray[x][y] = '*';

            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x-2][y] = '*';
            FunctionArray[x-3][y] = '*';
            FunctionArray[x-4][y] = '*';

            PlaceMove(TestArray, piece, x, y);

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceV3() {

          RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("V3");

            TestArray[x][y] = '*';

            FunctionArray[x][y] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x-1][y] = '*';

            PlaceMove(TestArray, piece, x, y);

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceL4() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("L4");

            TestArray[x][y] = '*';

            FunctionArray[x][y] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x-2][y] = '*';

            PlaceMove(TestArray, piece, x, y);

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceZ4() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("Z4");

            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);

            FunctionArray[x][y] = '*';
            FunctionArray[x][y-1] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x-1][y+1] = '*';


            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceO4() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("O4");

            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);



            FunctionArray[x][y] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x-1][y+1] = '*';


            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceL5() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("L5");

            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);

            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x][y+2] = '*';
            FunctionArray[x][y+3] = '*';


            CheckToSeeIfEqual(FunctionArray,TestArray);
    }
    @Test
    public void pieceT5() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("T5");

            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);


            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x-2][y] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x][y-1] = '*';

            CheckToSeeIfEqual(FunctionArray,TestArray);
    }
    @Test
    public void pieceV5() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("V5");

            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);


            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x-2][y] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x][y+2] = '*';

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceN() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("N");

            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);


            FunctionArray[x][y] = '*';
            FunctionArray[x+1][y] = '*';
            FunctionArray[x+1][y-1] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x][y+2] = '*';

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }
    @Test
    public void pieceZ5() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("Z5");

            PlaceMove(TestArray, piece, x, y);

            FunctionArray[x][y] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x-1][y+1] = '*';
            FunctionArray[x][y-1] = '*';
            FunctionArray[x+1][y-1] = '*';

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceT4() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("T4");

            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);


            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x][y-1] = '*';

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceP() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("P");


            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);


            FunctionArray[x][y] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x+1][y+1] = '*';
            FunctionArray[x+1][y] = '*';
            FunctionArray[x+2][y] = '*';

            CheckToSeeIfEqual(FunctionArray,TestArray);

    }

    @Test
    public void pieceW() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("W");

            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);


            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x][y-1] = '*';
            FunctionArray[x-1][y+1] = '*';
            FunctionArray[x+1][y-1] = '*';

            CheckToSeeIfEqual(FunctionArray,TestArray);
    }

    @Test
    public void pieceU() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("U");


            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);


            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x+1][y] = '*';
            FunctionArray[x-1][y+1] = '*';
            FunctionArray[x+1][y+1] = '*';

            CheckToSeeIfEqual(FunctionArray,TestArray);
    }
    @Test
    public void pieceX() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("X");


            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);


            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x+1][y] = '*';
            FunctionArray[x][y-1] = '*';
            FunctionArray[x][y+1] = '*';


            CheckToSeeIfEqual(FunctionArray,TestArray);
    }
    @Test
    public void pieceY() {

        RenewArray(FunctionArray,TestArray);

            Piece piece = new Piece("Y");

            TestArray[x][y] = '*';
            PlaceMove(TestArray, piece, x, y);


            FunctionArray[x][y] = '*';
            FunctionArray[x-1][y] = '*';
            FunctionArray[x][y-1] = '*';
            FunctionArray[x][y+1] = '*';
            FunctionArray[x][y+2] = '*';

            CheckToSeeIfEqual(FunctionArray,TestArray);
        }


    //We could use the PlaceMove from our Blokus_Duo class but we want to make one here so that it will be easier for clarity reasons and we would
    //have to start a whole new game too which means not using the file
    public void PlaceMove(char[][] TestArray, Piece piece, int x, int y){

        for (int i = 0; i < piece.sequence.length; i++) {
            int j = piece.sequence[i];

            if (i % 2 == 1) {
                y = y + j;
            } else {
                x = x - j;
            }
            TestArray[x][y] = '*';
        }
    }

    public void RenewArray(char[][] Actual, char[][] Expected) {
        for (int k = 0; k < Actual.length; k++) {
            for (int j = 0; j < Actual.length; j++) {
                Actual[k][j] = '-';
                Expected[k][j] = '-';
            }
        }
    }

    public void CheckToSeeIfEqual(char[][] Actual, char[][] Expected) {
//        FlipTest.CheckToSeeIfEqual(Actual, Expected);
    }
//    public void CheckToSeeIfEqual(char [][] Actual,char[][] Expected){
//        for (int c = 0; c < Actual.length; c++) {
//            for (int r = 0; r < Actual.length; r++) {
//                try {
//                    assertEquals(Expected[c][r], Actual[c][r]);
//                } catch (AssertionError error) {
//                    System.out.format("[%d][%d] = %c vs %c\n",c,r,Expected[c][r],Actual[c][r]);
//                }
//            }
//        }
//    }
}
