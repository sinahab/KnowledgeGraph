package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Subject;
import com.knowledge.graph.frontend.IndexPage;

public class SubjectsPanel extends DirectoryPanel {
	// Subject panels displays all subjects thus has no ID
	private int selected_index;
	
	public SubjectsPanel() {
		super("Subjects", "This is a list of all subjects");
		selected_index = -1;
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		generateGUI();
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel list = new DefaultListModel();
		List<Subject> allSubjects = Mainpage.getSubjects().getAllSubjects();
		for (int i = 0; i < allSubjects.size(); i++ ) {
			list.addElement(new NodeWrapper(allSubjects.get(i).getName(), allSubjects.get(i).getID()));
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
				IndexPage.nav_bar.revalidate();
				IndexPage.nav_bar.repaint();
			}
		});
		IndexPage.nav_bar.add(IndexPage.topics_button);
		IndexPage.nav_bar.revalidate();
	}

	@Override
	protected void addAction() {
		// TODO Auto-generated method stub
		
	}
	
}
