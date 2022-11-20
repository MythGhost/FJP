package aufgabe4_1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InorderListeJupiterTest {
    protected InorderListe<Integer> l = null;

    @BeforeEach
    void setUp() throws Exception {
        l = new InorderListe<Integer>();
        l.add(17);
        l.add(11);
        l.add(42);
        l.add(17);
        l.add(99);
    }

    @AfterEach
    void tearDown() throws Exception {
        l = null;
    }

    @ParameterizedTest
    @ValueSource(ints = {4711, 815})
    void testAdd(int i) {
        l.add(i);
        assertTrue(l.contains(i));
        assertEquals(l.get(l.size() - 1), Integer.valueOf(i), () -> "Can not delete element added a second ago.");
    }

    @Test
    void testDelete() {
        l.add(4711);
        assertTrue(l.contains(4711));
        l.delete(4711);
        assertFalse(l.contains(4711));

        assertAll("deleting",
                () -> assertTrue(l.delete(17)),
                () -> assertTrue(l.delete(17)),
                () -> assertFalse(l.delete(17))
        );

        l.add(18);
        assertTrue(l.delete(18));
    }
}
