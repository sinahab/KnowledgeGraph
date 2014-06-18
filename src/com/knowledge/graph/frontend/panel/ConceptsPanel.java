package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Concept;
import com.knowledge.graph.backend.Concepts;
import com.knowledge.graph.backend.Subject;
import com.knowledge.graph.backend.Topic;
import com.knowledge.graph.frontend.IndexPage;

public class ConceptsPanel extends DirectoryPanel {

	private Subject subject;
	private Topic topic;
	private List<Concept> concepts;
	
	public ConceptsPanel(int topic_ID) {
		super( Mainpage.getTopics().getTopicByID(topic_ID).getName(),
				Mainpage.getTopics().getTopicByID(topic_ID).getDescription());
		this.subject = Mainpage.getTopics().getTopicByID(topic_ID).getTiedSubject();
		this.topic = Mainpage.getTopics().getTopicByID(topic_ID);
		this.concepts = Mainpage.getTopics().getTiedConcepts(topic_ID);
		this.ID = topic_ID;
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		add_name.setToolTipText("Name of new concept");
		add_field.setToolTipText("Description of new concept");
		generateGUI();
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		if (this.concepts != null) {
			for (int i = 0; i < concepts.size(); i++ ) {
				model.addElement( new NodeWrapper(this.concepts.get(i).getName(),
						this.concepts.get(i).getConcept_ID()) );
			}
		}
		// model.addElement(new NodeWrapper("Why study Algebra?", 1));
		//model.addElement(new NodeWrapper("How do I solve x = 1?", 2));
		
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
				if (IndexPage.answers_button != null) {
					IndexPage.nav_bar.remove(IndexPage.answers_button);
				}
				if (IndexPage.answers_spacer != null) {
					IndexPage.nav_bar.remove(IndexPage.answers_spacer);
				}
				IndexPage.nav_bar.revalidate();
				IndexPage.nav_bar.repaint();
			}
		});
		IndexPage.questions_spacer = new JLabel(" > ");
		IndexPage.nav_bar.add(IndexPage.questions_spacer);
		IndexPage.nav_bar.add(IndexPage.questions_button);
		IndexPage.nav_bar.revalidate();
	}

	@Override
	protected void deleteAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		Mainpage.getConcepts().deleteConceptByID( node.getID() );

		redrawMeatPanel();
	}
	
	@Override
	protected void addAction() {
		// Adds a new subject to the list of subjects
		String title = add_name.getText();
		String body = add_field.getText();
		Mainpage.getConcepts().addConcept(topic.getTopicID(), title, body);
		
		// Refresh display
		redrawMeatPanel();
	}
}
