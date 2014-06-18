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
	protected JScrollPane scroll;
	protected JButton add_button;
	protected JFormattedTextField add_name; // Name of new field added
	protected JFormattedTextField add_field;
	protected JButton go_button;
	protected JButton delete_button;
	public JList list;
	protected int ID;
	
	public DirectoryPanel(String name, String description) {
		this.name = name;
		this.description = new JLabel(description);
		this.description.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), name));
		this.description.setPreferredSize(new Dimension(500,50));
		add_button = new JButton("Add New");
		add_button.setPreferredSize(new Dimension(100,100));
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAction();
			}
		});
		go_button = new JButton("Go");
		go_button.setPreferredSize(new Dimension(100,20));
		go_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goAction();
			}
		});
		delete_button = new JButton("Delete");
		delete_button.setPreferredSize(new Dimension(100,20));
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteAction();
			}
		});

		add_name = new JFormattedTextField();
		add_name.setPreferredSize(new Dimension(400,20));
		add_field = new JFormattedTextField();
		add_field.setPreferredSize(new Dimension(400,50));
		
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
		constraint.gridwidth = 1;
		constraint.anchor = GridBagConstraints.LINE_END;
		add(go_button, constraint);
		
		constraint.gridx = 0;
		constraint.anchor = GridBagConstraints.LINE_START;
		add(delete_button, constraint);
		
		// Add Scrollpane and list
	    scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(500, 200));
		constraint.gridx = 0; constraint.gridy = 3;
		constraint.weightx = 2; constraint.weighty = 10;
		constraint.gridwidth = 2;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(scroll, constraint);
		
		// Add new entry field
		constraint.gridx = 0; constraint.gridy = 4;
		constraint.weightx = 5; constraint.weighty = 1;
		constraint.gridwidth = 1;
		constraint.anchor = GridBagConstraints.LINE_START;
		add(add_name, constraint);
		constraint.gridy = 5;
		add(add_field, constraint);
		constraint.gridx = 1; constraint.gridy = 4;
		constraint.weightx = 1;
		constraint.gridheight = 2;
		add(add_button, constraint);
		
	}
	
	public String getName() {
		return name;
	}
	
	protected abstract DefaultListModel generateList();
	protected abstract void goAction();
	protected abstract void deleteAction();
	protected abstract void addAction();
}
