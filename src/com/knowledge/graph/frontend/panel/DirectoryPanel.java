package com.knowledge.graph.frontend.panel;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

public abstract class DirectoryPanel extends JPanel {
	protected JLabel title;
	protected JList list;
	protected int ID;
	
	public DirectoryPanel(String name) {
		title = new JLabel(name);
	}
	
	protected abstract ListModel generateList();
}
