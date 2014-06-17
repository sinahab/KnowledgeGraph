package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.knowledge.graph.frontend.IndexPage;

public class ConceptsPanel extends DirectoryPanel {

	public ConceptsPanel(int ID) {
		super("Concept");
		this.ID = ID;
	}

	@Override
	protected String getDescription() {
		return "Algebra is such a fun concept!";
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		model.addElement(new NodeWrapper("Why study Algebra?", 1));
		model.addElement(new NodeWrapper("How do I solve x = 1?", 2));
		return model;
	}

	@Override
	protected void goAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		
		// Add new card
		IndexPage.question = new QuestionsPanel(node.getID());
		IndexPage.cards.add(IndexPage.question, "Question");
		CardLayout c = (CardLayout) IndexPage.cards.getLayout();
		c.show(IndexPage.cards, "Question");
		
		// Add new navi button
		IndexPage.nav_bar.add(new JLabel(" > "));
		IndexPage.nav_bar.add(new JButton(node.getName()));
		IndexPage.nav_bar.revalidate();
	}
}
