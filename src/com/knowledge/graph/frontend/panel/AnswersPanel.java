package com.knowledge.graph.frontend.panel;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Answer;
import com.knowledge.graph.backend.Concept;
import com.knowledge.graph.backend.Question;
import com.knowledge.graph.backend.Student;
import com.knowledge.graph.frontend.IndexPage;

public class AnswersPanel extends DirectoryPanel {
	private Question question;
	private Concept concept;
	private Student asker;
	
	public AnswersPanel(int question_ID) {
		super("", "");
		question = Mainpage.getQuestions().searchQuestionByID(question_ID);
		concept = Mainpage.getConcepts().getConceptByID(question.getConcept_ID());
		asker = Mainpage.getStudents().searchForStudentBySID(question.getStudent_ID());
		name = "Question on " + concept.getName();
		description.setText(question.getText());
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		generateGUI();
		add_name.setVisible(false);
	}

	@Override
	protected DefaultListModel generateList() {
		DefaultListModel model = new DefaultListModel();
		List<Answer> answers = question.getTiedAnswers();
		if (answers != null) {
			for (Answer ans : answers) {
				model.addElement(new NodeWrapper(ans.getText(), ans.getAnswerID()));
			}
		}
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
		
		// No navi button for answers
		
	}
	
	@Override
	protected void deleteAction() {
		// Find node
		NodeWrapper node = (NodeWrapper)list.getSelectedValue();
		Mainpage.getAnswers().deleteAnswerByID( node.getID() );

		redrawMeatPanel();
	}

	@Override
	protected void addAction() {
		// Adds a new subject to the list of subjects
		String body = add_field.getText();
		Mainpage.getAnswers().addAnswer(question.getQuestion_ID(), body, Mainpage.student.getStudentID());
		
		// Refresh display
		redrawMeatPanel();
	}

}
