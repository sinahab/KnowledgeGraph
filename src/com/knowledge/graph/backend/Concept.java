package com.knowledge.graph.backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Concept {
	private int concept_id;
	String concept_name;
	String description;
	int topic_id;
	
	public Concept(int c_id, String c_name, String description, int t_id){
		concept_id = c_id;
		concept_name = c_name;
		this.description = description;
		topic_id = t_id;
	}
	
	public int getTopic_ID(){
		return topic_id;
	}
	
	public int getConcept_ID(){
		return concept_id;
	}
	
	public String getName(){
		return concept_name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public Topic getTiedTopic(){
		Connection connection = JdbcSqlConnection.getConnection();
		Topic topic = null;
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT t.* FROM BelongedConcepts c, BelongedTopics t WHERE c_id="+concept_id+" AND c.t_id=t.t_id"+";";
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next())
				topic = new Topic(rs.getNString("t_name"),rs.getNString("description"), rs.getInt("t_id"), rs.getInt("s_id"));
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
		return topic;
	}
	
	public List<Question> getTiedQuestions(){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Question> tied_questions = new ArrayList<Question>();
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM AskedConceptQuestions WHERE c_id="+concept_id;
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				tied_questions.add(new Question(rs.getNString("text"), rs.getInt("q_id"),
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
		if(tied_questions.isEmpty())
			return null;
		else{
			return tied_questions;
			}
	}
}
