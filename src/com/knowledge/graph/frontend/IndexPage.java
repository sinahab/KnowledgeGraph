package com.knowledge.graph.frontend;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class IndexPage extends Page {

	public IndexPage() {
		JPanel background = new JPanel(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		
		IndexTree indexTree = new IndexTree();
		background.add(indexTree, constraint);
		JPanel navigation = new JPanel();
		add(background);
	}
	
	
}
