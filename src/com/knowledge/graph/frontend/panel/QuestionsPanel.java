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
	private Question question;

	public QuestionsPanel(int question_id) {
		super("","");
		question = Mainpage.getQuestions().searchQuestionByID(question_id);
		Concept concept = Mainpage.getConcepts().getConceptByID(question.getConcept_ID());
		this.name = "Question on " + concept.getName();
		this.description.setText(question.getText());
		list = new JList(generateList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		student = Mainpage.getStudents().searchForStudentBySID(question.getStudent_ID());
		user_pane = new JPanel();
		JLabel submitted = new JLabel("Asked by: " + student.getFullName() +
				" on " + question.getQuestionDate() + "  ");
		user_pane.add(submitted);
		JButton add_mentor = new JButton("Add as mentor");
		add_mentor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mainpage.getMentors().addMentor(Mainpage.student.getStudentID(), student.getStudentID());
			}
		});
		user_pane.add(add_mentor);
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 0; constraint.gridy = 2;
		constraint.weightx = 1; constraint.weighty = 1;
		constraint.gridwidth = 1; constraint.gridheight = 1;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(user_pane, constraint);
		generateGUI();
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
		
		// No navi button for answer
	}

	@Override
	protected void addAction() {
		// TODO Auto-generated method stub
		
	}

}
