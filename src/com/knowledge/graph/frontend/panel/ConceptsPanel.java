package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import com.knowledge.graph.frontend.IndexPage;

public class ConceptsPanel extends DirectoryPanel {

	public ConceptsPanel(int ID) {
		super("Concept", "Algebra is such a fun concept!");
		this.ID = ID;
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		generateGUI();
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		model.addElement(new NodeWrapper("Why study Algebra?", 1));
		model.addElement(new NodeWrapper("How do I solve x = 1?", 2));
		return model;
	}

	@Override
	protected void goAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		
		// Add new card
		IndexPage.question = new QuestionsPanel(node.getID());
		IndexPage.cards.add(IndexPage.question, "Question");
		CardLayout c = (CardLayout) IndexPage.cards.getLayout();
		c.show(IndexPage.cards, "Question");
		
		// Add new navi button
		IndexPage.questions_button = new JButton(node.getName());
		IndexPage.questions_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) IndexPage.cards.getLayout();
				cl.show(IndexPage.cards, "Question");
			}
		});
		IndexPage.questions_spacer = new JLabel(" > ");
		IndexPage.nav_bar.add(IndexPage.questions_spacer);
		IndexPage.nav_bar.add(IndexPage.questions_button);
		IndexPage.nav_bar.revalidate();
	}

	@Override
	protected void addAction() {
		// TODO Auto-generated method stub
		
	}
}
