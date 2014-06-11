package com.knowledge.graph;

import javax.swing.SwingUtilities;

import com.knowledge.graph.frontend.LoginPage;
import com.knowledge.graph.frontend.Page;

	
public class Main {
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	Page page = new Page();
	        	page.add(new LoginPage());
	            page.setVisible(true);
	        }
	    });
	}
}