package com.knowledge.graph.backend;
import java.util.*;
import java.sql.*;
public class Mentor extends Student{
	
	int mentor_ID;
	int mentee_id;
	
	public Mentor(int student_ID, String first_name, String last_name,
				String degree, String password, int mentee_id) {
		super(student_ID, first_name, last_name, degree, password);
		this.mentor_ID = student_ID;
		this.mentee_id = mentee_id;
	}
	
	public boolean addMentee(int mentored_student){
		Connection connection = JdbcSqlConnection.getConnection();
		boolean success = false;
		
		try{
			Statement statement = connection.createStatement();
			String update = "INSERT INTO Mentors(student_number, mentored_student) VALUES ("+mentor_ID+", "+mentored_student+")";
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
			String query = "SELECT s.* FROM Mentors m, Students s WHERE m.student_number="+mentor_ID+" AND s.student_number=m.mentored_student";
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
	
	public boolean approveAnswer(int answer_id, int mentor_id){
		boolean approval_success = false;
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			Statement statement = connection.createStatement();
			String update  = "UPDATE TiedAnswers SET status= 'approved' WHERE a_id="+answer_id+";";		
			int success = statement.executeUpdate(update);
			if(success==1){
				update = "INSERT INTO MentorApproves(a_id, student_number) VALUES("+answer_id+", "+mentor_id+");";
				success = statement.executeUpdate(update);
				//Check whether the approval was succesful
				if(success==1)
					approval_success = true;
			}
		}
		catch(SQLException e){
			System.out.println("An error occured while updating or the answer doesn't exist!");
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
		return approval_success;
	}
}
