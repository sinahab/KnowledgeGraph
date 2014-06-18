package com.knowledge.graph.frontend;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.knowledge.graph.Mainpage;

public class Homepage extends Page {
	public Homepage() {
		// Setup box layout
		JPanel background = new JPanel(new GridBagLayout());
		GridBagConstraints back_constr = new GridBagConstraints();
		
		// adding the title component
		JLabel title = new JLabel("Welcome to the Knowledge Base!");
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		// adding the subtitle
		JLabel subtitle = new JLabel("Knowledge by students for students ...");
		subtitle.setFont(new Font("Helvetica", Font.BOLD, 18));

		// adding components to the panel
		back_constr.anchor = GridBagConstraints.PAGE_START;
		back_constr.gridx = 1;
		background.add(title, back_constr);
		back_constr.anchor = GridBagConstraints.CENTER;
		background.add(subtitle, back_constr);
		
		// Bottom panel
		JPanel bottom_panel = new JPanel();
		
		// Create buttons for bottom panel
		JButton login_button = new JButton("Login");
		Font f = login_button.getFont();
		login_button.setFont(new Font(f.getName(), f.getStyle(), 18));
		login_button.setPreferredSize(new Dimension(100,50));
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			   Mainpage.loginpage = new LoginPage();
			   Mainpage.loginpage.setVisible(true);
			   Mainpage.homepage.setVisible(false);
		   }
	    });
		JButton signup_button = new JButton("Signup");
		signup_button.setPreferredSize(new Dimension(100,50));
		signup_button.setFont(new Font(f.getName(), f.getStyle(), 18));
		signup_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			   Mainpage.signuppage = new SignupPage();
			   Mainpage.signuppage.setVisible(true);
			   Mainpage.homepage.setVisible(false);
		   }
	    });

		bottom_panel.add(login_button);
		bottom_panel.add(signup_button);
		
		// Add top and bottom panels to homepage
		back_constr.anchor = GridBagConstraints.PAGE_END;
		background.add(bottom_panel, back_constr);
		add(background);
	}	
	
}