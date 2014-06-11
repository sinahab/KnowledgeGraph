package com.knowledge.graph;

import javax.swing.SwingUtilities;

	
public class Main {
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	Page page = new Page();
	        	page.add(new IndexPage());
	            page.setVisible(true);
	        }
	    });
	}
}