package com.knowledge.graph.backend;
import java.sql.*;
import java.util.*;

public class Topics {
	
	public List<Concept> getTiedConcepts(int topic_id){
		Connection connection = JdbcSqlConnection.getConnection();
		List<Concept> tied_concepts = new ArrayList<Concept>();
		
		try{
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM BelongedConcepts WHERE t_id="+topic_id;
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				tied_concepts.add(new Concept(rs.getInt("c_id"), rs.getNString("c_name"), rs.getNString("description"),
						rs.getInt("t_id")));
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
		return tied_concepts;
	}
	
}
