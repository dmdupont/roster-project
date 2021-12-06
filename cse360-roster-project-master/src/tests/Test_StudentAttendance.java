package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.Student;
import main.StudentAttendance;
import main.StudentCoreData;

public class Test_StudentAttendance {

	String[] arr = { "123", "Amar", "Yadav", "SER", "Graduate", "ayadav42" };
	String csr = String.join(",", arr);
	List<String> list = new ArrayList<>(Arrays.asList(arr));
	String[] arr2 = { "ayadav42", "91" };
	String csr_att = String.join(",", arr2);
	List<String> list2 = new ArrayList<>(Arrays.asList(arr2));
	Student st = new StudentCoreData(list);

	@Test
	public void testStudentAttendanceToString() {
		Student student = new StudentAttendance(st, csr_att);
		String actual = student.toString();
		String expected = String.join(",", arr).concat(",91");
		assertEquals(expected, actual);
	}

	@Test
	public void testStudentAttendance() {
		Student student = new StudentAttendance(st, csr_att);
		String actual = student.toString();
		String expected = csr.concat(",91");
		assertEquals(expected, actual);
	}

	@Test
	public void testStudentAttendance2() {
		Student student = new StudentAttendance(st, list2);
		String actual = student.toString();
		String expected = csr.concat(",91");
		assertEquals(expected, actual);
	}

}
