package com.knowledge.graph.frontend.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public abstract class DirectoryPanel extends JPanel {
	protected JLabel description;
	protected JButton add_button;
	protected JList<DefaultListModel<NodeWrapper>> list;
	protected int ID;
	
	public DirectoryPanel(String name) {
		description = new JLabel(getDescription());
		description.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), name));
		description.setPreferredSize(new Dimension(500,50));
		add_button = new JButton("Add New");
		list = new JList(generateList());
		generateGUI();
	}
	
	protected void generateGUI() {
		// Setup layout and add title and description
		setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.weightx = 1; constraint.weighty = 1;
		constraint.gridx = 0; constraint.gridy = 0;
		constraint.gridwidth = 2; constraint.gridheight = 1;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(description, constraint);
		
		// Add buttons
		constraint.gridy = 2;
		add(add_button, constraint);
		
		// Add Scrollpane and list
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(500, 300));
		constraint.gridy = 3;
		constraint.weightx = 2; constraint.weighty = 10;
		constraint.gridwidth = 2;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(scroll, constraint);
		
	}
	protected abstract String getDescription();
	protected abstract DefaultListModel<NodeWrapper> generateList();
}
