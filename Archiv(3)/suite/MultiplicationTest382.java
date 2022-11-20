package suite;

import junit.framework.TestCase;

public class MultiplicationTest382 extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
        System.out.println("setUp()");
    }

    public void tearDown() throws Exception {
        System.out.println("tearDown()");
    }

    public void testMult() {
        assertEquals(10, Multiplication.mult(5, 2));
    }

    public void testMustThrowException() {
        try
        {
            Multiplication.mustThrowException();
            fail("No exception thrown");
        }
        catch (UnsupportedOperationException e)
        {
            assertTrue(true); // Konvention
        }
        System.out.println("testException()");
    }
}