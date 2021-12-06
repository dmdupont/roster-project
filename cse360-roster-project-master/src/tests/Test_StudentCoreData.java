package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.Student;
import main.StudentCoreData;

public class Test_StudentCoreData {

	String[] arr = { "123", "Amar", "Yadav", "SER", "Graduate", "ayadav42" };
	String csr = String.join(",", arr);
	List<String> list = new ArrayList<>(Arrays.asList(arr));
	
	@Test
	public void testStudentCoreDataToString() {
		Student student = new StudentCoreData(list);
		String actual = student.toString();
		String expected = String.join(",", arr);
		assertEquals(expected, actual);
	}

	@Test
	public void testStudentCoreData() {
		Student student = new StudentCoreData(list);
		String actual = student.toString();
		String expected = csr;
		assertEquals(expected, actual);
	}

	@Test
	public void testStudentCoreData2() {
		Student student = new StudentCoreData(csr);
		String actual = String.join(",", student.toList());
		String expected = csr;
		assertEquals(expected, actual);
	}

}
