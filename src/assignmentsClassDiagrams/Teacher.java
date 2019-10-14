package assignmentsClassDiagrams;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
	private String name;
	private String department;
	private String office;
	private List<Course> teaches;
	
	public Teacher(String name, String department, String office) {
		this(name, department, office, new ArrayList<Course>());
	}

	public Teacher(String name, String department, String office, List<Course> teaches) {
		this.name = name;
		this.department = department;
		this.office = office;
		this.teaches = teaches;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public String getOffice(){
		return office;
	}
	
	public List<Course> getCourses(){
		return teaches;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void setOffice(String office) {
		this.office = office;
	}
	
	public boolean addCourse(Course newCourse) {
		return true;
	}
	
	public boolean removeCourse(Course course){
		return true;
	}
	
	
}

