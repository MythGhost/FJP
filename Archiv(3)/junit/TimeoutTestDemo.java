package junit;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.junit.jupiter.api.Test;

class TimeoutTestDemo {
    @Test()
    void timeoutNotExceeded() {
        // The following assertion succeeds. Also possible ofSeconds(), ...
        // 2nd parameter functional interface Executable
        assertTimeout(ofMinutes(2), () ->
        { // Perform a task that takes less than 2 minutes.
            System.out.println("fast task < 2 min");
            Thread.sleep(1000);
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        // 2nd parameter functional interface ThrowingSupplier
        String actualResult = assertTimeout(ofMinutes(2),
                () -> {
                    return "a result";
                });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        // 2nd parameter functional interface ThrowingSupplier
        String actualGreeting = assertTimeout(ofMinutes(2), TimeoutTestDemo::greeting);
        assertEquals("Hello, World!", actualGreeting);
    }

    private static String greeting() {
        return "Hello, World!";
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 90 ms
        assertTimeout(ofMillis(10),
                () ->
                {
                    // Simulate task that takes more than 10 ms.
//            Thread.sleep(100);        // comment out!!!
                });
    }
}
