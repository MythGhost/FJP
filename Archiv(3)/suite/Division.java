package suite;

public class Division {
    public static int div(int x, int y) {
        return x / y;
    }

    public static boolean mustThrowException() {
        throw new UnsupportedOperationException();
    }
}
