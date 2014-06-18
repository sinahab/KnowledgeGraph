package com.knowledge.graph.backend;
import java.util.*;
import java.sql.*;
public class Mentor extends Student{
	
	int Mentor_ID;
	List<Student> mentored_students = new ArrayList<Student>();
	
	public Mentor(int student_ID, String first_name, String last_name,
				String degree, String password, int Mentored_ID) {
		super(student_ID, first_name, last_name, degree, password);

	}
	
	public void addMentored_Student(Student mentored_student){
		mentored_students.add(mentored_student);
	}

	public void deleteMentored_Student(Student mentored_student){
		mentored_students.remove(mentored_student);
	}
	
	public List<Student> getMentored_students(){
		return mentored_students;
	}
	

	
}
