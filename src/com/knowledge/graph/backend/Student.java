package com.knowledge.graph.backend;


public class Student {

	int student_ID;
	String first_name;
	String last_name;
	String degree;
	String fullname;
	private String password;
	
	public Student(int student_ID,String first_name,String last_name,String degree,String password){
		
		this.student_ID = student_ID;
		this.first_name = first_name;
		this.last_name = last_name;
		this.degree = degree;
		this.password = password;
		fullname = first_name +" "+ last_name;
	}
	
	public int getStudentID(){
		return student_ID;
	}
	
	public String getFullName(){
		return fullname;
	}
	
	public String getDegree(){
		return degree;
	}
	
	public String toString(){
		Integer sID = new Integer(student_ID);
		return "Name: "+fullname+"\nStudent ID:"+ sID.toString()+"\nDegree: "+degree;
	}
	
	public boolean checkPassword(String password){
		boolean authenticate = false;
		
		if(password.equals(this.password))
			authenticate = true;
		
		return authenticate;
	}
}
