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
	
	/**
	 * 
	 * @param mentor_id
	 * @param mendee_id
	 * @return boolean indicating whether a mentorship exists between the 2 given ids
	 */
	public boolean isMentoring(int mentor_id, int mentee_id){
		boolean is_a_mentor = false;
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			Statement statement = connection.createStatement();
			String query  = "SELECT * FROM Mentors WHERE student_number="+mentor_id+" AND mentored_student="+mentee_id;
			ResultSet rs = statement.executeQuery(query);
			if(rs.next())
				is_a_mentor = true;
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
		return is_a_mentor;
	}

	public List<Mentor> mentorsWhoApprovedAnswer(int a_id){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Mentor> mentors  = new ArrayList<Mentor>();
		try{
			Statement statement = connection.createStatement();
			//retrieves the student numbers of mentors who approved this question
			String query  = "SELECT student_number FROM MentorApproves  WHERE a_id="+a_id+";";
			ResultSet rs = statement.executeQuery(query);
			
			List<Integer> IDs = new ArrayList<Integer>();
			while(rs.next()){
				//Store the id of mentors in a list
				IDs.add(new Integer(rs.getInt("student_number")));
			}
			Iterator<Integer> iterate = IDs.iterator();
			while(iterate.hasNext()){
				int ID = iterate.next().intValue();
				String query2 = "SELECT s.*, m.mentored_student FROM Students s, Mentors m WHERE m.student_number="+ID
						+" AND m.student_number=s.student_number;";
				rs = statement.executeQuery(query2);
				if(rs.next())
					mentors.add(new Mentor(ID, rs.getNString("first_name"), rs.getNString("last_name"), rs.getNString("degree"),
						rs.getNString("password"),rs.getInt("mentored_student")));
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
