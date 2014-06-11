package com.knowledge.graph.frontend;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.knowledge.graph.Mainpage;

public class SignupPage extends Page {

	public SignupPage() {
		// Setup box layout
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
		
		// Create student number panel
		JLabel sidLabel = new JLabel("Student Number: ");
		JFormattedTextField sidField = new JFormattedTextField();
		JPanel sidPanel = new JPanel(new GridLayout(1,0));
		sidPanel.add(sidLabel);
		sidPanel.add(sidField);
		
		// Create first name panel
		JLabel fnameLabel = new JLabel("First name: ");
		JFormattedTextField fnameField = new JFormattedTextField();
		JPanel fnamePanel = new JPanel(new GridLayout(1,0));
		sidPanel.add(fnameLabel);
		sidPanel.add(fnameField);
		
		// Create last name panel
		JLabel lnameLabel = new JLabel("Last name: ");
		JFormattedTextField lnameField = new JFormattedTextField();
		JPanel lnamePanel = new JPanel(new GridLayout(1,0));
		sidPanel.add(lnameLabel);
		sidPanel.add(lnameField);
		
		// Create last name panel
		JLabel degLabel = new JLabel("Degree: ");
		JFormattedTextField degField = new JFormattedTextField();
		JPanel degPanel = new JPanel(new GridLayout(1,0));
		sidPanel.add(degLabel);
		sidPanel.add(degField);

		// Password panel
		JLabel passLabel = new JLabel("Password :");
		JFormattedTextField passField = new JFormattedTextField();
		JPanel passPanel = new JPanel(new GridLayout(1,0));
		passPanel.add(passLabel);
		passPanel.add(passField);
		
		// Login buttons panel
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			   Mainpage.indexpage = new IndexPage();
			   Mainpage.signuppage.setVisible(false);
			   Mainpage.indexpage.setVisible(true);
		   }
	    });
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			   Mainpage.homepage.setVisible(true);
			   Mainpage.signuppage.setVisible(false);
			   Mainpage.signuppage = null;
		   }
	    });
		JPanel buttonPanel = new JPanel(new GridLayout(1,0));
		buttonPanel.add(loginButton);
		buttonPanel.add(cancelButton);
		
		boxPanel.add(sidPanel);
		boxPanel.add(fnamePanel);
		boxPanel.add(lnamePanel);
		boxPanel.add(degPanel);
		boxPanel.add(passPanel);
		boxPanel.add(buttonPanel);
		
		this.add(boxPanel);
	}
}
