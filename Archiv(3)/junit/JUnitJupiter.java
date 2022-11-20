package junit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author leo
 */
class JUnitJupiter {
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("in @BeforeAll");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
//    System.out.println("in @AfterAll");
    }

    @BeforeEach
    void setUp() throws Exception {
//    System.out.println("in @BeforeEach");
    }

    @AfterEach
    void tearDown() throws Exception {
//    System.out.println("in @AfterEach");
    }

    /**
     * Test method for {@link junit.Addition#addiere(int, int)}.
     */
    @Test
    @DisplayName("Custom test name containing spaces")
    @Disabled("for demonstration purposes")
    void testAddiere() {
        fail("Not yet implemented");
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }

    @Test
    void standardAssertions() {
        // first failing assertion stops further checks
        assertEquals(2, 2);
        assertEquals(4, 4, "The optional assertion message is now the last parameter.");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and any failures will be reported together.
        // assertAll has a list of FunctionalInterfaces Executable (void -> void)
        assertAll("addition",
                () -> assertEquals(11, Addition.addiere(5, 6)),
                () -> assertEquals(12, Addition.addiere(5, 7)));
        assertAll("addition2",
                () -> assertEquals(12, Addition.addiere(5, 6), "2nd, 1st failed"),
                () -> assertEquals(11, Addition.addiere(5, 6), () -> "2nd, 2nd failed"),
                () -> assertEquals(13, Addition.addiere(5, 7), () -> "2nd, 3rd failed"));
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("both",
                () ->
                {
                    assertEquals(0, Addition.addiere(1, -1), "addition 1 + (-1) --> 0");
                    assertEquals(1, Addition.addiere(1, -1), "addition 1 + (-1) --> 1");     // what happens?

                    // Executed only if the previous assertion is valid.
                    System.out.println("second assertion in dependent addition block");
                    assertAll("addition",
                            () -> assertEquals(3, Addition.addiere(1, 2), "assertAll: 1st, 1st failed"),
                            () -> assertEquals(4, Addition.addiere(1, 3), "assertAll: 1st, 2nd failed"));
                },
                () ->
                {
                    // Grouped assertion, so processed independently
                    // of results of first both assertions.
                    Integer result = Subtraction.subtract(2, 1);
                    assertNotNull(result);

                    // Executed only if the previous assertion is valid.
                    System.out.println("second assertion in dependent subtraction block");
                    assertAll("subtraction",
                            () -> assertEquals(-5, Subtraction.subtract(-1, 4), "assertAll: 2nd, 1st failed"),
                            () -> assertEquals(2, Subtraction.subtract(1, -1), "assertAll: 2nd, 2nd failed"));
                });
    }

    @Test
    void exceptionTesting() {
        // check parameter (javadoc or implementation) of assertThrows
        assertThrows(UnsupportedOperationException.class, () -> Addition.mustThrowException());

        // but could catch also the thrown exception
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    throw new IllegalArgumentException("a message");
                });

        System.out.println("exception message: " + exception.getMessage());
        assertEquals("a message", exception.getMessage());
    }

    @Test
    void assertWithHamcrestMatcher() {     // are still working
        assertThat(Addition.addiere(2, 1), is(equalTo(3)));
    }
}
