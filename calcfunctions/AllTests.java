package calculatorfunction;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LnTest.class, Pow10Test.class, SineTest.class, SinhTest.class,
		SqrtTest.class })
public class AllTests {

}
