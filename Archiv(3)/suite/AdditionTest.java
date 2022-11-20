package suite;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.Addition;

public class AdditionTest {
    Collection<String> col;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("setUpBeforeClass");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("tearDownAfterClass");
    }

    @Before
    public void setUp() throws Exception {
//    col = new LinkedList<String>() {{add("abc");}};   // result: green
//    col = new ArrayList<String>() {{add("abc");}};    // result: red
        col = new ArrayList<String>() {{
            add("abcd");
        }}; // result: green

        System.out.println("setUP");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }

    @Test
    public void testAddiere() {
        assertEquals(1., 1.1, 0.11);
        assertEquals(10, Addition.addiere(5, 5));
        assertEquals(0, Addition.addiere(-5, 5));
        assertThat(Addition.addiere(-3, 5), is(2));
        // assertNotNull();
        // assertEquals();
        // assertTrue();
        // assertNotSame();
    }

    @Test(expected = UnsupportedOperationException.class)
//  @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testMustThrowException() {
        assertEquals(10, Addition.addiere(5, 5));
        Addition.mustThrowException();
    }

//  @Test(timeout=100)
//  public void infinity()
//  {
//    while(true);
//  }


    /**
     * Testmethode mit Assumption und Matcher
     */
    @Test
    public void testContains() {
//    assumeThat(col, is( ArrayList.class ));
        assertTrue(col.contains("abcd"));
    }
}
