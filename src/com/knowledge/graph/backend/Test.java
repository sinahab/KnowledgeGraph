package com.knowledge.graph.backend;

import java.util.*;


public class Test {

	public static void main(String[] args) {
		
		Subjects subjects = new Subjects();
		Topics topics = new Topics();
		Concepts concepts = new Concepts();
		Questions questions = new Questions();
		Students students = new Students();
		Mentors mentors = new Mentors();
		
		//Populate the Subjects table with subjects
		subjects.addSubject("Other", "Storage for orphaned topics");
		subjects.addSubject("Computer Science", "Computation, math, machines etc");
		subjects.addSubject("Chemistry", "Breaking bad");
		subjects.addSubject("Culinary Arts", "The art of cooking, baking etc");
		
		//populate the BelongedTopics table with topics
		topics.addTopic("Other", "Storage for orphaned concepts", 1);
		topics.addTopic("Databases", "Placeholder 1", 2);
		topics.addTopic("Algorithms", "Placeholder 2", 2);
		topics.addTopic("Machine Learning", "Placeholder 3", 2);
		topics.addTopic("Organic Chemistry","Placeholder 4", 3);
		topics.addTopic("Chemistry placeholder 1", "Placeholder 5", 3);
		topics.addTopic("Deep Frying", "Art of deep frying", 4);
		topics.addTopic("Dessert", "Dessert specialization", 4);
		
		//populate the BelongedConcepts table with concepts
		concepts.addConcept(1, "Others", "Storage for orphaned questions");
		concepts.addConcept(2, "ER diagrams", "Database placeholder 1");
		concepts.addConcept(2, "Functional Dependencies", "A determines B");
		concepts.addConcept(2, "SQL queries", "SELECT * FROM....");
		concepts.addConcept(2, "Optimization", "Fast and furious queris");
		concepts.addConcept(2, "ORM", "Object relational mapping, making databases easier");
		
		//populate the Students table with students
		students.addStudent("Cze Wen", "Goh", "CPSC",19392125, "QWERTY");
		students.addStudent("Yu Yang", "Lai", "ENPH",32084097, "ilikecurry");
		students.addStudent("Chun Han", "Yen", "CPEN",46644100, "12345");
		students.addStudent("Sina", "Habibian", "ENPH",75704098, "OpenUp");
		students.addStudent("Bob", "Number one", "CHEM",11119999, "password");
		
		//populate the askedconceptquestions table with questions
		
		questions.addQuestion("Why diamonds not hexagons?", 19392125, 2);
		questions.addQuestion("MySQL vs SQLplus", 19392125, 4);
		questions.addQuestion("How do I optimize?", 19392125, 5);
		questions.addQuestion("What does ORM stand for?", 19392125, 6);
		questions.addQuestion("What does BCNF stand for?", 19392125, 3);
		questions.addQuestion("What does ER stand for in a ER diagram", 11119999, 2);
		questions.addQuestion("What shape is an entity?", 11119999, 2);
		
		mentors.addMentor(75704098, 19392125);
		mentors.addMentor(75704098, 46644100);
		
		
		//Demo and testing of nested aggregation, aggregation, division and update queries
		
		//A division query that returns students who have answered a question in every concept 
		
		
		
		
		
	}

}
