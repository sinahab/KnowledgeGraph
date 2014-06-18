package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;

public class Subjects {
	
	public void addSubject(String subject_name, String description){
		Connection connection = JdbcSqlConnection.getConnection();
		
		try{
			Statement statement = connection.createStatement();
			String query = "INSERT INTO Subjects(s_name, description) VALUES (\""+subject_name+"\", \""+description
					+"\")";
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
	
	public List<Subject> getAllSubjects(){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Subject> subjects = new ArrayList<Subject>();
		try{
			Statement statement = connection.createStatement();
			String query ="SELECT * FROM Subjects";
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				subjects.add(new Subject(rs.getInt("s_id"), rs.getNString("s_name"), rs.getNString("description")));
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
		if(subjects.isEmpty())
			return null;
		else{
			return subjects;
			}
		
	}
	
}
