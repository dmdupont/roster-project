import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.Test_PlotData;
import tests.Test_Repository;
import tests.Test_StudentAttendance;
import tests.Test_StudentCoreData;

@RunWith(Suite.class)
@SuiteClasses({ Test_StudentCoreData.class, Test_StudentAttendance.class, Test_Repository.class, Test_PlotData.class })
public class AllTests {

}
