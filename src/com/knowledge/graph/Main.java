package com.knowledge.graph;

import javax.swing.SwingUtilities;

	
public class Main {
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            IndexPage homepage = new IndexPage();
	            homepage.setVisible(true);
	        }
	    });
	}
}