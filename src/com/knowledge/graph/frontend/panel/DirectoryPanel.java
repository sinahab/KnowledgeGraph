package com.knowledge.graph.frontend.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

public abstract class DirectoryPanel extends JPanel {
	public String name;
	protected JLabel description;
	protected JButton add_button;
	protected JFormattedTextField add_field;
	protected JButton go_button;
	public JList list;
	protected int ID;
	
	public DirectoryPanel(String name, String description) {
		this.name = name;
		this.description = new JLabel(description);
		this.description.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), name));
		this.description.setPreferredSize(new Dimension(500,50));
		add_button = new JButton("Add New");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAction();
			}
		});
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
		constraint.gridx = 1;
		constraint.gridwidth = 2;
		constraint.anchor = GridBagConstraints.PAGE_START;
		add(go_button, constraint);
		
		// Add Scrollpane and list
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(500, 200));
		constraint.gridx = 0; constraint.gridy = 3;
		constraint.weightx = 2; constraint.weighty = 10;
		constraint.gridwidth = 2;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(scroll, constraint);
		
		// Add new entry field
		add_field = new JFormattedTextField();
		add_field.setPreferredSize(new Dimension(400,50));
		constraint.gridx = 0; constraint.gridy = 4;
		constraint.weightx = 5; constraint.weighty = 1;
		constraint.gridwidth = 1;
		constraint.anchor = GridBagConstraints.LINE_START;
		add(add_field, constraint);
		constraint.gridx = 1;
		constraint.weightx = 1;
		add(add_button, constraint);
		
	}
	
	public String getName() {
		return name;
	}
	
	protected abstract DefaultListModel generateList();
	protected abstract void goAction();
	protected abstract void addAction();
}
