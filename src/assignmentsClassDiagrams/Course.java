package assignmentsClassDiagrams;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private Teacher instructor;
	private List<Student> students;
	private String room;
	private int period;
	
	public Course(Teacher instructor, String room, int period) {
		this(instructor, room, period, new ArrayList<Student>());
	}
	
	public Course(Teacher instructor, String room, int period, List<Student> students) {
		this.instructor = instructor;
		this.room = room;
		this.period = period;
		this.students = students;
	}
	
	public boolean addStudent(Student student) {
		return true;
	}
	
	public boolean removeStudent(Student student){
		return true;
	}
	
	public Teacher getInstructor() {
		return instructor;
	}
	
	public void setIntructor(Teacher instructor) {
		this.instructor = instructor;
	}
	
	public List<Student> getStudents(){
		return students;
	}
	
	public String getRoom() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
	
	public int getPeriod() {
		return period;
	}
}
