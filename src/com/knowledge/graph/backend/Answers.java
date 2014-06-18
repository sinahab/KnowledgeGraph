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
						rs.getInt("q_id"), rs.getNString("status")));
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
						rs.getInt("q_id"), rs.getNString("status"));
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
			String update = "INSERT INTO TiedAnswers( text, q_id, status, student_number) VALUES ("+
							text+", "+q_id+", "+status+", "+student_number+");";
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
	
	public Question getTied_Question(int q_id){
		Connection connection = JdbcSqlConnection.getConnection();
		Question question = null;
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM AskedConceptQuestions WHERE q_id="+q_id;
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			question = new Question(rs.getNString("text"), rs.getInt("q_id"), rs.getInt("student_number"),
												rs.getInt("c_id"));
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
		return question;
	}
	/**
	 * 
	 * @param a_id
	 * @return the creation date for the answer that is identified by its ID. Null if no such answer exists
	 */
	public String getAnswerDate(int a_id){
		Connection connection = JdbcSqlConnection.getConnection();
		String date = null;
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT create_date FROM TiedAnswers WHERE a_id="+a_id;
			
			ResultSet rs = statement.executeQuery(query);
			if(rs.next())
				date = rs.getNString("create_date");
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
		return date;
	}
	
	
}
