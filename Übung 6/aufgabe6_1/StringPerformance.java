package aufgabe6_1;

public class StringPerformance {
    private static final long SLEEP_TIME = 0;

    private static final int COUNT = 10000;   // ca. 1 second
//    private static final int COUNT = 50000;   // ca. 11 seconds

    private static final String HEADER = """
            <html>
              <head>
                <title>String Performance Test</title>
                <!-- ... andere Angaben im Dateikopf ... -->
              </head>
              <body>
                <h1>String Performance Test</h1>
                <table border="1">
            """;

    private static final String FOOTER = """
                </table>
              </body>
            </html>
            """;

    /**
     * @param count
     * @return
     * @throws InterruptedException
     */
    private String numbers(final int count) throws InterruptedException {
        String s = "<tr>\n";

        for (int i = 1; i <= count; i++) {
            s += "  <td>" + i + "</td>\n";
            Thread.sleep(SLEEP_TIME);
        }

        s += "</tr>\n";

        return s;
    }

    /**
     * @param count
     * @return
     * @throws InterruptedException
     */
    private String root(final int count) throws InterruptedException {
        String s = "<tr>\n";

        for (int i = 1; i <= count; i++) {
            s += "  <td>" + Math.sqrt(i) + "</td>\n";
            Thread.sleep(SLEEP_TIME);
        }

        s += "</tr>\n";

        return s;
    }

    /**
     * @param count
     * @return
     * @throws InterruptedException
     */
    private String square(final int count) throws InterruptedException {
        String s = "<tr>\n";

        for (int i = 1; i <= count; i++) {
            s += "  <td>" + Math.pow(i, 2) + "</td>\n";
            Thread.sleep(SLEEP_TIME);
        }

        s += "</tr>\n";

        return s;
    }

    /**
     * @param count
     * @return
     * @throws InterruptedException
     */
    private String log(final int count) throws InterruptedException {
        String s = "<tr>\n";

        for (int i = 1; i <= count; i++) {
            s += "  <td>" + Math.log(i) + "</td>\n";
            Thread.sleep(SLEEP_TIME);
        }

        s += "</tr>\n";

        return s;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        StringPerformance sp = new StringPerformance();
        String result = HEADER;
        try {
            result += sp.numbers(COUNT);
            result += sp.root(COUNT);
            result += sp.square(COUNT);
            result += sp.log(COUNT);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        result += FOOTER;

        endTime = System.currentTimeMillis();

        System.out.println(result);
        System.err.println("Zeit: " + (endTime - startTime));
    }
}
