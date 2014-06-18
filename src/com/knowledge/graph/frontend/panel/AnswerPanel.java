package com.knowledge.graph.frontend.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.knowledge.graph.Mainpage;
import com.knowledge.graph.backend.Answer;
import com.knowledge.graph.backend.Concept;
import com.knowledge.graph.backend.Mentor;
import com.knowledge.graph.backend.Question;
import com.knowledge.graph.backend.Student;
import com.knowledge.graph.frontend.IndexPage;

public class AnswerPanel extends JPanel {
	private Answer answer;
	private Question question;
	private Concept concept;
	private Student asker;
	private Student answerer;
	private JButton approve;
	private JPanel approver_panel;

	public AnswerPanel(int ID) {
		answer = Mainpage.getAnswers().getAnswerByA_ID(ID);
		question = Mainpage.getQuestions().searchQuestionByID(answer.getTiedQuestionID());
		concept = Mainpage.getConcepts().getConceptByID(question.getConcept_ID());
		asker = Mainpage.getStudents().searchForStudentBySID(question.getStudent_ID());
		answerer = Mainpage.getStudents().searchForStudentBySID(answer.getStudent_number());
		
		JLabel description = new JLabel(question.getText());
		description.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Question on " + concept.getName()));
		description.setPreferredSize(new Dimension(500,50));
		// Setup layout and add title and description
		setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.weightx = 1; constraint.weighty = 1;
		constraint.gridx = 0; constraint.gridy = 0;
		constraint.gridwidth = 2; constraint.gridheight = 1;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(description, constraint);
		
		// Add asker
		JPanel asker_panel = new JPanel();
		JLabel asker_label = new JLabel("Asked by " + asker.getFullName() + 
				" on " + question.getQuestionDate() + "  ");
		asker_panel.add(asker_label);
		JButton add_mentor = new JButton("Add as mentor");
		add_mentor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mainpage.getMentors().addMentor(Mainpage.student.getStudentID(), asker.getStudentID());
				IndexPage.redrawUserPane();
			}
		});
		asker_panel.add(add_mentor);
		constraint.gridx = 0; constraint.gridy = 1;
		constraint.weightx = 1; constraint.weighty = 0.1;
		constraint.gridwidth = 1; constraint.gridheight = 1;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(asker_panel, constraint);
		
		// Answer body
		constraint.gridx = 0; constraint.gridy = 2;
		constraint.weighty = 1;
		JLabel ans_label = new JLabel(answer.getText());
		ans_label.setPreferredSize(new Dimension(500,50));
		ans_label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		add(ans_label, constraint);
		
		// Add answerer
		JPanel answerer_panel = new JPanel();
		JLabel answerer_label = new JLabel("Answered by " + answerer.getFullName() + 
				" on " + answer.getAnswerDate() + "  ");
		answerer_panel.add(answerer_label);
		JButton add_ans_mentor = new JButton("Add as mentor");
		add_ans_mentor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mainpage.getMentors().addMentor(Mainpage.student.getStudentID(), answerer.getStudentID());
				IndexPage.redrawUserPane();
			}
		});
		answerer_panel.add(add_ans_mentor);
		constraint.gridx = 0; constraint.gridy = 3;
		constraint.weightx = 1; constraint.weighty = 0.1;
		constraint.gridwidth = 1; constraint.gridheight = 1;
		constraint.anchor = GridBagConstraints.LINE_START;
		add(answerer_panel, constraint);
		
		// Approvals
		if (Mainpage.getMentors().isMentoring(Mainpage.student.getStudentID(), answerer.getStudentID())) {
			approve = new JButton("Approve answer");
			approve.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Mentor mentor = Mainpage.getMentors().getMentorByID(Mainpage.student.getStudentID());
					mentor.approveAnswer(answer.getAnswerID(), mentor.getStudentID());
					redrawApprovals();
				}
			});
			constraint.gridx = 1;
			constraint.anchor = GridBagConstraints.LINE_END;
			add(approve, constraint);
			
			
		}
		
		if (answer.getStatus().equals("approved")) {
			// Add approved by
			redrawApprovals();
		}
		
	}
	
	private void redrawApprovals() {
		if (approver_panel != null) {
			remove(approver_panel);
		}
		approver_panel = new JPanel();
		List<Mentor> approvers = Mainpage.getMentors().mentorsWhoApprovedAnswer(answer.getAnswerID());
		String namelist = "Approved by " + approvers.get(0).getFullName();
		for (int i=1; i<approvers.size(); i++) {
			namelist = namelist + " , " + approvers.get(i).getFullName();
		}
		JLabel approver_label = new JLabel(namelist);
		approver_panel.add(approver_label);
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 0; constraint.gridy = 4;
		constraint.weightx = 1; constraint.weighty = 0.1;
		constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		add(approver_panel, constraint);
		revalidate();
		repaint();
	}

}
