package aufgabe4_2;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class FunctionContext {
    private FunctionStrategy functionStrategy = null;

    // just to simplify the test
    static List<FunctionStrategy> fl = List.of(new Sinus(), new Cosinus(), new Exp());

    public void setFunctionStrategy(final FunctionStrategy fs) {
        functionStrategy = fs;
    }

    public void calcFunction(final double x) {
        System.out.println(functionStrategy.toString() + "\t\t\t\t\t" + functionStrategy.fvonx(x));
        System.out.println("Ableitung von " + functionStrategy.toString() + "\t" + functionStrategy.fstrichvonx(x));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        FunctionContext ctx = new FunctionContext();
        double[] doubleArray = {0, 1.5, Math.PI};

        // just print out the calculated values
        for (double x : doubleArray) {
            System.out.println("----------- Value: " + x);
            for (FunctionStrategy f : fl) {
                ctx.setFunctionStrategy(f);
                ctx.calcFunction(x);
                System.out.println();
            }
        }
    }
}
