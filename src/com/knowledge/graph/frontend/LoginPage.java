package com.knowledge.graph.frontend;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginPage extends JPanel {

	public LoginPage() {
		
		// Create student number panel
		JLabel sidLabel = new JLabel("Student Number: ");
		JFormattedTextField sidField = new JFormattedTextField();
		JPanel sidPanel = new JPanel();
		sidPanel.add(sidLabel);
		sidPanel.add(sidField);
		
		JLabel passLabel = new JLabel("Password :");
		JFormattedTextField passField = new JFormattedTextField();
		JPanel passPanel = new JPanel();
		passPanel.add(passLabel);
		passPanel.add(passField);
		
		add(sidPanel);
		add(passPanel);
		
	}
}
