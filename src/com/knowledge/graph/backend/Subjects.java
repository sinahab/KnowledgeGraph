package com.knowledge.graph.backend;
import java.sql.*;


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
	}//end updateDescriptionWithSID
	
	
	
}
