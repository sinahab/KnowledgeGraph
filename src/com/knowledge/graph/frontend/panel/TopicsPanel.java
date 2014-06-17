package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.knowledge.graph.frontend.IndexPage;

public class TopicsPanel extends DirectoryPanel {

	public TopicsPanel(int ID) {
		super("Topic");
		this.ID = ID;
	}

	@Override
	protected String getDescription() {
		return "Mathematical!";
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		model.addElement(new NodeWrapper("Algebra", 1));
		model.addElement(new NodeWrapper("PDEs", 2));
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
		IndexPage.nav_bar.add(new JLabel(" > "));
		IndexPage.nav_bar.add(new JButton(node.getName()));
		IndexPage.nav_bar.revalidate();
	}

}
