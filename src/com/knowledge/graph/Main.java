package com.knowledge.graph;

import javax.swing.SwingUtilities;

	
public class Main {
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            Homepage homepage = new Homepage();
	            homepage.setVisible(true);
	        }
	    });
	}
}