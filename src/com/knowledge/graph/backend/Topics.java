package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;

public class Topics {
			
	public void addTopic(String topic_name, String description, int subject_id){
		Connection connection = JdbcSqlConnection.getConnection();
		
		try{
			Statement statement = connection.createStatement();
			String query = "INSERT INTO BelongedTopics(t_name, description, s_id) VALUES ("
					+topic_name+", "+description+", "+subject_id+")";
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
}
