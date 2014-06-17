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
		return tied_answers;
	}// end getAnswerBySID
	
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
