package com.knowledge.graph.backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Question {

	String text ="";
	int question_id;
	int student_id;
	int concept_id;
	
	public Question(String text, int question_id, int student_id, int concept_id ){
		this.text = text;
		this.question_id = question_id;
		this.student_id = student_id;
		this.concept_id = concept_id;
	}
	

	public String getText(){
		return text;
	}
	
	public int getQuestion_ID(){
		return question_id;
	}
	
	public int getConcept_ID(){
		return concept_id;
	}
	
	public int getStudent_ID(){
		return student_id;
	}
	
public List<Answer> getTiedAnswers(int q_id){
		
		Connection connection = JdbcSqlConnection.getConnection();
		List<Answer> tied_answers = new ArrayList<Answer>();
		try{
			String query = "SELECT * FROM TiedAnswers WHERE q_id ="+q_id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				tied_answers.add(new Answer(rs.getNString("text"),rs.getInt("student_number"), 
						rs.getInt("q_id"),rs.getNString("status"), rs.getInt("a_id")));
			}
		}
		catch(SQLException e){
			System.out.println("An error occured while searching!");
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
	}//end getTiedAnswers

/**
 * 
 * @param q_id
 * @return the creation date for the question that is identified by its ID. Null if no such answer exists
 */
	public String getQuestionDate(){
		Connection connection = JdbcSqlConnection.getConnection();
		String date = null;
	
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT create_date FROM AskedConceptQuestions WHERE q_id="+question_id;
		
			ResultSet rs = statement.executeQuery(query);
			if(rs.next())
				date = rs.getDate("create_date").toString();
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
