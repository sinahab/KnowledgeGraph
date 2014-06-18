package com.knowledge.graph.frontend;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Mentor;
import com.knowledge.graph.backend.Student;
import com.knowledge.graph.frontend.panel.AnswerPanel;
import com.knowledge.graph.frontend.panel.ConceptsPanel;
import com.knowledge.graph.frontend.panel.QuestionsPanel;
import com.knowledge.graph.frontend.panel.SubjectsPanel;
import com.knowledge.graph.frontend.panel.TopicsPanel;

public class IndexPage extends Page {
	public static JPanel nav_bar;
	public static SubjectsPanel subject;
	public static TopicsPanel topic;
	public static ConceptsPanel concept;
	public static JPanel cards;
	public static QuestionsPanel question;
	public static AnswerPanel answer;
	
	// Navbar buttons
	public static JButton topics_button;
	public static JButton concepts_button;
	public static JLabel concepts_spacer;
	public static JButton questions_button;
	public static JLabel questions_spacer;

	public IndexPage() {
		JPanel background = new JPanel(new BorderLayout());
		
		// Name panel for name and date
		JPanel name_pane = new JPanel(new BorderLayout());
		JLabel name = new JLabel("Welcome " + getStudentName() + "!");
		name_pane.add(name, BorderLayout.LINE_START);
		JLabel date = new JLabel("          " + getDate());
		name_pane.add(date, BorderLayout.CENTER);
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mainpage.student = null;
				Mainpage.homepage = new Homepage();
				Mainpage.indexpage.setVisible(false);
				Mainpage.homepage.setVisible(true);
			}
		});
		name_pane.add(logout, BorderLayout.LINE_END);
		
		// Navigation bar for navigation history
		nav_bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		final JButton home = new JButton("Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Show subject
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.show(cards, "Subject");
				
				// Remove extra buttons from nav bar
				nav_bar.removeAll();
				nav_bar.add(home);
				JLabel arrow_spacer = new JLabel(" > ");
				nav_bar.add(arrow_spacer);
				nav_bar.revalidate();
				nav_bar.repaint();
			}
		});
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
		
		// Meat in the middle
		cards = new JPanel(new CardLayout());
		cards.add(new SubjectsPanel(), "Subject");
		background.add(cards, BorderLayout.CENTER);
		
		add(background);
	}
	
	private String getStudentName() {
		return Mainpage.student.getFullName();
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
		// checking to find mentors.
		if ( Mainpage.student.getMentors() != null) {
			for ( Mentor mentor : Mainpage.student.getMentors() ) {
				mentors.add(new DefaultMutableTreeNode( mentor.getFullName()) );
			}
		}
		// checking to find mentees.
		Mentor mentor =  Mainpage.getMentors().getMentorByID( Mainpage.student.getStudentID() );
		List<Student> student_mentees = mentor.getMentees();

		if ( mentor.getMentees() != null) {
			for ( Student mentee : mentor.getMentees() ) {
				mentees.add(new DefaultMutableTreeNode( mentee.getFullName()) );
			}
		}
	}	
}
