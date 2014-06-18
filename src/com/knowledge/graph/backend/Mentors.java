package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;


public class Mentors {
	
	public boolean addMentor(int mentored_id, int mentor_id){
		Connection connection = JdbcSqlConnection.getConnection();
		boolean success = false;
		try{
			Statement statement = connection.createStatement();
			String update = "INSERT INTO Mentors(student_number, mentored_student) VALUES("+mentor_id+", "+mentored_id+");";
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
	
	
	public Mentor getMentorByID(int mentor_sid){
		Mentor mentor = null;
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			String query = "SELECT DISTINCT s.*, m.mentored_student FROM Mentors m, Students s WHERE m.student_number = " + mentor_sid + " AND s.student_number = m.student_number;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()){
				mentor = new Mentor(mentor_sid, rs.getNString("first_name"), rs.getNString("last_name"), rs.getNString("degree"),
						rs.getNString("password"),rs.getInt("mentored_student"));
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
		return mentor;
	}

	
	
}
