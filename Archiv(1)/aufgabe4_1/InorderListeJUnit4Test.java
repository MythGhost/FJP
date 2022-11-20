package aufgabe4_1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// or extend Test of "normal" List ;-)
public class InorderListeJUnit4Test {
    protected InorderListe<Integer> l = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        l = new InorderListe<Integer>();
        l.add(17);
        l.add(11);
        l.add(42);
        l.add(17);
        l.add(99);
    }

    @After
    public void tearDown() throws Exception {
        l = null;
    }

    @Test
    public void testAdd() {
        l.add(4711);
        assertTrue(l.contains(4711));
        assertEquals(l.get(5), Integer.valueOf(4711));
    }

    @Test
    public void testDelete() {
        l.add(4711);
        assertTrue(l.contains(4711));
        l.delete(4711);
        assertFalse(l.contains(4711));

        assertTrue(l.delete(17));
        assertTrue(l.delete(17));
        assertFalse(l.delete(17));

        l.add(18);
        assertTrue(l.delete(18));
    }

    @Test
    public void testGet() {
        try {
            l.get(-1);
            fail("no exception");
        } catch (Exception e) {
            assertTrue(true);
        }
        assertEquals(l.get(4), Integer.valueOf(99));
        assertEquals(l.get(3), Integer.valueOf(17));
        assertEquals(l.get(2), Integer.valueOf(42));
        assertEquals(l.get(1), Integer.valueOf(11));
        assertEquals(l.get(0), Integer.valueOf(17));
        try {
            l.get(5);
            fail("no exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testContains() {
        assertTrue(l.contains(17));
        assertTrue(l.contains(11));
        assertTrue(l.contains(42));
        assertTrue(l.contains(99));
        assertTrue(l.contains(42));
        assertFalse(l.contains(13));
    }

    @Test
    public void testSize() {
        Liste<String> stringListe = new Liste<>();
        assertEquals(0, stringListe.size());

        assertEquals(5, l.size());
        l.add(15);
        assertEquals(6, l.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWithExceptions() {
        l.get(-1);
        l.get(5); // never reached
    }

    @Test
    public void testRemove() {
        assertTrue(l.contains(42));
        int i = l.indexOf(42);

        l.remove(i);
        assertFalse(l.contains(42));
    }
}
