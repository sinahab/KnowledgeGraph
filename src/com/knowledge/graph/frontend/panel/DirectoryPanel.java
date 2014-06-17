package com.knowledge.graph.frontend.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;

public abstract class DirectoryPanel extends JPanel {
	public String name;
	protected JLabel description;
	protected JButton add_button;
	protected JButton go_button;
	public JList list;
	protected int ID;
	
	public DirectoryPanel(String name) {
		this.name = name;
		description = new JLabel(getDescription());
		description.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), name));
		description.setPreferredSize(new Dimension(500,50));
		add_button = new JButton("Add New");
		go_button = new JButton("Go");
		go_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goAction();
			}
		});
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
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
		constraint.gridx = 1;
		constraint.anchor = GridBagConstraints.PAGE_START;
		add(go_button, constraint);
		
		// Add Scrollpane and list
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(500, 300));
		constraint.gridx = 0; constraint.gridy = 3;
		constraint.weightx = 2; constraint.weighty = 10;
		constraint.gridwidth = 2;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(scroll, constraint);
		
	}
	
	public String getName() {
		return name;
	}
	
	protected abstract String getDescription();
	protected abstract DefaultListModel generateList();
	protected abstract void goAction();
}
