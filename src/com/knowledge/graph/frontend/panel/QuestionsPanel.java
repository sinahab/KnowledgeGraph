package com.knowledge.graph.frontend.panel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestionsPanel extends FeedPanel {

	public QuestionsPanel(int concept_id) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel conceptLabel = new JLabel("Concept #" + Integer.toString(concept_id));
		
		panel.add(conceptLabel);
		add(panel);
	}
}
