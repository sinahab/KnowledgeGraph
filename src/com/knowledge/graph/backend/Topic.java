package com.knowledge.graph.backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Topic {
	
	int topic_id;
	int subject_id;
	String topic_name;
	String description;
	
	public Topic(String name, String description, int t_id, int s_id){
		topic_id = t_id;
		subject_id = s_id;
		topic_name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.topic_name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Topic getTopicByID(int t_id){
		Connection connection = JdbcSqlConnection.getConnection();
		Topic topic = null;
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM BelongedTopics WHERE t_id="+t_id;
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
	
	public List<Concept> getTiedConcepts(int topic_id){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Concept> tied_concepts = new ArrayList<Concept>();
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM BelongedConcepts WHERE t_id="+topic_id;
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				tied_concepts.add(new Concept(rs.getInt("c_id"), rs.getNString("c_name"), rs.getNString("description"),
						rs.getInt("t_id")));
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
		if(tied_concepts.isEmpty())
			return null;
		else{
			return tied_concepts;
			}
	}

}
