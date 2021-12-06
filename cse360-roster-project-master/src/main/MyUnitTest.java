package main;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyUnitTest extends TestCase {


		@SuppressWarnings("deprecation")
		@Test
		public void testCalculatePercentAttendance() {
			int time = 0;
			assertEquals(0.0, PlotData.calculatePercentAttendance(time));
		}
		
		@Test
		public void testCalculatePercentAttendance1() {
			int time = 17;
			assertEquals(2.5, PlotData.calculatePercentAttendance(time));
		}
		
		@Test
		public void testCalculatePercentAttendance2() {
			int time = 46;
			assertEquals(6.0, PlotData.calculatePercentAttendance(time));
		}
		
		@Test
		public void testCalculatePercentAttendance3() {
			int time = 59;
			assertEquals(8.0, PlotData.calculatePercentAttendance(time));
		}
		
		@Test
		public void testCalculatePercentAttendance4() {
			int time = 75;
			assertEquals(10.0, PlotData.calculatePercentAttendance(time));
		}
		
		public void testCalculatePercentAttendance5() {
			int time = 80;
			assertEquals(10.0, PlotData.calculatePercentAttendance(time));
		}
		
}



