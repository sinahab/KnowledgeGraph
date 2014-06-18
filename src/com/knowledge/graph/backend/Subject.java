package com.knowledge.graph.backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Subject {
	 
	int subject_id;
	String subject_name;
	String description;
	
	public Subject(int subject_id, String subject_name, String description){
		this.subject_id = subject_id;
		this.subject_name = subject_name;
		this.description = description;
	}
	
	public String getName(){
		return subject_name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getID(){
		return subject_id;
	}
	
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
	
	public boolean updateDescriptionWithSID(String new_description, int s_id){
		boolean success = false;
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			Statement statement = connection.createStatement();
			String update = "UPDATE Subjects SET description=\""+new_description+"\" WHERE s_id="+s_id;
			int result  = statement.executeUpdate(update);
			if(result==1)
				success = true;
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
		return success;
	}//end updateDescriptionWithSID

}
