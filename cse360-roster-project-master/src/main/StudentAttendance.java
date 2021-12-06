package main;

import java.util.List;

public class StudentAttendance extends StudentDecorator {

	protected String asurite;
	protected int time = 0;

	public StudentAttendance(Student student, String csr) {
		super.add(student);
		setStudentAttendanceData(csr);
	}

	public StudentAttendance(Student student, List<String> list) {
		setStudent(student);
		setStudentAttendanceData(list);
	}

	public StudentAttendance(List<String> list) {
		setStudentAttendanceData(list);
	}

	public void setStudent(Student student) {
		super.add(student);
	}

	public void incrementTime(String time) {
		this.time += Integer.parseInt(time);
	}
	
	public void incrementTime(int time) {
		this.time += time;
	}

	private void setStudentAttendanceData(String csr) {
		String[] arr = csr.split(",");
		this.asurite = arr[0].trim();
		if(arr[1] != null) {
			this.time = Integer.parseInt(arr[1]);			
		}else {
			this.time = 0;
		}
	}

	private void setStudentAttendanceData(List<String> list) {
		this.asurite = list.get(0).trim();
		if(list.get(1) != null) {
			this.time = Integer.parseInt(list.get(1));			
		}else {
			this.time = 0;
		}
	}

	@Override
	public String toString() {
		return super.toString() + "," + this.time;
	}
	
	@Override
	public List<String> toList() {
		List<String> list = super.toList();
		list.add(String.valueOf(this.time));
		return list;
	}

	public void display() {
		System.out.println(this.asurite + ", " + this.time);
	}

}
