package com.knowledge.graph.backend;

import java.sql.*;

public class JdbcSqlConnection {

	public static final String url = "jdbc:mysql://localhost:3306/KnowledgeGraph";
	public static final String user = "root";
	private static final String password = "h3nri3tta";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	//static refernce to itself 
	public static JdbcSqlConnection instance = new JdbcSqlConnection();
	
	private JdbcSqlConnection(){
		//Loads the jdbc mySQL driver 
		try{
			Class.forName(DRIVER_CLASS);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Connection createConnection(){
		
		Connection con = null;
		//Attempt to establish a connection to the database
		try{
			con = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e){
			System.out.println("Error: Can't establish a connection to the database.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(con!=null){
			System.out.println("Connection succesful!");
		}
		return con;		
	}
	
	
	
	
	
	public static Connection getConnection(){
		return instance.createConnection();
	}
}
