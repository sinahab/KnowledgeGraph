package com.knowledge.graph.backend;

public class Answer {
		
	String text;
	int student_number;
	int q_id;
	String status;
	
	public Answer(String text, int student_number, int q_id, String status){
		this.text = text;
		this.student_number = student_number;
		this.q_id = q_id;
		this.status = status;
	}
	
	public int getStudent_number(){
		return student_number;
	}
	
	public int getTiedQuestionID(){
		return q_id;
	}
	
	public String getStatus(){
		return status;
	}
	
	public String getText(){
		return text;
	}
}
