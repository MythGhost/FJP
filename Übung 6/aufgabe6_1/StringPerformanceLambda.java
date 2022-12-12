package aufgabe6_1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.function.Function;

public class StringPerformanceLambda {
    private static final int COUNT = 50_000;

    private static final String HEADER = """
            <html>
              <head>
                <title>String Performance Test</title>
              </head>
              <body>
                <h1>String Performance Test</h1>
                <table border="1">
                  <tr bgcolor=green>
                    <th>Anzahl</th>
                    <th>Wurzel n</th>
                    <th>n Quadrat</th>
                    <th>Log n</th>
                  </tr>
            """;

    private static final String FOOTER = """
                </table>
              </body>
            </html>
            """;

    public static void main(String... args) {
        long startTime, endTime;

        // list of calculations as functional interface "Function" which will be calculated and printed
        ArrayList<Function<Integer, Double>> list = new ArrayList<>();
//      list.add( a -> new Double(a) );   // would be more generic but less efficient
        list.add(Math::sqrt);
        list.add(a -> Math.pow(a, 2));
        list.add(Math::log10);

        startTime = System.currentTimeMillis();

        StringBuffer result = new StringBuffer(HEADER);

        for (int i = 1; i <= COUNT; i++) {
            result.append("<tr align=right> <td>").append(i).append("</th>");
            for (Function<Integer, Double> f : list) {
                result.append("<td>");
                result.append(f.apply(i));
                result.append("</td>");
            }
            result.append("</td></tr>\n");
        }

        result.append(FOOTER);

        endTime = System.currentTimeMillis();

        try (PrintStream ps = new PrintStream(new FileOutputStream("aufgabe6_1/CalculatedLambda.html"))) {
            ps.print(result);
        } catch (FileNotFoundException fne) {
            System.out.println(fne);
        }

//    System.out.println( result );
        System.err.println("Zeit: " + (endTime - startTime) + " ms.");
    }
}