package com.knowledge.graph.frontend.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class AnswerPanel extends JPanel {
	private int ID;

	public AnswerPanel(int ID) {
		this.ID = ID;
		JLabel description = new JLabel("What is the answer to Life, The Universe and Everything?");
		description.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Question on Life"));
		description.setPreferredSize(new Dimension(500,50));
		// Setup layout and add title and description
		setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.weightx = 1; constraint.weighty = 1;
		constraint.gridx = 0; constraint.gridy = 0;
		constraint.gridwidth = 2; constraint.gridheight = 1;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(description, constraint);
		
		constraint.gridx = 0; constraint.gridy = 1;
		JLabel answer = new JLabel("The Answer to everything is 42.");
		answer.setPreferredSize(new Dimension(500,50));
		answer.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		add(answer, constraint);
		
	}

}
