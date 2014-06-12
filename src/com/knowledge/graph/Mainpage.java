package com.knowledge.graph;

import javax.swing.SwingUtilities;

import com.knowledge.graph.frontend.Homepage;
import com.knowledge.graph.frontend.IndexPage;
import com.knowledge.graph.frontend.LoginPage;
import com.knowledge.graph.frontend.QuestionsPage;
import com.knowledge.graph.frontend.SignupPage;

	
public class Mainpage {
	
	public static Homepage homepage;
	public static LoginPage loginpage;
	public static SignupPage signuppage;
	public static IndexPage indexpage;
	public static QuestionsPage questionspage;
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	homepage = new Homepage();
	        	homepage.setVisible(true);
	        }
	    });
	}
}