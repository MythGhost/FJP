package aufgabe4_1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

/**
 * neue JUnit4-Tests koennen ueber einen JUnit4TestAdapter auch mit dem alten
 * JUnitRunner laufen.
 *
 * @author Laumeyer
 */
public class ListeJUnit4Test {
    protected Liste<Integer> l = null;

    /**
     * Is called only once before whole test execution.
     *
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * Is called before each test method execution.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        l = new Liste<Integer>();
        l.add(17);
        l.add(11);
        l.add(42);
        l.add(17);
        l.add(99);
        System.out.println("setUp: size of list: " + l.size());
    }

    /**
     * Is called after each test method execution.
     *
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown: size of list: " + l.size());
        l = null;
    }

    /**
     * Test method for {@link aufgabe4_1.Liste#add(java.lang.Object)}.
     */
    // @Test(timeout=10)
    @Test
    public void testAdd() {
        assertFalse(l.contains(4711));
        l.add(4711);
        assertTrue(l.contains(4711));
        assertEquals(l.get(0), Integer.valueOf(4711));

        assertThat(l, hasItem(4711));
    }

    /**
     * Test method for {@link aufgabe4_1.Liste#delete(java.lang.Object)}.
     */
    @Test
    public void testDelete() {
        l.add(4711);
        assertTrue(l.contains(4711));
        l.delete(4711);
        assertFalse(l.contains(4711));

        assertTrue(l.delete(11));
        assertFalse(l.delete(11));
    }

    /**
     * Test method for {@link aufgabe4_1.Liste#indexOf(java.lang.Object)}.
     */
    @Test
    public void testIndexOf() {
        assertEquals(2, l.indexOf(42)); // Index startet bei 0
    }

    /**
     * Test method for {@link aufgabe4_1.Liste#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        assertTrue(l.contains(17));
        assertTrue(l.contains(11));
        assertTrue(l.contains(42));
        assertTrue(l.contains(99));
        assertTrue(l.contains(42));
        assertFalse(l.contains(13));
    }

    /**
     * Test method for {@link aufgabe4_1.Liste#size()}.
     */
    // @Ignore("not ready yet")
    @Test
    public void testSize() {
        Liste<String> stringListe = new Liste<>();
        assertEquals(0, stringListe.size());

        assertEquals(5, l.size());
        l.add(15);
        assertEquals(6, l.size());
        assertNotEquals(5, l.size());
    }

    /**
     * Test method for {@link aufgabe4_1.Liste#get(int)}.
     */
    @Test
    public void testGet() {
        Integer i = 42;

        assertEquals(i, l.get(l.indexOf(i)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException() {
        Integer i = 42;

        assertEquals(i, l.get(i));
    }

    /**
     * Test method for {@link aufgabe4_1.Liste#remove(int)}.
     */
    @Test
    public void testRemove() {
        assertTrue(l.contains(42));
        int i = l.indexOf(42);

        l.remove(i);
        assertFalse(l.contains(42));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove2() {
        l.remove(17);   // position index not value!
    }
}
