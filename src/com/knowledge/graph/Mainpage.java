package com.knowledge.graph;

import javax.swing.SwingUtilities;

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
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	homepage = new Homepage();
	        	homepage.setVisible(true);
	        }
	    });
	}
}