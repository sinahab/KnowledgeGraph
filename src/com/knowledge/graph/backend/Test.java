package com.knowledge.graph.backend;

import java.util.*;

import static org.junit.Assert.*;




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
		questions.addQuestion("What does BCNF stand for?", 19392125, 3);
		questions.addQuestion("MySQL vs SQLplus", 19392125, 4);
		questions.addQuestion("How do I optimize?", 19392125, 5);
		questions.addQuestion("What does ORM stand for?", 19392125, 6);
		questions.addQuestion("What does ER stand for in a ER diagram", 11119999, 2);
		questions.addQuestion("What shape is an entity?", 11119999, 2);
		
		questions.addQuestion("Question placeholder 1", 32084097, 2);
		questions.addQuestion("Question placeholder 2", 32084097, 3);
		questions.addQuestion("Question placeholder 3", 32084097, 4);
		questions.addQuestion("Question placeholder 4", 32084097, 5);
		questions.addQuestion("Question placeholder 5", 32084097, 6);
		
		mentors.addMentor(19392125, 75704098);
		mentors.addMentor(75704098, 46644100);
		
		
		//Demo and testing of nested aggregation, aggregation, division and update queries
		
		//A division and nest aggregation query with group by that returns students who have asked a question in every concept 
		System.out.println("----------------TEST 1 Students who answered a question in every concept------------------------------");
		System.out.println("\nNested Aggregation query with group by");
		List<Student> students_everyconcept = questions.getStudentEveryConcept();
		Iterator<Student> iterate = students_everyconcept.iterator();
		
		Student student = iterate.next();
		assertEquals(student.getStudentID(), 19392125);
		System.out.println("\n"+student.toString()+"\n");
		
		student = iterate.next();
		assertEquals(student.getStudentID(), 32084097);
		System.out.println(student.toString()+"\n");
		
		//An aggregation query that returns the most recent question
		System.out.println("----------------TEST 3 Most recent question (Aggregation query)------------------------------");
		questions.addQuestion("Question placeholder 1", 19392125, 5);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		questions.addQuestion("Most recent question", 19392125, 6);
		List<Question> q_list = questions.findMostRecentQuestion();
		Iterator<Question> iterate_q = q_list.iterator();
		
		Question question = iterate_q.next();
		System.out.println(question.getText());
		assertTrue(question.getText().equals("Most recent question"));
		assertEquals(question.getStudent_ID(), 19392125);
		System.out.println("\nQuestion text: "+question.getText()+"\nStudent Number: "+question.getStudent_ID()+"\n");
		
		//A nested aggregation query that returns students who have asked the most number of questions (they might have the same amount of questions
		//asked)
		System.out.println("----------------TEST 4 Least/Most questions asked (aggregation query)------------------------------");
		System.out.println("\nStudents who asked the most number of questions:\n" );
		questions.addQuestion("Before the equalizer", 32084097, 5);
		questions.addQuestion("The equalizer", 32084097, 6);
		List<Student> most_questions = questions.getAskedMostQuestions();
		Iterator<Student> iterate_s = most_questions.iterator();
		
		student = iterate_s.next();
		assertEquals(student.getStudentID(), 19392125);
		System.out.println(student.toString()+"\n");
		student = iterate_s.next();
		assertEquals(student.getStudentID(), 32084097);
		System.out.println("\n"+student.toString()+"\n");
		
		//A nested aggregation query that returns students who have asked the least number of questions (they might have the same amount of questions
		//asked)
		System.out.println("Students who asked the least number of questions:\n" );
		List<Student> least_questions = questions.getAskedLeastQuestions();
		Iterator<Student> iterate_s2 = least_questions.iterator();
		student = iterate_s2.next();
		assertEquals(student.getStudentID(), 11119999);
		System.out.println("\n"+student.toString()+"\n");
	
		System.out.println("----------------TEST 5 Approving Answer Update query------------------------------");
		Mentors m1 = new Mentors();
		Answers as1 = new Answers();
		as1.addAnswer(5, "Insert answer here: ", 19392125);
		Answer a1 = as1.getAnswerByA_ID(1);
		System.out.println(a1.getStatus());
		System.out.println("answer ID: " + a1.getAnswerID() + " answer text: " + a1.getText() + " answer status: " + a1.getStatus());
		System.out.println("updating answer with a_id = 1....");
		
		m1.approveAnswer(a1.getAnswerID(), 75704098);
		a1 = as1.getAnswerByA_ID(1);

		assertTrue(a1.getStatus().equals("approved"));
		System.out.println("After approval: \n");
		System.out.println("answer ID: " + a1.getAnswerID() + " answer text: " + a1.getText() + " answer status: " + a1.getStatus());
		
	}

}
