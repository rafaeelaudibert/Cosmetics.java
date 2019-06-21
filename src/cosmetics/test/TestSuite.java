package cosmetics.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DatabaseTest.class, 
        EvaluationTest.class, 
        GroupTest.class, 
        ProductTest.class,
        UserTest.class})

public class TestSuite {

}