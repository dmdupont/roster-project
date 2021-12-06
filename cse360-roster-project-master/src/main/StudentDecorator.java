package main;

import java.util.List;

public abstract class StudentDecorator extends Student {
	
	protected Student student;
	
	public void add(Student student) {
		this.id = student.id.trim();
		this.firstName = student.firstName.trim();
		this.lastName = student.lastName.trim();
		this.programAndPlan = student.programAndPlan.trim();
		this.academicLevel = student.academicLevel.trim();
		this.asurite = student.asurite.trim();
		this.student = student;
	}
	
	@Override
	public void setStudentData(String csr) {
		this.student.setStudentData(csr);
	}
	
	@Override
	public void setStudentData(List<String> list) {
		this.student.setStudentData(list);
	}
	
	@Override
	public String toString() {
		return this.student.toString();
	}
	
	@Override
	public List<String> toList(){
		return this.student.toList();
	}

}
