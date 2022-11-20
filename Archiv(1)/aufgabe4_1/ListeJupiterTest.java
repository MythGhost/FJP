package aufgabe4_1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ListeJupiterTest {
    protected Liste<Integer> l = null;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        l = new Liste<Integer>();
        l.add(17);
        l.add(11);
        l.add(42);
        l.add(17);
        l.add(99);
        System.out.println("setUp: size of list: " + l.size());
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("tearDown: size of list: " + l.size());
        l = null;
    }

    @Test
    void testAdd() {
        l.add(4711);
        assertAll("adding",
                () -> assertTrue(l.contains(4711)),
                () -> assertEquals(l.get(0), Integer.valueOf(4711))
        );
    }

    @Test
    void testDelete() {
        l.add(4711);
        assertTrue(l.contains(4711));
        l.delete(4711);
        assertFalse(l.contains(4711));

        assertAll("deleting",
                () -> assertTrue(l.delete(11)),
                () -> assertFalse(l.delete(11))
        );
    }

    @Test
    void testRemove() {
        assertTrue(l.contains(42));
        int i = l.indexOf(42);

        l.remove(i);
        assertFalse(l.contains(42));
    }

    @Test
    void testRemove2() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            l.remove(17);
        });  // invalid position index not value!
    }

    @ParameterizedTest
    @ValueSource(ints = {17, 11, 42, 99, 42})
    void testContains(int argument) {
        assertAll("contains",
                () -> assertTrue(l.contains(argument)),
                () -> assertFalse(l.contains(13))
        );
    }

    @Disabled("for demonstration purposes")
    @Test
    void testIndexOf() {
        assertEquals(l.indexOf(42), 2); // Index startet bei 0
    }

    @Test
    void testSize() {
        assertEquals(l.size(), 5);
    }

    @Test
    void testGet() {
        Integer i = 42;
        assertEquals(i, l.get(l.indexOf(i)));
        assertEquals(i, l.get(l.indexOf(i)));
    }

}
