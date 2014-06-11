package com.knowledge.graph.frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Homepage extends JPanel {
	
	public Homepage() {
		
		// Create top panel for words
		JPanel topPanel = new JPanel();		
	    
		// space component
        JLabel spacer = new JLabel(" ");
		
		// adding the title component
		JLabel title = new JLabel("Welcome to the Knowledge Base!");
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		// adding the subtitle
		JLabel subtitle = new JLabel("Knowledge by students for students ...");
		subtitle.setFont(new Font("Helvetica", Font.BOLD, 18));

		// adding components to the panel
		topPanel.add(title);
		topPanel.add(spacer);
		topPanel.add(subtitle);
		
		// Bottom panel
		JPanel bottomPanel = new JPanel();
		
		// Create buttons for bottom panel
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);
	          }
	       });
		JButton signupButton = new JButton("Signup");
		bottomPanel.add(loginButton);
		bottomPanel.add(signupButton);
		
		// Add top and bottom panels to homepage
		add(topPanel);
		add(bottomPanel);
	}	
	
}