package tests;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.Repository;
import main.Student;
import main.StudentAttendance;
import main.StudentCoreData;

public class Test_Repository {

	String csv = "" + "123,Amar,Yadav,SER,Graduate,ayadav42\n" + "345,Danielle,Dupont,CSE,Undergrad,dmdupont52\n"
			+ "123,Amar,Yadav,SER,Graduate,ayadav42\n" + "456,Taylor,Northcott,CE,Undergraduate,tnorthcott45\n"
			+ "567,Abi,Mcgee,bs, Undergraduate,amcgee21\n" + "678,Abby,Steinman,BSE, Undergraduate,asteinman17";

	String csv2 = "" + "ayadav42,20\n" + "dmdupont52,60\n" + "ayadav42,50\n" + "tnorthcott45,25\n" + "amcgee21,3\n"
			+ "asteinman17,25";

	String csv3 = "ayadav42,40\ndmdupont52,80\ntnorthcott45,0";

	String[][] arr = { "123,Amar,Yadav,SER,Graduate,ayadav42".split(","),
			"345,Danielle,Dupont,CSE,Undergrad,dmdupont52".split(","),
			"456,Taylor,Northcott,CE,Undergraduate,tnorthcott45".split(","),
			"567,Abi,Mcgee,bs,Undergraduate,amcgee21".split(","),
			"678,Abby,Steinman,BSE,Undergraduate,asteinman17".split(","), };

	String[][] arr2 = { { "123", "Amar", "Yadav", "SER", "Graduate", "ayadav42", "70", "40" },
			{ "345", "Danielle", "Dupont", "CSE", "Undergrad", "dmdupont52", "60", "80" },
			{ "456", "Taylor", "Northcott", "CE", "Undergraduate", "tnorthcott45", "25", "0" },
			{ "567", "Abi", "Mcgee", "bs", "Undergraduate", "amcgee21", "3", "0" },
			{ "678", "Abby", "Steinman", "BSE", "Undergraduate", "asteinman17", "25", "0" }, };

	@Test
	public void testRepositoryAddStudents() throws Exception {
		Repository repo = new Repository();
		repo.addStudentsFromRoster(new StringReader(csv));
		int actual = 5;
		int expected = repo.getStudentList().size();
		assertEquals(expected, actual);
	}

//	@Test
//	public void testRepositoryAddStudents2() throws Exception {
//		Repository repo = new Repository();
//		repo.addStudentsFromRoster(new StringReader(csv));
//		
//		String[][] actual = arr;
//		String[][] expected = repo.getTableData();
//		print2DArrayOfStrings(arr);
//		System.out.println("---");
//		print2DArrayOfStrings(expected);
//		boolean check1 = Arrays.deepEquals(actual, expected);
//		assertTrue(check1);
//	}

	@Test
	public void testRepositoryAddAttendance() throws Exception {
		Repository repo = new Repository();
		repo.addStudentsFromRoster(new StringReader(csv));
		repo.addAttendance(new StringReader(csv2), "Nov 20");
		repo.addAttendance(new StringReader(csv3), "Nov 21");

//		System.out.println("---");
		String[][] actual = arr2;
		String[][] expected = repo.getTableData();
//		print2DArrayOfStrings(arr2);
//		System.out.println("---");
//		print2DArrayOfStrings(expected);
//		System.out.println("---");
//		System.out.println("---");

		assertTrue(are2DArraysSame(actual, expected));
	}

	public boolean are2DArraysSame(String[][] arr1, String[][] arr2) {
		boolean retVal = true;
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[i].length; j++) {
				if (arr1[i][j] != null && arr2[i][j] == null) {
					retVal = false;
					break;
				} else if (arr1[i][j] == null && arr2[i][j] != null) {
					retVal = false;
					break;
				} else if (arr1[i][j] != null && arr2[i][j] != null) {
					boolean equal = arr1[i][j].equals(arr2[i][j]);
					if (!equal) {
//						System.out.print("[" + i + "," + j + "]: " + equal + " :");
//						System.out.print(arr1[i][j] + " " + arr1[i][j].length());
//						System.out.print(arr2[i][j] + " " + arr2[i][j].length());
						retVal = false;
						break;
					}
				}
			}
		}
//		System.out.print(retVal);
		return retVal;
	}

	public void print2DArrayOfStrings(String[][] arr) {
		for (String[] row : arr) {
			for (String str : row) {
				System.out.print(str + " ");
//				System.out.print(str.getClass().getName());
			}
			System.out.println();
		}
	}

}
