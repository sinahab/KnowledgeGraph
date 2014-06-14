package com.knowledge.graph.backend;
import java.sql.*;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		Questions q = new Questions();
		List<Question> list = q.searchQuestionsBySID(19392125);
		Iterator<Question> iterate = list.iterator();
		
		while(iterate.hasNext()){
			Question q1 = iterate.next();
			System.out.println(q1.getQuestion_ID());
			System.out.println(q1.getConcept_ID());
			System.out.println(q1.getStudent_ID());
			System.out.println(q1.getText()+"\n");
		}
	}

}