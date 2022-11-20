package junit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdditionTest413 {
    int erg;

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
        erg = 10;

        System.out.println("setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }

    @Test
    public void testAddiere() {
        assertEquals(1., 1.1, 0.11);
        assertEquals(erg, Addition.addiere(5, 5));
        assertEquals(0, Addition.addiere(-5, 5));
        // assertNotNull();
        // assertTrue();
        // assertNotSame();
        System.out.println("testAddiere");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMustThrowException() {
        System.out.println("mustThrowException");
        assertEquals(erg, Addition.addiere(5, 5));
        Addition.mustThrowException();
    }

    @Test(timeout = 100)
    public void infinity() {
        System.out.println("infinity");
//     while(true);
    }

    /**
     * Testmethode mit AssertThat und Matcher
     */
    @Test
    public void testAssertThat() {
        ArrayList<String> al = new ArrayList<>() {{
            add("abc");
        }};
        int x = 3;
        String responseString = "color";

        assertTrue(al.contains("abc"));

        // now with assertThat and Matchers from hamcrest
        assertThat(Addition.addiere(-3, 5), is(2));

        assertThat(al, isA(ArrayList.class));
        assertThat(al, instanceOf(ArrayList.class));

        assertThat(x, is(3));
        assertThat(x, is(not(4)));

        assertThat(responseString, either(containsString("color")).or(containsString("colour")));

        assertThat(al, hasItem("abc"));
        assertThat(al.get(0), endsWith("bc"));
        System.out.println("testAssertThat");
    }


    @Test
    public void featureMatcher() throws Exception {
        // with own written matcher length!!!
        assertThat("Hello World!", length(is(12)));
        System.out.println("featureMatcher");
    }

    private Matcher<String> length(Matcher<? super Integer> matcher) {
        return new FeatureMatcher<String, Integer>(matcher, "a String of length that", "length") {
            @Override
            protected Integer featureValueOf(String actual) {
                return actual.length();
            }
        };
    }
}
