package com.knowledge.graph.frontend;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.frontend.panel.QuestionsPanel;

public class IndexTree extends JPanel implements TreeSelectionListener {
	private JTree indexTree;

	public IndexTree() {
		
		// Create root node and populate tree
	    DefaultMutableTreeNode top = new DefaultMutableTreeNode("Knowledge Graph");
	    populateTree(top);
	    
	    indexTree = new JTree(top);
	    indexTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		//Listen for when the selection changes.
		indexTree.addTreeSelectionListener(this);
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
		concept = new ConceptNode("Entity", 1);
		topic.add(concept);
		concept = new ConceptNode("Relation", 2);
		topic.add(concept);
		
		topic = new DefaultMutableTreeNode("SQL");
		subject.add(topic);
		
	}
	
	public class ConceptNode extends DefaultMutableTreeNode {
		private int concept_id;
		
		public ConceptNode(Object arg0, int id) {
			super(arg0);
			this.concept_id = id;
		}
		
		public int getID() {
			return concept_id;
		}
	}

	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                indexTree.getLastSelectedPathComponent();

		if (node == null)
		//Nothing is selected.     
		return;
		
		if (node instanceof ConceptNode) {
			System.out.println("Concept #" + Integer.toString(((ConceptNode) node).getID()) + " selected");
			Mainpage.indexpage.setVisible(false);
			Mainpage.questionspage = new QuestionsPanel(((ConceptNode) node).getID());
			Mainpage.questionspage.setVisible(true);
		}
	}
}
