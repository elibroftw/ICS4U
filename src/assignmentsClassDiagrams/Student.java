package assignmentsClassDiagrams;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private String studentNumber;
	private List<Course> courses;
	private Textbook textbook;
	
	public Student(String name, String studentNumber){
		this.name = name;
		this.studentNumber = studentNumber;
		courses = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getStudentNumber() {
		return studentNumber;
	}
	
	public List<Course> getCourses(){
		return courses;
	}
	
	public Textbook getTextbook(){
		return textbook;
	}
	
	public boolean enroll(Course course) {
		return true;
	}
	
	public boolean drop(Course course) {
		return true;
	}
	
	public boolean assignTextbook(Textbook textbook) {
		return true;
	}
}
