package com.knowledge.graph.backend;
import java.util.*;
import java.sql.*;
public class Mentor extends Student{
	
	int Mentor_ID;
	int mentee_id;
	
	public Mentor(int student_ID, String first_name, String last_name,
				String degree, String password, int mentee_id) {
		super(student_ID, first_name, last_name, degree, password);
		this.mentee_id = mentee_id;
	}
	
	public boolean addMentee(int mentored_student){
		Connection connection = JdbcSqlConnection.getConnection();
		boolean success = false;
		
		try{
			Statement statement = connection.createStatement();
			String update = "INSERT INTO Mentors(student_number, mentored_student) VALUES ("+Mentor_ID+", "+mentored_student+")";
			int result = statement.executeUpdate(update);
			if(result == 1){
				success=true;
			}
		}
		catch(SQLException e){
			System.out.println("An error occured while updating!");
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
		return success;
	}

	
	
	public List<Student> getMentees(){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Student> mentees = new ArrayList<Student>();
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT s.* FROM Mentors m, Students s WHERE m.student_number="+Mentor_ID+" AND s.student_number=m.mentored_student";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				mentees.add(new Student(rs.getInt("student_number"), rs.getNString("first_name"),
					rs.getNString("last_name"), rs.getNString("degree"), rs.getNString("password")));
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
		if(mentees.isEmpty()){
			return null;
		}
		else{
			return mentees;
		}
	}
}
