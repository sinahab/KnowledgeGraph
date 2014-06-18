package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
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
	
	public ConceptsPanel(int ID) {
		super( Mainpage.getTopics().getTopicByID(ID).getName(),
				Mainpage.getTopics().getTopicByID(ID).getDescription());
		this.subject = Mainpage.getTopics().getTopicByID(ID).getTiedSubject();
		this.topic = Mainpage.getTopics().getTopicByID(ID);
		this.concepts = Mainpage.getTopics().getTiedConcepts(ID);
		this.ID = ID;
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		generateGUI();
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		
		for (int i = 0; i < concepts.size(); i++ ) {
			model.addElement( new NodeWrapper(this.concepts.get(i).getName(),
					this.concepts.get(i).getConcept_ID()) );
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
