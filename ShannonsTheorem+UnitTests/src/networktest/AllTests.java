package networktest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Test_ShannonsModel.class, Test_ShannonsTheorem.class })
public class AllTests {

}
