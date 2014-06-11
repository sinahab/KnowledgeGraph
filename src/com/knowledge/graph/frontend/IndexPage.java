package com.knowledge.graph.frontend;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class IndexPage extends Page {

	public IndexPage() {
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
		
		IndexTree indexTree = new IndexTree();
		boxPanel.add(indexTree);
		
		add(boxPanel);
	}
	
	
}
