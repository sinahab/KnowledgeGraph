package com.knowledge.graph.backend;
import java.util.*;
import java.sql.*;

public class Answers {
	
	public List<Answer> getAnswerBySID(int sid){
		
		Connection connection = JdbcSqlConnection.getConnection();
		List<Answer> tied_answers = new ArrayList<Answer>();
		try{
			String query = "SELECT * FROM TiedAnswers WHERE student_number ="+sid;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				tied_answers.add(new Answer(rs.getNString("text"),rs.getInt("student_number"),
						rs.getInt("q_id"), rs.getNString("status"), rs.getInt("a_id")));
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
		if(tied_answers.isEmpty())
			return null;
		else{
			return tied_answers;
			}
	}// end getAnswerBySID
	
	public Answer getAnswerByA_ID(int a_id){
		Connection connection = JdbcSqlConnection.getConnection();
		Answer answer = null;
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM TiedAnswers WHERE a_id="+a_id;
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()){
				answer = new Answer(rs.getNString("text"),rs.getInt("student_number"),
						rs.getInt("q_id"), rs.getNString("status"), rs.getInt("a_id"));
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
		return answer;
	}
	
	public boolean addAnswer(int q_id, String text, int student_number){
		boolean success = false;
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			Statement statement = connection.createStatement();
			String status = "\"pending\"";
			String update = "INSERT INTO TiedAnswers( text, q_id, status, student_number) VALUES (\""+
							text+"\", "+q_id+", \""+status+"\", "+student_number+");";
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
	
	public List<Integer> getUnapprovedMenteeAnswersByMentorID(int mentor_id ){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Integer> unapproved_answers = new ArrayList<Integer>();
		try{
			String query = "SELECT a.* FROM TiedAnswers a, Mentors m WHERE a.student_number=m.mentored_student AND a.status<>\"approved\""
					+ " AND m.student_number="+mentor_id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				unapproved_answers.add(new Integer(rs.getInt("a_id")));
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
		if(unapproved_answers.isEmpty())
			return null;
		else{
			return unapproved_answers;
		}
	}
	
	
	
}
