package com.knowledge.graph.backend;
import java.util.*;
import java.sql.*;

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
	
	public List<Mentor> getMentors(){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Mentor> mentors = new ArrayList<Mentor>();
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT s.*, m.student_number FROM Mentors m, Students s WHERE m.mentored_student="+student_ID+" AND s.student_number=m.student_number";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				mentors.add(new Mentor(student_ID, rs.getNString("first_name"), rs.getNString("last_name"), rs.getNString("degree"), 
						query, rs.getInt("student_number")));
			}
		}
		catch(SQLException e){
			System.out.println("An error occured while searching!");
			e.printStackTrace();
		}
		finally{
			if(connection!=null)
				try{
					connection.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
		}
		if(mentors.isEmpty()){
			return null;
		}
		else{
			return mentors;
		}
		
	}
}
