package com.knowledge.graph.frontend;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class IndexTree extends JPanel implements TreeSelectionListener {
	private JTree indexTree;

	public IndexTree() {
		
		// Create root node and populate tree
	    DefaultMutableTreeNode top = new DefaultMutableTreeNode("Knowledge Graph");
	    populateTree(top);
	    
	    indexTree = new JTree(top);
	    JScrollPane treeView = new JScrollPane(indexTree);
	    add(treeView);
	}
	
	private void populateTree(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode subject = null;
		DefaultMutableTreeNode topic = null;
		DefaultMutableTreeNode concept = null;
		
		// Add subject
		subject = new DefaultMutableTreeNode("Database Systems");
		top.add(subject);
		
		// Add topics
		topic = new DefaultMutableTreeNode("ER Diagrams");
		subject.add(topic);

		// Add concepts
		concept = new DefaultMutableTreeNode("Entity");
		topic.add(concept);
		concept = new DefaultMutableTreeNode("Relation");
		topic.add(concept);
		
		topic = new DefaultMutableTreeNode("SQL");
		subject.add(topic);
		
	}

	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
