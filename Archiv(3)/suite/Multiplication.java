package suite;

public class Multiplication {
    public static int mult(int x, int y) {
        return x * y;
    }

    public static boolean mustThrowException() {
        throw new UnsupportedOperationException();
    }
}