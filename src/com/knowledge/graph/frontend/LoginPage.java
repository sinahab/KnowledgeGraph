package com.knowledge.graph.frontend;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Student;
import com.knowledge.graph.backend.Students;

public class LoginPage extends Page {
	
	private JPanel background;

	public LoginPage() {
		// Setup box layout
		background = new JPanel(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.anchor = GridBagConstraints.CENTER;
		constraint.ipadx = 10; constraint.ipady = 10;
		
		// Student number field
		JLabel sid_label = new JLabel("Student Number : ", JLabel.RIGHT);
		constraint.gridx = 0; constraint.gridy = 0;
		constraint.gridwidth = 1; constraint.gridheight = 1;
		constraint.weightx = 1; constraint.weighty = 1;
		background.add(sid_label, constraint);
		
		final JTextField sid_field = new JTextField();
		constraint.gridx = 1; constraint.weightx = 2;
		constraint.insets = new Insets(0, 0, 0, 30);
		background.add(sid_field, constraint);
		
		// Password field
		JLabel pass_label = new JLabel("Password : ", JLabel.RIGHT);
		constraint.gridx = 0; constraint.gridy = 1;
		constraint.weightx = 1;
		constraint.insets = new Insets(0, 0, 0, 0);
		background.add(pass_label, constraint);
		
		final JPasswordField pass_field = new JPasswordField();
		constraint.gridx = 1; constraint.weightx = 2;
		constraint.insets = new Insets(0, 0, 0, 30);
		background.add(pass_field, constraint);
		
		// Login buttons panel
		JButton login_button = new JButton("Login");
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String sid_text_field = sid_field.getText();
				if ( isValidSID(sid_text_field)) {
					
					Mainpage.students = new Students();
					Student student = Mainpage.students.searchForStudentBySID( Integer.parseInt( sid_text_field ));
					if ( student == null ) {
						GridBagConstraints c = new GridBagConstraints();
						JLabel SIDNotFoundErrorLabel = new JLabel("Student number not found. Please try again.", JLabel.RIGHT);
						c.gridx = 1; c.gridy = 2;
						c.gridwidth = 1; c.gridheight = 1;
						c.weightx = 1; c.weighty = 0;
						background.add(SIDNotFoundErrorLabel, c);
						background.revalidate();
						background.repaint();
					} else {
						if ( student.checkPassword( new String(pass_field.getPassword()) ) ) {
							Mainpage.student = student;
							Mainpage.indexpage = new IndexPage();
							Mainpage.loginpage.setVisible(false);
							Mainpage.indexpage.setVisible(true);
						} else {
							printPasswordNotFoundError();
						}
					}
				} else {
					printSIDNotValidError();
				}
		   }
	    });
		constraint.anchor = GridBagConstraints.LAST_LINE_START;
		constraint.gridx = 0; constraint.gridy = 4;
		constraint.insets = new Insets(10, 10, 10, 5);
		login_button.setPreferredSize(new Dimension(20,50));
		background.add(login_button, constraint);
		
		JButton cancel_button = new JButton("Cancel");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			   Mainpage.homepage.setVisible(true);
			   Mainpage.loginpage.setVisible(false);
			   Mainpage.loginpage = null;
		   }
	    });
		constraint.anchor = GridBagConstraints.LAST_LINE_END;
		constraint.gridx = 1;
		constraint.insets = new Insets(5, 10, 10, 10);
		cancel_button.setPreferredSize(new Dimension(20,50));
		background.add(cancel_button, constraint);
		
		this.add(background);
	}
	
	private boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
	
	private boolean isValidSID(String s) {
		if ( s!="" && isInteger(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	private void printSIDNotValidError() {
		GridBagConstraints c = new GridBagConstraints();
		JLabel SIDNotValidErrorLabel = new JLabel("Please enter a valid student number with 8 digits.", JLabel.RIGHT);
		c.gridx = 1; c.gridy = 2;
		c.gridwidth = 1; c.gridheight = 1;
		c.weightx = 1; c.weighty = 0;
		background.add(SIDNotValidErrorLabel, c);
		background.revalidate();
		background.repaint();
	}
	
	private void printPasswordNotFoundError() {
		GridBagConstraints c = new GridBagConstraints();
		JLabel passErrorLabel = new JLabel("Wrong password. Please try again.", JLabel.RIGHT);
		c.gridx = 1; c.gridy = 3;
		c.gridwidth = 1; c.gridheight = 1;
		c.weightx = 1; c.weighty = 0;
		background.add(passErrorLabel, c);
		background.revalidate();
		background.repaint();
	}
}
