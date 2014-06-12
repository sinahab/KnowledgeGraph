package com.knowledge.graph.frontend;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestionsPage extends Page {

	public QuestionsPage(int concept_id) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel conceptLabel = new JLabel("Concept #" + Integer.toString(concept_id));
		
		panel.add(conceptLabel);
		add(panel);
	}
}
