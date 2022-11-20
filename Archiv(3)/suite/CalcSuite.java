package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdditionTest.class,
        SubtractionTest.class,
        MultiplicationTest.class})
public class CalcSuite {
}
