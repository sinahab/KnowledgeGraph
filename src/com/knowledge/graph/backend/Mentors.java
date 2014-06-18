package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;


public class Mentors {
	
	public boolean addMentor(int mentored_id, int mentor_id){
		Connection connection = JdbcSqlConnection.getConnection();
		boolean success = false;
		try{
			Statement statement = connection.createStatement();
			String update = "INSERT INTO Mentors(student_number, mentored_student) VALUES("+mentored_id+", "+mentor_id+");";
			int result = statement.executeUpdate(update);
			if(result==1)
				success=true;
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
	
	public boolean deleteMentor(int mentor_id){
		Connection connection = JdbcSqlConnection.getConnection();
		boolean success = false;
		try{
			Statement statement = connection.createStatement();
			String update = "DELETE FROM Mentors WHERE student_number="+mentor_id;
			int result = statement.executeUpdate(update);
			if(result==1)
				success=true;
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
	
	public boolean approveQuestion(int answer_id, int mentor_id){
		boolean approval_success = false;
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			Statement statement = connection.createStatement();
			String update = "INSERT INTO MentorApproves(a_id, student_number) VALUES("+answer_id+", "+mentor_id+");";
			int success = statement.executeUpdate(update);
			if(success==1){
				update = "UPDATE TiedAnswers SET status=approved WHERE a_id"+answer_id+";";
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
