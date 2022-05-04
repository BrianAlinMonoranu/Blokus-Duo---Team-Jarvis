package Control;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

public class Piece {

    public final String type;

    public int value;

    public int x = 0;

    public int[] sequence = new int[16];

    public Piece(String type) {
        this.type = type;

        /* Here the first position we set to 0 as we don't want to use this position */
        this.sequence[x++] = 0;
        this.sequence[x++] = 0;
        /* Therefore, we want to use the next positions to input what positions we want to place the symbol depending on the piece */

        switch (type) {
            case "I1" -> I1();
            case "I2" -> I2();
            case "I3" -> I3();
            case "I4" -> I4();
            case "I5" -> I5();
            case "V3" -> V3();
            case "L4" -> L4();
            case "Z4" -> Z4();
            case "Z5" -> Z5();
            case "O4" -> O4();
            case "L5" -> L5();
            case "T4" -> T4();
            case "T5" -> T5();
            case "V5" -> V5();
            case "N" -> N();
            case "P" -> P();
            case "W" -> W();
            case "U" -> U();
            case "F" -> F();
            case "X" -> X();
            case "Y" -> Y();
        }
    }

    private void up() {
        sequence[x++] = 0;
        sequence[x++] = 1;
    }

    private void down() {
        sequence[x++] = 0;
        sequence[x++] = -1;
    }

    private void left() {
        sequence[x++] = -1;
        sequence[x++] = 0;
    }

    private void right() {
        sequence[x++] = 1;
        sequence[x++] = 0;
    }

    private void I1() {
        value = 1;
    }

    private void I2() {
        up();
        value = 2;
    }

    private void I3() {
        up();
        up();
        value = 3;
    }

    private void I4() {
        up();
        up();
        up();
        value = 4;
    }

    private void I5() {
        up();
        up();
        up();
        up();
        value = 5;
    }

    private void V3() {
        up();
        down();
        right();
        value = 3;
    }

    private void L4() {
        up();
        up();
        down();
        down();
        right();
        value = 4;
    }

    private void Z4() {
        left();
        right();
        up();
        right();
        value = 4;

    }

    private void Z5() {
        right();
        up();
        down();
        left();
        left();
        down();
        value = 5;
    }

    private void O4() {
        up();
        right();
        down();
        value = 4;
    }

    private void L5() {
        up();
        down();
        right();
        right();
        right();
        value = 5;
    }

    private void T4() {
        up();
        down();
        left();
        right();
        right();
        value = 4;
    }

    private void T5() {
        left();
        right();
        right();
        left();
        up();
        up();
        value = 5;
    }

    private void V5() {
        up();
        up();
        down();
        down();
        right();
        right();
        value = 5;
    }

    private void N() {
        down();
        left();
        right();
        up();
        right();
        right();
        value = 5;
    }

    private void P() {
        right();
        down();
        left();
        down();
        value = 5;
    }

    private void W() {
        up();
        right();
        left();
        down();
        left();
        down();
        value = 5;
    }

    private void U() {
        up();
        right();
        left();
        down();
        down();
        right();
        value = 5;
    }

    private void F() {
        up();
        right();
        left();
        down();
        left();
        right();
        down();
        value = 5;
    }

    private void X() {
        up();
        down();
        left();
        right();
        right();
        left();
        down();
        value = 5;
    }

    private void Y() {
        left();
        right();
        up();
        down();
        right();
        right();
        value = 5;
    }

    public void Rotate() {
        double angle = Math.round(-Math.PI / 2);
        for (int i = 0; i < this.sequence.length; i += 2) {
            int x = this.sequence[i];
            int y = this.sequence[i + 1];
            this.sequence[i] = (int) Math.round(x * Math.cos(angle) - y * Math.sin(angle));
            this.sequence[i + 1] = (int) Math.round(x * Math.sin(angle) + y * Math.cos(angle));
        }
    }

    public void Flip() {
        for (int i = 0; i < sequence.length; i += 2) {
            sequence[i] *= -1;
        }
    }

    public String getType() {
        return type;
    }
  
    public int getValue() {
        return value;
    }
}