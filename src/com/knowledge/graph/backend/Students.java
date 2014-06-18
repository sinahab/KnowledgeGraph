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
			statement.executeUpdate(query);
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
	}//end addStudent
	
	public void deleteStudentByID(int student_id){
		
		Connection connection = JdbcSqlConnection.getConnection();
		
		try{
			String query = "DELETE FROM Students WHERE student_number = " + student_id+";";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		}
		catch(SQLException e){
			System.out.println("Deletion was unsuccesful!");
			e.printStackTrace();
		}
		finally{
			if(connection!=null)
				try{
					connection.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		}
	}//end deleteStudentByID
	
	public Student searchForStudentBySID(int sid){
		
		Student student = null;
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			String query = "SELECT * FROM Students WHERE student_number = " + sid + ";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()){
				student = new Student(rs.getInt("student_number"), rs.getNString("first_name"),
					rs.getNString("last_name"), rs.getNString("degree"), rs.getNString("password"));
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
		
		return student;
	}
}
