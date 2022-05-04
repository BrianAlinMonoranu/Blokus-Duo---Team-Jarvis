//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TestFlip {
//
////        static src.main.java.Control.InitializeGame start = new src.main.java.Control.InitializeGame();
//        String stPlS;
//        private int x = 7;
//        private int y = 5;
//
//    /*
//       I created this class to test out using I2 to see if flip works obviously we have proven in the TestPieces that all pieces can place properly
//       therefore I only used I2 in this case. I tested to see if we can flip vertically if the piece is rotated 90 degrees which will mean its point
//       right and then when we flip the piece will flip vertical on axis to the left and same when using 270 degrees it will go from left to right
//     */
//
//
//        /* This is quite useful as we setup the tests by using the file to test all the test cases as we need player names and our program
//           only accepts manual input or file input */
//        @BeforeAll
//        public static void setup() {
//            try {
//                start.initPlayers("Random", new Scanner(new File("sample.txt")));
//            } catch (FileNotFoundException e) {
//                System.out.println("Unable to find file.");
//            }
//        }
//
//        /* This will remain in the same position */
//        @Test
//        public void Flip(){
//
//            char[][] FunctionArray = new char[11][11];
//            char[][] TestArray = new char[11][11];
//
//            src.main.java.Control.Piece piece = new src.main.java.Control.Piece("I2");
//
//            piece.flip();
//
//            PlaceMove(TestArray, piece, x, y);
//
//            FunctionArray[x][y] = '*';
//            FunctionArray[x-1][y] = '*';
//
//            CheckToSeeIfEqual(FunctionArray, TestArray);
//
//        }
//
//        /* src.main.java.Control.Piece will look like this
//                    * *
//                 WHEN YOU FLIP
//                 * *
//         */
//
//        @Test
//        public void FlipOnVerticalAxisRightToLeft(){
//
//            char[][] FunctionArray = new char[11][11];
//            char[][] TestArray = new char[11][11];
//
//            src.main.java.Control.Piece piece = new src.main.java.Control.Piece("I2");
//
//            piece.Rotate();
//            piece.flip();
//
//            PlaceMove(TestArray, piece, x, y);
//
//            FunctionArray[x][y] = '*';
//            FunctionArray[x][y-1] = '*';
//
//            CheckToSeeIfEqual(FunctionArray, TestArray);
//        }
//
//        /* src.main.java.Control.Piece will look like this
//                 * *
//                 WHEN YOU FLIP
//                     * *
//         */
//
//        @Test
//        public void FlipOnVerticalAxisLeftToRight(){
//
//            char[][] FunctionArray = new char[11][11];
//            char[][] TestArray = new char[11][11];
//
//            src.main.java.Control.Piece piece = new src.main.java.Control.Piece("I2");
//
//            piece.Rotate();
//            piece.Rotate();
//            piece.Rotate();
//            piece.flip();
//
//            PlaceMove(TestArray, piece, x, y);
//
//            FunctionArray[x][y] = '*';
//            FunctionArray[x][y+1] = '*';
//
//            CheckToSeeIfEqual(FunctionArray, TestArray);
//
//        }
//
//
//        //We place the move in the TestArray
//        public char[][] PlaceMove(char[][] TestArray, src.main.java.Control.Piece piece, int x, int y){
//
//            TestArray[x][y] = '*';
//            for (int i = 0; i < piece.sequence.length; i++) {
//                int j = piece.sequence[i];
//
//                if (i % 2 == 1) {
//                    y = y + j;
//                } else {
//                    x = x - j;
//                }
//                TestArray[x][y] = '*';
//            }
//            return TestArray;
//        }
//
//        /* Checks to see if everything is the same in both arrays */
//        public void CheckToSeeIfEqual(char [][] Actual,char[][] Expected){
//            for (int c = 0; c < Actual.length; c++) {
//                for (int r = 0; r < Actual.length; r++) {
//                    assertEquals(Expected[c][r], Actual[c][r]);
//                }
//            }
//        }
//    }
//
