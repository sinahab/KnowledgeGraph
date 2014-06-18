package com.knowledge.graph.frontend;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Students;

public class SignupPage extends Page {

	private JPanel boxPanel;
	
	public SignupPage() {
		// Setup box layout
		boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
		
		// Create student number panel
		JLabel sidLabel = new JLabel("Student Number: ");
		final JFormattedTextField sidField = new JFormattedTextField();
		JPanel sidPanel = new JPanel(new GridLayout(1,0));
		sidPanel.add(sidLabel);
		sidPanel.add(sidField);
		
		// Create first name panel
		JLabel fnameLabel = new JLabel("First name: ");
		final JFormattedTextField fnameField = new JFormattedTextField();
		JPanel fnamePanel = new JPanel(new GridLayout(1,0));
		sidPanel.add(fnameLabel);
		sidPanel.add(fnameField);
		
		// Create last name panel
		JLabel lnameLabel = new JLabel("Last name: ");
		final JFormattedTextField lnameField = new JFormattedTextField();
		JPanel lnamePanel = new JPanel(new GridLayout(1,0));
		sidPanel.add(lnameLabel);
		sidPanel.add(lnameField);
		
		// Create degree panel
		JLabel degLabel = new JLabel("Degree: ");
		final JFormattedTextField degField = new JFormattedTextField();
		JPanel degPanel = new JPanel(new GridLayout(1,0));
		sidPanel.add(degLabel);
		sidPanel.add(degField);

		// Password panel
		JLabel passLabel = new JLabel("Password :");
		final JFormattedTextField passField = new JFormattedTextField();
		JPanel passPanel = new JPanel(new GridLayout(1,0));
		passPanel.add(passLabel);
		passPanel.add(passField);
				
		// Login buttons panel
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// checking for valid input fields
				if ( isValidSID(sidField.getText()) && 
						isNonEmptyString(fnameField.getText()) &&
						isNonEmptyString(lnameField.getText()) &&
						isNonEmptyString(degField.getText()) &&
						isNonEmptyString(passField.getText()) ) {
					Mainpage.getStudents().addStudent(fnameField.getText(),
							lnameField.getText(),
							degField.getText(),
							Integer.parseInt(sidField.getText()),
							passField.getText());
					Mainpage.indexpage = new IndexPage();
					Mainpage.signuppage.setVisible(false);
					Mainpage.indexpage.setVisible(true);
				} else {
				}
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
	
	private boolean isNonEmptyString(String s) {
		if ( s!="" ) {
			return true;
		} else {
			return false;
		}
	}

}
