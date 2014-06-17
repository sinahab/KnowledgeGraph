package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import com.knowledge.graph.frontend.IndexPage;

public class SubjectsPanel extends DirectoryPanel {
	// Subject panels displays all subjects thus has no ID
	private int selected_index;
	
	public SubjectsPanel() {
		super("Subjects");
		selected_index = -1;
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel list = new DefaultListModel();
		list.addElement(new NodeWrapper("Math", 1));
		list.addElement(new NodeWrapper("Science", 2));
		list.addElement(new NodeWrapper("Coffee", 3));
		return list;
	}

	@Override
	protected String getDescription() {
		return "This is a list of all subjects";
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
		IndexPage.nav_bar.add(new JButton(node.getName()));
		IndexPage.nav_bar.revalidate();
	}
}
