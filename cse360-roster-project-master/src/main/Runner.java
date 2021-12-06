package main;

import java.io.StringReader;

public class Runner {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Repository repository = new Repository();

		String csv = "123,Amar,Yadav,SER,Graduate,ayadav42\n345,Danielle,Dupont,CSE,Undergrad,dmdupont52";
		repository.addStudentsFromRoster(new StringReader(csv));

		repository.displayStudents();
		System.out.println("");

		String csv2 = "ayadav42,20\ndmdupont52,60\nayadav42,50";
		repository.addAttendance(new StringReader(csv2), "Nov 20");

		String csv3 = "ayadav42,70\ndmdupont52,50";
		repository.addAttendance(new StringReader(csv3), "Nov 21");

		repository.display();
//		System.out.println(repository.getTableData());
		print2DArrayOfStrings(repository.getTableData());
	}

	public static void print2DArrayOfStrings(String[][] arr) {
		for (String[] row : arr) {
			for (String str : row) {
				System.out.print(str + " ");
			}
			System.out.println();
		}
	}

}
