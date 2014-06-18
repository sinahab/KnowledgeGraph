package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Answer;
import com.knowledge.graph.backend.Concept;
import com.knowledge.graph.backend.Question;
import com.knowledge.graph.backend.Student;
import com.knowledge.graph.frontend.IndexPage;

public class QuestionsPanel extends DirectoryPanel {
	private JPanel user_pane;
	private Student student;
	private Concept concept;

	public QuestionsPanel(int concept_id) {
		super("","");
		concept = Mainpage.getConcepts().getConceptByID(concept_id);
		this.name = concept.getName();
		this.description.setText(concept.getDescription());
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		generateGUI();
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		List<Question> questions = concept.getTiedQuestions();
		if (questions != null) {
			for (Question question : questions) {
				model.addElement(new NodeWrapper(question.getText(), question.getQuestion_ID()));
			}
		}
		return model;
	}

	@Override
	protected void goAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		
		// Add new card
		IndexPage.answers = new AnswersPanel(node.getID());
		IndexPage.cards.add(IndexPage.answers, "Answers");
		CardLayout c = (CardLayout) IndexPage.cards.getLayout();
		c.show(IndexPage.cards, "Answers");
		
		// Add answers navi button
		IndexPage.answers_button = new JButton(node.getName());
		IndexPage.answers_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) IndexPage.cards.getLayout();
				cl.show(IndexPage.cards, "Answers");
			}
		});
		IndexPage.answers_spacer = new JLabel(" > ");
		IndexPage.nav_bar.add(IndexPage.answers_spacer);
		IndexPage.nav_bar.add(IndexPage.answers_button);
		IndexPage.nav_bar.revalidate();		
	}

	@Override
	protected void addAction() {
		// TODO Auto-generated method stub
		
	}

}
