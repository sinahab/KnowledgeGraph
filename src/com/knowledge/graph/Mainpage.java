package com.knowledge.graph;

import javax.swing.SwingUtilities;

import com.knowledge.graph.backend.Answers;
import com.knowledge.graph.backend.Concepts;
import com.knowledge.graph.backend.Questions;
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
	
	public static Answers dbAnswers;
	public static Concepts dbConcepts;
	public static Questions dbQuestions;
	public static Students dbStudents;
	public static Subjects dbSubjects;
	public static Topics dbTopics;
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	homepage = new Homepage();
	        	homepage.setVisible(true);
	        }
	    });
	}
}