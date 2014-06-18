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
	
	public int getTopicID() {
		return this.topic_id;
	}
	
	public Subject getTiedSubject(){
		Connection connection = JdbcSqlConnection.getConnection();
		Subject subject = null;
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Subjects s, BelongedTopics t WHERE t_id="+topic_id+" AND t.s_id=s.s_id"+";";
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next())
				subject = new Subject(rs.getInt("s_id"), rs.getNString("s_name"), rs.getNString("description"));
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
		return subject;
	}
	
	
}
