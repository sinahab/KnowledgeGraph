package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;

public class Concepts {
	
	public Concept getConceptByID(int concept_id){
		Connection connection = JdbcSqlConnection.getConnection();
		Concept concept = null;
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM BelongedConcepts WHERE c_id="+concept_id;
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			concept = new Concept(rs.getInt("c_id"), rs.getNString("c_name"),rs.getNString("description"),
					rs.getInt("t_id"));		
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
		return concept;
	}
	
	public List<Question> getTiedQuestions(int c_id){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Question> tied_questions = new ArrayList<Question>();
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM AskedConceptQuestions WHERE c_id="+c_id;
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				tied_questions.add(new Question(rs.getNString("text"), rs.getInt("q_uid"),
						rs.getInt("student_number"),rs.getInt("c_id")));
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
		return tied_questions;
	}
	
}