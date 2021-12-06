package main;

import java.util.List;

public class StudentCoreData extends Student {
	
	public StudentCoreData(String csr) {
		setStudentData(csr);
	}
	
	public StudentCoreData(List<String> list) {
		setStudentData(list);
	}
	
	@Override
	public void setStudentData(String csr) {
		super.setStudentData(csr);
	}
	
	@Override
	public void setStudentData(List<String> list) {
		super.setStudentData(list);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public List<String> toList(){
		return super.toList();
	}
}
