package com.knowledge.graph.backend;

public class Topic {
	
	int topic_id;
	int subject_id;
	String topic_name;
	String description;
	
	public Topic(String name, String description, int t_id, int s_id){
		topic_id = t_id;
		subject_id = s_id;
		topic_name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.topic_name;
	}
	
	public String getDescription() {
		return this.description;
	}
}
