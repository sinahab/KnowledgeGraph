package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;

public class Subjects {
	
	public void addSubject(String subject_name, String description){
		Connection connection = JdbcSqlConnection.getConnection();
		
		try{
			Statement statement = connection.createStatement();
			String query = "INSERT INTO Subjects(s_name, description) VALUES ("+subject_name+", "+description
					+")";
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
	
	public void updateDescriptionWithSID(String new_description, int s_id){
		
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			Statement statement = connection.createStatement();
			String query = "UPDATE Subjects SET description="+new_description+" WHERE s_id="+s_id;
			statement.executeUpdate(query);
		}
		catch(SQLException e){
			System.out.println("An error occured while updating or the subject doesn't exist!");
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
	}//end updateDescriptionWithSID
	
	public List<Topic> getTiedTopics(int subject_id){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Topic> tied_topics = new ArrayList<Topic>();
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM BelongedTopics WHERE s_id="+subject_id;
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				tied_topics.add(new Topic(rs.getNString("t_name"),rs.getNString("description"), rs.getInt("t_id"), rs.getInt("s_id")));
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
		if(tied_topics.isEmpty())
			return null;
		else{
			return tied_topics;
			}
	}
	
}
