package com.knowledge.graph.backend;

public class Concept {
	int concept_id;
	String concept_name;
	String description;
	int topic_id;
	
	public Concept(int c_id, String c_name, String description, int t_id){
		concept_id = c_id;
		concept_name = c_name;
		this.description = description;
		topic_id = t_id;
	}
	
	public int getTopic_ID(){
		return topic_id;
	}
	
	public int getConcept_ID(){
		return concept_id;
	}
	
	public String getName(){
		return concept_name;
	}
	
	public String getDescription(){
		return description;
	}
}
