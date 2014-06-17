package com.knowledge.graph.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultMutableTreeNode;

public class IndexPage extends Page {
	public static JPanel nav_bar;

	public IndexPage() {
		JPanel background = new JPanel(new BorderLayout());
		
		// Name panel for name and date
		JPanel name_pane = new JPanel(new BorderLayout());
		JLabel name = new JLabel(getStudentName());
		name_pane.add(name, BorderLayout.LINE_START);
		JLabel date = new JLabel(getDate());
		name_pane.add(date, BorderLayout.LINE_END);
		
		// Navigation bar for navigation history
		nav_bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton home = new JButton("Home");
		nav_bar.add(home);
		JLabel arrow_spacer = new JLabel(" > ");
		nav_bar.add(arrow_spacer);
		
		// Combine name and navipane into namenavipane
		JPanel name_navi_pane = new JPanel(new BorderLayout());
		name_navi_pane.add(name_pane, BorderLayout.PAGE_START);
		name_navi_pane.add(nav_bar, BorderLayout.PAGE_END);
		name_navi_pane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		background.add(name_navi_pane, BorderLayout.PAGE_START);
		
		// Quicklinks pane for special functions
		JPanel quicklinks = new JPanel();
		quicklinks.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		quicklinks.setLayout(new BoxLayout(quicklinks, BoxLayout.PAGE_AXIS));
		JButton my_questions = new JButton("My Questions");
		quicklinks.add(my_questions);
		JButton my_answers = new JButton("My Answers");
		quicklinks.add(my_answers);
		
		background.add(quicklinks, BorderLayout.LINE_START);
		
		// Users pane for students and mentors
	    DefaultMutableTreeNode users = new DefaultMutableTreeNode("Users");
	    populate_users(users);
	    
		JTree user_tree = new JTree(users);
		JScrollPane user_pane = new JScrollPane(user_tree);
		user_pane.setPreferredSize(new Dimension(150,20));
		
		background.add(user_pane, BorderLayout.LINE_END);
		
		add(background);
	}
	
	private String getStudentName() {
		return "Justin Timberlake";
	}
	
	private String getDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    return sdf.format(cal.getTime());
	}
	
	private void populate_users(DefaultMutableTreeNode users) {
		DefaultMutableTreeNode mentors = new DefaultMutableTreeNode("Mentors");
		users.add(mentors);
		DefaultMutableTreeNode mentees = new DefaultMutableTreeNode("Mentees");
		users.add(mentees);
		mentors.add(new DefaultMutableTreeNode("Yoda"));
		mentees.add(new DefaultMutableTreeNode("Obi Wan"));
		mentees.add(new DefaultMutableTreeNode("Luke Skywalker"));
	}
}
