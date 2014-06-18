package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;

public class Topics {
			
	public void addTopic(String topic_name, String description, int subject_id){
		Connection connection = JdbcSqlConnection.getConnection();
		
		try{
			Statement statement = connection.createStatement();
			String query = "INSERT INTO BelongedTopics(t_name, description, s_id) VALUES ("
					+topic_name+", "+description+", "+subject_id+");";
			statement.executeUpdate(query);
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
	}
	
	public Topic getTopicByID(int t_id){
		Connection connection = JdbcSqlConnection.getConnection();
		Topic topic = null;
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM BelongedTopics WHERE t_id="+t_id+";";
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
			String query = "SELECT * FROM BelongedConcepts WHERE t_id="+topic_id+";";
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
