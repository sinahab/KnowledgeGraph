package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.knowledge.graph.frontend.IndexPage;

public class TopicsPanel extends DirectoryPanel {

	public TopicsPanel(int ID) {
		super("Topic");
		this.ID = ID;
	}

	@Override
	protected String getDescription() {
		return "Mathematical!";
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		model.addElement(new NodeWrapper("Algebra", 1));
		model.addElement(new NodeWrapper("PDEs", 2));
		return model;
	}

	@Override
	protected void goAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		
		// Add new card
		IndexPage.concept = new ConceptsPanel(node.getID());
		IndexPage.cards.add(IndexPage.concept, "Concept");
		CardLayout c = (CardLayout) IndexPage.cards.getLayout();
		c.show(IndexPage.cards, "Concept");
		
		// Add new navi button
		IndexPage.concepts_button = new JButton(node.getName());
		IndexPage.concepts_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) IndexPage.cards.getLayout();
				cl.show(IndexPage.cards, "Concept");
				if (IndexPage.questions_button != null) {
					IndexPage.nav_bar.remove(IndexPage.questions_button);
				}
				if (IndexPage.questions_spacer != null) {
					IndexPage.nav_bar.remove(IndexPage.questions_spacer);
				}
				IndexPage.nav_bar.revalidate();
				IndexPage.nav_bar.repaint();
			}
		});
		IndexPage.concepts_spacer = new JLabel(" > ");
		IndexPage.nav_bar.add(IndexPage.concepts_spacer);
		IndexPage.nav_bar.add(IndexPage.concepts_button);
		IndexPage.nav_bar.revalidate();
	}

	@Override
	protected void addAction() {
		// TODO Auto-generated method stub
		
	}

}