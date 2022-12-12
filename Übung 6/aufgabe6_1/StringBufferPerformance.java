package aufgabe6_1;

import java.util.ArrayList;

public class StringBufferPerformance {
    private static final int COUNT = 10000;   // ca. 250 ms

    private static final String HEADER = """
            <html>
              <head>
                <title>String Buffer Performance Test</title>
                <!-- ... andere Angaben im Dateikopf ... -->
              </head>
              <body>
                <h1>String Buffer Performance Test</h1>
                <table border="1">
            """;

    private static final String FOOTER = """
                </table>
              </body>
            </html>
            """;

    interface Calculator {
        double calc(int num);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        long startTime, endTime;

        ArrayList<Calculator> al = new ArrayList<>();
        al.add(new Calculator() {
            public double calc(int num) {
                return num;
            }
        });
        al.add(new Calculator() {
            public double calc(int num) {
                return Math.sqrt(num);
            }
        });
        al.add(new Calculator() {
            public double calc(int num) {
                return Math.pow(num, 2);
            }
        });
        al.add(new Calculator() {
            public double calc(int num) {
                return Math.log(num);
            }
        });

        startTime = System.currentTimeMillis();

//     StringBuffer result = new StringBuffer( 10000 ); // bringt nicht viel!
        StringBuffer result = new StringBuffer(); // kein Unterschied zu StringBuilder
//     StringBuilder result = new StringBuilder();

        result.append(HEADER);

        for (Calculator c : al) {
            result.append("    <tr>\n      ");

            for (int i = 1; i <= COUNT; i++) {
                result.append("<td>");
                result.append(c.calc(i));
                result.append("</td> ");
            }
            result.append("\n    </tr>\n");
        }
        result.append(FOOTER);

        endTime = System.currentTimeMillis();

        System.out.println( result );
        System.err.println("Zeit: " + (endTime - startTime) + " ms.");
    }
}
