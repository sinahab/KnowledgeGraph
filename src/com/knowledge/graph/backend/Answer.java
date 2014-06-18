package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;


public class Answer {
		
	String text;
	int student_number;
	int q_id;
	String status;
	
	public Answer(String text, int student_number, int q_id, String status){
		this.text = text;
		this.student_number = student_number;
		this.q_id = q_id;
		this.status = status;
	}
	
	public int getStudent_number(){
		return student_number;
	}
	
	public int getTiedQuestionID(){
		return q_id;
	}
	
	public String getStatus(){
		return status;
	}
	
	public String getText(){
		return text;
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
	
}
