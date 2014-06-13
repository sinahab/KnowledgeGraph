package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;


public class Questions {
	

	//The question's numeric id will be auto-generated by SQL
	public void addQuestion(String question_text, int student_id, int c_id){
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			String query = "INSERT INTO AskedConceptQuestions (text, student_number, c_id)"
					+ "VALUES ("+question_text + "," + student_id + "c_id" + ");";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			
		}
		catch(SQLException e){
			System.out.println("Insertion was unsuccesful!");
			e.printStackTrace();
		}
		finally{
			if(connection!=null)
				try{
					connection.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		}
	}//end addQuestion
	
	public Question searchQuestionByID(int q_id){
		Connection connection = JdbcSqlConnection.getConnection();
		Question question = null;
		try{
			String query = "SELECT * FROM AskedConceptQuestions WHERE q_id="+q_id+";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			question = new Question(rs.getNString("text"),rs.getInt("q_id"), rs.getInt("student_number"), rs.getInt("c_id"));
			
		}
		catch(SQLException e){
			System.out.println("An error occured while searching!");
		}
		finally{
			if(connection!=null)
				try{
					connection.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		}	
		return question;
	}// end searchQuestionByID
	
	public List<Question> searchQuestionsBySID(int student_id){
		List<Question> q_list = new ArrayList<Question>();
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			String query = "SELECT (*) FROM AskedConceptQuestions WHERE s_id="+student_id+";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				Question question = new Question(rs.getNString("text"), rs.getInt("q_id"), rs.getInt("s_id"),
												rs.getInt("c_id"));
				q_list.add(question);
			}
		}
		catch(SQLException e){
			e.printStackTrace(); //Placeholder, will modify later
		}
		finally{
			if(connection!=null)
				try{
					connection.close();
				}
				catch(SQLException e){
					e.printStackTrace(); //Placeholder, will modify later
				}
		}
		return q_list;
	}//end searchQuestionsBySID
}