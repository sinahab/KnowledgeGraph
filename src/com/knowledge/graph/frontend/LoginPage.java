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

	public LoginPage() {
		// Setup box layout
		JPanel background = new JPanel(new GridBagLayout());
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
				Mainpage.dbStudents = new Students();
				Student student = Mainpage.dbStudents.searchForStudentBySID( Integer.parseInt( sid_field.getText()) );
				if ( student.checkPassword( new String(pass_field.getPassword()) ) ) {
					Mainpage.indexpage = new IndexPage();
					Mainpage.loginpage.setVisible(false);
					Mainpage.indexpage.setVisible(true);
				} else {
					
				}
		   }
	    });
		constraint.anchor = GridBagConstraints.LAST_LINE_START;
		constraint.gridx = 0; constraint.gridy = 2;
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
}
