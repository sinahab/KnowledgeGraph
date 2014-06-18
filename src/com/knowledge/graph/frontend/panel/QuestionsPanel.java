package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.knowledge.graph.frontend.IndexPage;

public class QuestionsPanel extends DirectoryPanel {

	public QuestionsPanel(int concept_id) {
		super("Question");
	}

	@Override
	protected String getDescription() {
		return "Why is the sky blue?";
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		model.addElement(new NodeWrapper("Because your face.", 1));
		model.addElement(new NodeWrapper("Ask Sina Habibian", 2));
		return model;
	}

	@Override
	protected void goAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		
		// Add new card
		IndexPage.answer = new AnswerPanel(node.getID());
		IndexPage.cards.add(IndexPage.answer, "Answer");
		CardLayout c = (CardLayout) IndexPage.cards.getLayout();
		c.show(IndexPage.cards, "Answer");
		
		// No navi button for answer
	}

	@Override
	protected void addAction() {
		// TODO Auto-generated method stub
		
	}
}
