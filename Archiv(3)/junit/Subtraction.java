package junit;

public class Subtraction {
    public static int subtract(int x, int y) {
        return x - y;
    }

    public static boolean mustThrowException() {
        throw new UnsupportedOperationException();
    }
}
