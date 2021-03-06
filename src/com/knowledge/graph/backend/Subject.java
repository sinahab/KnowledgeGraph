package com.knowledge.graph.backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Subject {
	 
	int subject_id;
	String subject_name;
	String description;
	
	public Subject(int subject_id, String subject_name, String description){
		this.subject_id = subject_id;
		this.subject_name = subject_name;
		this.description = description;
	}
	
	public String getName(){
		return subject_name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getID(){
		return subject_id;
	}

}
