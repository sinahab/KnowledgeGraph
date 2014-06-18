package com.knowledge.graph;

import javax.swing.SwingUtilities;

import com.knowledge.graph.backend.Answers;
import com.knowledge.graph.backend.Concepts;
import com.knowledge.graph.backend.Mentors;
import com.knowledge.graph.backend.Questions;
import com.knowledge.graph.backend.Student;
import com.knowledge.graph.backend.Students;
import com.knowledge.graph.backend.Subjects;
import com.knowledge.graph.backend.Topics;
import com.knowledge.graph.frontend.Homepage;
import com.knowledge.graph.frontend.IndexPage;
import com.knowledge.graph.frontend.LoginPage;
import com.knowledge.graph.frontend.SignupPage;
import com.knowledge.graph.frontend.panel.QuestionsPanel;
	
public class Mainpage {
	
	public static Homepage homepage;
	public static LoginPage loginpage;
	public static SignupPage signuppage;
	public static IndexPage indexpage;
	public static QuestionsPanel questionspage;
	
	public static Answers answers;
	public static Concepts concepts;
	public static Questions questions;
	public static Students students;
	public static Mentors mentors;
	public static Subjects subjects;
	public static Topics topics;
	
	public static Student student;
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	homepage = new Homepage();
	        	homepage.setVisible(true);
	        }
	    });
	}
	
	public static Students getStudents() {
		if (students == null) {
			students = new Students();
		}
		return students;
	}
	
	public static Mentors getMentors() {
		if (mentors == null) {
			mentors = new Mentors();
		}
		return mentors;
	}
	
	public static Questions getQuestions() {
		if (questions == null) {
			questions = new Questions();
		}
		return questions;
	}
	
	public static Concepts getConcepts() {
		if (concepts == null) {
			concepts = new Concepts();
		}
		return concepts;
	}
	
	public static Subjects getSubjects() {
		if (subjects == null) {
			subjects = new Subjects();
		}
		return subjects;
	}
	
	public static Topics getTopics() {
		if (topics == null) {
			topics = new Topics();
		}
		return topics;
	}
}