package com.knowledge.graph.frontend.panel;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

public class SubjectPanel extends DirectoryPanel {
	// Subject panels displays all subjects thus has no ID
	
	public SubjectPanel() {
		super("Subjects");
	}

	@Override
	protected DefaultListModel generateList() {
		// TODO Auto-generated method stub
		DefaultListModel list = new DefaultListModel();
		list.addElement(new NodeWrapper("Math", 1));
		list.addElement(new NodeWrapper("Science", 2));
		list.addElement(new NodeWrapper("Coffee", 3));
		return list;
	}

	@Override
	protected String getDescription() {
		// TODO Auto-generated method stub
		return "This is a list of all subjects";
	}
}
