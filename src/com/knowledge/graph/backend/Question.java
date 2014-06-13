package com.knowledge.graph.backend;

public class Question {

	String text ="";
	int question_id;
	int student_id;
	int concept_id;
	
	public Question(String text, int question_id, int student_id, int concept_id ){
		this.text = text;
		this.question_id = question_id;
		this.student_id = student_id;
		this.concept_id = concept_id;
	}
	

	public String getText(){
		return text;
	}
	
	public int getQuestion_ID(){
		return question_id;
	}
	
	public int getConcept_ID(){
		return concept_id;
	}
	
	public int getStudent_ID(){
		return student_id;
	}
}
