package com.knowledge.graph.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class IndexPage extends JPanel implements TreeSelectionListener {
	private JTree indexTree;

	public IndexPage() {
	
		// Create root node and populate tree
	    DefaultMutableTreeNode top = new DefaultMutableTreeNode("Knowledge Graph");
	    populateTree(top);
	    
	    indexTree = new JTree(top);
	    JScrollPane treeView = new JScrollPane(indexTree);
	    add(treeView);
	}
	
	private void populateTree(DefaultMutableTreeNode top) {
		
	}

	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
