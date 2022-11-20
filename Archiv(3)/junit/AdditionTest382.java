package junit;

import junit.framework.TestCase;

import static org.junit.Assert.assertNotEquals;

public class AdditionTest382 extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("setUp()");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        System.out.println("tearDown()");
    }

//       public void tesstAddiere() {
    public void testAddiere() {
        // bringt Test zum Scheitern!!!
//    fail("Not yet implemented");
        System.out.println("testAddiere()");
    }

    public void testAddiere2() {
        assertEquals(10, Addition.addiere(5, 5));
        assertEquals(0, Addition.addiere(-5, 5));
        assertNotEquals(2, Addition.addiere(-5, 5));
        System.out.println("testAddiere2()");
        assertEquals(3.14, 3.14, 0.1);
    }

    public void testException() {
        try {
            Addition.mustThrowException();
            fail("No exception thrown");
        } catch (UnsupportedOperationException e) {
            assertTrue(true); // Konvention
        }
        System.out.println("testException()");
    }
}
