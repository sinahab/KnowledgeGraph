package com.knowledge.graph.frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.knowledge.graph.Mainpage;

public class Homepage extends Page {
	
	public Homepage() {
		// Setup box layout
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
		
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
			   Mainpage.loginpage = new LoginPage();
			   Mainpage.homepage.setVisible(false);
			   Mainpage.loginpage.setVisible(true);
		   }
	    });
		JButton signupButton = new JButton("Signup");
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			   Mainpage.signuppage = new SignupPage();
			   Mainpage.homepage.setVisible(false);
			   Mainpage.signuppage.setVisible(true);
		   }
	    });

		bottomPanel.add(loginButton);
		bottomPanel.add(signupButton);
		
		// Add top and bottom panels to homepage
		boxPanel.add(topPanel);
		boxPanel.add(bottomPanel);
		add(boxPanel);
	}	
	
}