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
	
	public Subject getSubjectByID(int subject_id){
		Connection connection = JdbcSqlConnection.getConnection();
		Subject subject = null;
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Subjects WHERE s_id="+subject_id;
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
