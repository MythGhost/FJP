package junit;

// create JUnit Test in IntelliJ with Shift+Cmd+T on class name Addition
public class Addition {
    public static int addiere(int x, int y) {
        return x + y;
    }

    public static boolean mustThrowException() {
        throw new UnsupportedOperationException();
//    return false;
    }
}