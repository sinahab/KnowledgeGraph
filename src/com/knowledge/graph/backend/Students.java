package com.knowledge.graph.backend;
import java.sql.*;

public class Students {

	public void addStudent(String first_name, String last_name, String degree, 
			int student_ID, String password){
		
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			String query = "INSERT INTO Students (student_number, first_name, last_name, degree, password )"
							+ "VALUES ("+ student_ID+", "+first_name+", "+last_name+", "+degree+","+password+");";
			Statement statement = connection.createStatement();
			statement.execute(query);
		}
		catch(SQLException e){
			System.out.println("Insertion of student was unsuccesful!");
		}
		finally{
			if(connection!=null){
				try{
					connection.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
}