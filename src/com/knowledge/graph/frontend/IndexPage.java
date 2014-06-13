package com.knowledge.graph.frontend;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class IndexPage extends Page {

	public IndexPage() {
		JPanel background = new JPanel(new BorderLayout());
		
		IndexTree indexTree = new IndexTree();
		background.add(indexTree, BorderLayout.PAGE_START);
		JPanel navigation = new JPanel();
		add(background);
	}
	
	
}
