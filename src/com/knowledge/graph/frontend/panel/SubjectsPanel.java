package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Subject;
import com.knowledge.graph.frontend.IndexPage;

public class SubjectsPanel extends DirectoryPanel {
	// Subject panels displays all subjects thus has no ID
	private int selected_index;
	private List<Subject> subjects;
	
	public SubjectsPanel() {
		super("Subjects", "This is a list of all subjects");
		selected_index = -1;
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		add_name.setToolTipText("Name of new subject");
		add_field.setToolTipText("Description of new subject");
		generateGUI();
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel list = new DefaultListModel();
		this.subjects = Mainpage.getSubjects().getAllSubjects();
		for (int i = 0; i < this.subjects.size(); i++ ) {
			list.addElement(new NodeWrapper(this.subjects.get(i).getName(),
					this.subjects.get(i).getID()));
		}
		return list;
	}

	@Override
	protected void goAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		
		// Add new card
		IndexPage.topic = new TopicsPanel(node.getID());
		IndexPage.cards.add(IndexPage.topic, "Topic");
		CardLayout c = (CardLayout) IndexPage.cards.getLayout();
		c.show(IndexPage.cards, "Topic");
		
		// Add new navi button
		IndexPage.topics_button = new JButton(node.getName());
		IndexPage.topics_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) IndexPage.cards.getLayout();
				cl.show(IndexPage.cards, "Topic");
				if (IndexPage.concepts_button != null) {
					IndexPage.nav_bar.remove(IndexPage.concepts_button);
				}
				if (IndexPage.concepts_spacer != null) {
					IndexPage.nav_bar.remove(IndexPage.concepts_spacer);
				}
				if (IndexPage.questions_button != null) {
					IndexPage.nav_bar.remove(IndexPage.questions_button);
				}
				if (IndexPage.questions_button != null) {
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
		IndexPage.nav_bar.add(IndexPage.topics_button);
		IndexPage.nav_bar.revalidate();
	}

	@Override
	protected void deleteAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		Mainpage.getSubjects().deleteSubjectByID( node.getID() );

		// repainting the GUI
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
	
	@Override
	protected void addAction() {
		// Adds a new subject to the list of subjects
		String title = add_name.getText();
		String body = add_field.getText();
		Mainpage.getSubjects().addSubject(title, body);

		redrawMeatPanel();
	}
	
}
