//package test;

import Control.BlokusDuo;
import Control.Piece;
import Interface.graphical.TerminalUI;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotateTest {

    private final int x = 7;
    private final int y = 5;

    private final char[][] FunctionArray = new char[11][11];
    private final char[][] TestArray = new char[11][11];

    static BlokusDuo random;

    static {
        try {
            TerminalUI ui = new TerminalUI("sample.txt");
            random = new BlokusDuo(ui, 'R', false);//new Scanner(new File("sample.txt")));
        } catch (FileNotFoundException e) {
            TerminalUI ui = new TerminalUI();
            System.out.println("Unable to find file.");
            random = new BlokusDuo(ui, 'R', false);
        }
    }

    @Test
    public void Rotate90Degrees(){

         char[][] FunctionArray = new char[11][11];
         char[][] TestArray = new char[11][11];

        Piece piece = new Piece("I2");

        piece.Rotate();

        PlaceMove(TestArray, piece, x, y);

        FunctionArray[x][y] = '*';
        FunctionArray[x][y+1] = '*';

        CheckToSeeIfEqual(FunctionArray, TestArray);

    }

    @Test
    public void Rotate180Degrees(){

        char[][] FunctionArray = new char[11][11];
        char[][] TestArray = new char[11][11];

        Piece piece = new Piece("I2");

        piece.Rotate();
        piece.Rotate();

        PlaceMove(TestArray, piece, x, y);

        FunctionArray[x][y] = '*';
        FunctionArray[x+1][y] = '*';

        CheckToSeeIfEqual(FunctionArray, TestArray);

    }

    @Test
    public void Rotate270Degrees(){

        char[][] FunctionArray = new char[11][11];
        char[][] TestArray = new char[11][11];

        Piece piece = new Piece("I2");

        piece.Rotate();
        piece.Rotate();
        piece.Rotate();

        PlaceMove(TestArray, piece, x, y);

        FunctionArray[x][y] = '*';
        FunctionArray[x][y-1] = '*';

        CheckToSeeIfEqual(FunctionArray, TestArray);

    }

    @Test
    public void Rotate360Degrees(){

        char[][] FunctionArray = new char[11][11];
        char[][] TestArray = new char[11][11];

        Piece piece = new Piece("I2");

        piece.Rotate();
        piece.Rotate();
        piece.Rotate();
        piece.Rotate();

        PlaceMove(TestArray, piece, x, y);

        FunctionArray[x][y] = '*';
        FunctionArray[x-1][y] = '*';

        CheckToSeeIfEqual(FunctionArray, TestArray);

    }



    public void PlaceMove(char[][] TestArray, Piece piece, int x, int y){

        TestArray[x][y] = '*';
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

    public void CheckToSeeIfEqual(char [][] Actual,char[][] Expected){
        for (int c = 0; c < Actual.length; c++) {
            for (int r = 0; r < Actual.length; r++) {
                try {
                    assertEquals(Expected[c][r], Actual[c][r]);
                } catch (AssertionError error) {
                    System.out.format("[%d][%d] = %c vs %c\n",c,r,Expected[c][r],Actual[c][r]);
                    throw new AssertionError("Incorrect piece ");
                }
            }
        }
    }
}
