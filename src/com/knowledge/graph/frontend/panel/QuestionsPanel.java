package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Concept;
import com.knowledge.graph.backend.Question;
import com.knowledge.graph.backend.Student;
import com.knowledge.graph.frontend.IndexPage;

public class QuestionsPanel extends DirectoryPanel {
	private JPanel user_pane;

	public QuestionsPanel(int question_id) {
		super("Question", "Why is the sky blue?");
		Question question = Mainpage.getQuestions().searchQuestionByID(question_id);
		Concept concept = Mainpage.getConcepts().getConceptByID(question.getConcept_ID());
		this.name = "Question on " + concept.getName();
		Student student = Mainpage.getStudents().searchForStudentBySID(question.getStudent_ID());
		user_pane = new JPanel();
		JLabel submitted = new JLabel("Submitted by: " + student.getFullName() +
				" on ");
		user_pane.add(submitted);
		JLabel username = new JLabel();
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		model.addElement(new NodeWrapper("Because your face.", 1));
		model.addElement(new NodeWrapper("Ask Sina Habibian", 2));
		return model;
	}

	@Override
	protected void goAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		
		// Add new card
		IndexPage.answer = new AnswerPanel(node.getID());
		IndexPage.cards.add(IndexPage.answer, "Answer");
		CardLayout c = (CardLayout) IndexPage.cards.getLayout();
		c.show(IndexPage.cards, "Answer");
		
		// No navi button for answer
	}

	@Override
	protected void addAction() {
		// TODO Auto-generated method stub
		
	}
}
