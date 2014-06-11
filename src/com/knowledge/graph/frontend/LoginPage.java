package com.knowledge.graph.frontend;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginPage extends Page {

	public LoginPage() {
		// Setup box layout
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
		
		// Create student number panel
		JLabel sidLabel = new JLabel("Student Number: ");
		JFormattedTextField sidField = new JFormattedTextField();
		JPanel sidPanel = new JPanel(new GridLayout(1,0));
		sidPanel.add(sidLabel);
		sidPanel.add(sidField);
		
		// Password panel
		JLabel passLabel = new JLabel("Password :");
		JFormattedTextField passField = new JFormattedTextField();
		JPanel passPanel = new JPanel(new GridLayout(1,0));
		passPanel.add(passLabel);
		passPanel.add(passField);
		
		// Login buttons panel
		JButton loginButton = new JButton("Login");
		JButton cancelButton = new JButton("Cancel");
		JPanel buttonPanel = new JPanel(new GridLayout(1,0));
		buttonPanel.add(loginButton);
		buttonPanel.add(cancelButton);
		
		boxPanel.add(sidPanel);
		boxPanel.add(passPanel);
		boxPanel.add(buttonPanel);
		
		add(boxPanel);
		
	}
}
