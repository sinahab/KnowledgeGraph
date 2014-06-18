package com.knowledge.graph.backend;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		
		Questions q = new Questions();
		List<Question> list = q.searchQuestionsBySID(19392125);
		Iterator<Question> iterate = list.iterator();
		
		// SINA'S TESTS		
		// Question question = q.searchQuestionsBySID(3);
		
		System.out.println("-------------------------------TEST 1-------------------------------------");
		while(iterate.hasNext()){
			Question q1 = iterate.next();
			System.out.println(q1.getQuestion_ID());
			System.out.println(q1.getConcept_ID());
			System.out.println(q1.getStudent_ID());
			System.out.println(q1.getText()+"\n");
		}
		
		System.out.println("-------------------------------TEST 2-------------------------------------");
		List<Student> list2 = q.getStudentEveryConcept();
		Iterator<Student> iterate2 = null;
			if(list2!=null){
				iterate2 = list2.iterator();
			}
		if(iterate2!=null){
			while(iterate2.hasNext()){
				Student a1 = iterate2.next();
				System.out.println(a1.getFullName());
				System.out.println(a1.getStudentID()+"\n");
			}
		}
		else{
			System.out.println("No such student exists!");
		}
		System.out.println("-------------------------------TEST 3-------------------------------------");
		list2 = q.getAskedMostQuestions();
		iterate2 = null;
		if(list2!=null){
			iterate2 = list2.iterator();
		}
		if(iterate2!=null){
			while(iterate2.hasNext()){
				Student a1 = iterate2.next();
				System.out.println(a1.getFullName());
				System.out.println(a1.getStudentID()+"\n");
			}
		}
	
		System.out.println("-------------------------------TEST 4-------------------------------------");
		q.addQuestion("Placeholder ver2.0", 19392125, 5);
		
	}

}
