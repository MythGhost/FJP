package suite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests3Suite extends TestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests3Suite.class.getName());

        suite.addTestSuite(AdditionTest382.class);
        suite.addTestSuite(MultiplicationTest382.class);
        // add subtraction test

        return suite;
    }

}
