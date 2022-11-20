package suite;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SubtractionTest {

    @Test
    public void testSubtract() {
        assertEquals(10, Subtraction.subtract(15, 5));
    }
}
