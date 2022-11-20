package aufgabe4_1;

/**
 * Regeln zum Testen unter http://junit.sourceforge.net/doc/faq/faq.htm
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ListeJUnit4Test.class, InorderListeJUnit4Test.class})
public class TestSuiteJUnit4 {
}
