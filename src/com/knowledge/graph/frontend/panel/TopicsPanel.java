package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Subject;
import com.knowledge.graph.backend.Topic;
import com.knowledge.graph.backend.Topics;
import com.knowledge.graph.frontend.IndexPage;

public class TopicsPanel extends DirectoryPanel {
	
	private Subject subject;
	private List<Topic> topics;
	
	public TopicsPanel(int subject_ID) {
		super( Mainpage.getSubjects().getSubjectByID(subject_ID).getName(), 
				Mainpage.getSubjects().getSubjectByID(subject_ID).getDescription());
		this.subject = Mainpage.getSubjects().getSubjectByID(subject_ID);
		this.topics = Mainpage.getSubjects().getTiedTopics(this.ID);
		this.ID = subject_ID;
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		add_name.setToolTipText("Name of new topic");
		add_field.setToolTipText("Description of new topic");
		generateGUI();
	}

	@Override
	protected DefaultListModel generateList() {

		DefaultListModel model = new DefaultListModel();
		System.out.println(this.ID);
		
		List<Topic> topics = Mainpage.getSubjects().getTiedTopics(this.ID);
		if (topics != null) {
			for (int i = 0; i < topics.size(); i++ ) {
				model.addElement( new NodeWrapper(topics.get(i).getName(), topics.get(i).getTopicID()) );
			}
		}
		return model;
	}

	@Override
	protected void goAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		
		// Add new card
		IndexPage.concept = new ConceptsPanel(node.getID());
		IndexPage.cards.add(IndexPage.concept, "Concept");
		CardLayout c = (CardLayout) IndexPage.cards.getLayout();
		c.show(IndexPage.cards, "Concept");
		
		// Add new navi button
		IndexPage.concepts_button = new JButton(node.getName());
		IndexPage.concepts_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) IndexPage.cards.getLayout();
				cl.show(IndexPage.cards, "Concept");
				if (IndexPage.questions_button != null) {
					IndexPage.nav_bar.remove(IndexPage.questions_button);
				}
				if (IndexPage.questions_spacer != null) {
					IndexPage.nav_bar.remove(IndexPage.questions_spacer);
				}
				if (IndexPage.answers_button != null) {
					IndexPage.nav_bar.remove(IndexPage.answers_button);
				}
				if (IndexPage.answers_spacer != null) {
					IndexPage.nav_bar.remove(IndexPage.answers_spacer);
				}
				IndexPage.nav_bar.revalidate();
				IndexPage.nav_bar.repaint();
			}
		});
		IndexPage.concepts_spacer = new JLabel(" > ");
		IndexPage.nav_bar.add(IndexPage.concepts_spacer);
		IndexPage.nav_bar.add(IndexPage.concepts_button);
		IndexPage.nav_bar.revalidate();
	}

	@Override
	protected void addAction() {
		// Adds a new subject to the list of subjects
		String title = add_name.getText();
		String body = add_field.getText();
		Mainpage.getTopics().addTopic(title, body, subject.getID());
		
		// Refresh display
		remove(scroll);
		GridBagConstraints constraint = new GridBagConstraints();
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(500, 200));
		constraint.gridx = 0; constraint.gridy = 3;
		constraint.weightx = 2; constraint.weighty = 10;
		constraint.gridwidth = 2;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(scroll, constraint);
		add_name.setText("");
		add_field.setText("");
		revalidate();
		repaint();
		
	}

}
