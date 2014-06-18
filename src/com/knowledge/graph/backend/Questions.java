package com.knowledge.graph.backend;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class Questions {
	

	//The question's numeric id will be auto-generated by SQL
	public boolean addQuestion(String question_text, int student_id, int c_id){
		Connection connection = JdbcSqlConnection.getConnection();
		boolean success = false;
		try{
			String query = "INSERT INTO AskedConceptQuestions (text, student_number, c_id)"
					+ "VALUES ("+question_text + "," + student_id + "c_id" + ");";
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(query);
			if(result==1)
				success = true;
		}
		catch(SQLException e){
			System.out.println("Insertion was unsuccesful!");
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
		return success;
	}//end addQuestion
	
	public Question searchQuestionByID(int q_id){
		Connection connection = JdbcSqlConnection.getConnection();
		Question question = null;
		try{
			String query = "SELECT * FROM AskedConceptQuestions WHERE q_id="+q_id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if(rs.next())
				question = new Question(rs.getNString("text"),rs.getInt("q_id"), rs.getInt("student_number"), rs.getInt("c_id"));
		}
		catch(SQLException e){
			System.out.println("An error occured while searching!");
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
		return question;
	}// end searchQuestionByID
	
	public List<Question> searchQuestionsBySID(int student_id){
		List<Question> q_list = new ArrayList<Question>();
		Connection connection = JdbcSqlConnection.getConnection();
		try{
			String query = "SELECT * FROM AskedConceptQuestions WHERE student_number ="+student_id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				Question question = new Question(rs.getNString("text"), rs.getInt("q_id"), rs.getInt("student_number"),
												rs.getInt("c_id"));
				q_list.add(question);
			}
		}
		catch(SQLException e){
			e.printStackTrace(); //Placeholder, will modify later
		}
		finally{
			if(connection!=null)
				try{
					connection.close();
				}
				catch(SQLException e){
					e.printStackTrace(); //Placeholder, will modify later
				}
		}
		if(q_list.isEmpty())
			return null;
		else{
			return q_list;
			}
	}//end searchQuestionsBySID
	
	
	/**
	 * 
	 * @return A list containing students who have asked at least one question in every concept
	 */
	public List<Student> getStudentEveryConcept(){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Student> students = new ArrayList<Student>();
		
		try{
			Statement statement = connection.createStatement();
			//returns the number of concepts excluding "others"
			String subquery = "(SELECT COUNT(*) FROM BelongedConcepts WHERE c_id<>1)";
			String query = "SELECT s.* FROM AskedConceptQuestions t, Students s WHERE t.student_number=s.student_number"
					      +" GROUP BY t.student_number HAVING count(DISTINCT t.c_id)="+subquery;
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				students.add(new Student(rs.getInt("student_number"), rs.getNString("first_name"),
						rs.getNString("last_name"),rs.getNString("degree"),rs.getNString("password")));
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
		if(students.isEmpty())
			return null;
		else{
			return students;
			}
	}//end getStudentEveryConcept
	
	public List<Student> getAskedMostQuestions(){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Student> students = new ArrayList<Student>();
		try{
			Statement statement = connection.createStatement();
			//Finds the number of questions answered for each student_number
			String subquery = "(SELECT count(*) FROM AskedConceptQuestions GROUP BY student_number)";
			String query = "SELECT Distinct s.* "
					+ "FROM Students s, AskedConceptQuestions t "
					+ "WHERE s.student_number=t.student_number "
					+ "GROUP BY t.student_number "
					+ "HAVING count(*)>=ALL "+subquery;
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				students.add(new Student(rs.getInt("student_number"),rs.getNString("first_name"),rs.getNString("last_name"),
					rs.getNString("degree"),rs.getNString("password")));
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
		if(students.isEmpty())
			return null;
		else{
			return students;
			}
	}
	
	public List<Student> getAskedLeastQuestions(){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Student> students = new ArrayList<Student>();
		try{
			Statement statement = connection.createStatement();
			//Finds the number of questions answered for each student_number
			String subquery = "(SELECT count(*) FROM AskedConceptQuestions GROUP BY student_number)";
			String query = "SELECT Distinct s.* "
					+ "FROM Students s, AskedConceptQuestions t "
					+ "WHERE s.student_number=t.student_number "
					+ "GROUP BY t.student_number "
					+ "HAVING count(*)<=ALL "+subquery;
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				students.add(new Student(rs.getInt("student_number"),rs.getNString("first_name"),rs.getNString("last_name"),
					rs.getNString("degree"),rs.getNString("password")));
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
		if(students.isEmpty())
			return null;
		else{
			return students;
			}
	}
	
	public List<Question> findMostRecentQuestion(){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Question> questions = new ArrayList<Question>();
		
		try{
			Statement statement = connection.createStatement();
			String subquery = "(SELECT MIN(create_date) FROM AskedConceptQuestions)";
			String query = "SELECT * FROM AskedConceptQuestions WHERE create_date= "+subquery;
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				questions.add(new Question(rs.getNString("text"), rs.getInt("q_id"), rs.getInt("student_number"),
						rs.getInt("c_id")));
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
		if(questions.isEmpty())
			return null;
		else{
			return questions;
			}
	}
	
	
	
}
