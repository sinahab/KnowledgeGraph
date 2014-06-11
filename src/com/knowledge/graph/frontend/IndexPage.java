package com.knowledge.graph.frontend;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class IndexPage extends Page implements TreeSelectionListener {
	private JTree indexTree;

	public IndexPage() {
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
		
		// Create root node and populate tree
	    DefaultMutableTreeNode top = new DefaultMutableTreeNode("Knowledge Graph");
	    populateTree(top);
	    
	    indexTree = new JTree(top);
	    JScrollPane treeView = new JScrollPane(indexTree);
	    boxPanel.add(treeView);
	    add(boxPanel);
	}
	
	private void populateTree(DefaultMutableTreeNode top) {
		
	}

	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
