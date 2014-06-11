package com.knowledge.graph;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Homepage extends Page {
	
	public Homepage() {

		// setting up the panel
		JPanel panel = new JPanel();
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
		// space component
        JLabel spacer = new JLabel(" ");
		
		// adding the title component
		JLabel title = new JLabel("Welcome to the Knowledge Base!");
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		// adding the subtitle
		JLabel subtitle = new JLabel("Knowledge by students for students ...");
		subtitle.setFont(new Font("Helvetica", Font.BOLD, 18));

		// adding components to the panel
		panel.add(title);
		panel.add(spacer);
		panel.add(subtitle);
	}	
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            Homepage homepage = new Homepage();
	            homepage.setVisible(true);
	        }
	    });
	}
	
}