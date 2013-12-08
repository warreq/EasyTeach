package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * The TeacherManagerUI class constructs a JPanel with all the different
 * JComponents needed to maintain exercises, questions and review the progress
 * of classes and users. 
 * 
 * @author Morten Faarkrog
 * @version 1.1
 * @date 8. December, 2013
 */

public class TeacherManagerUI {

    private JPanel teacherManagerPanel;
    private JTabbedPane tabPanel;

    /**
     * Constructor for building the teacherManagerPanel. The panel is built by
     * calling the buildPanel method.
     */
    public TeacherManagerUI() {
        buildPanel();
    }

    /**
     * The getTeacherManagerUI returns the JPanel containing all the components
     * needed to maintain exercises, questions and review progress amongst
     * classes and users.
     * 
     * @return the JPanel, teacherManagerPanel. Meant to be used in another
     *         frame.
     */
    public JPanel getTeacherManagerUI() {
        return teacherManagerPanel;
    }

    /**
     * The method responsible for building the teacherManagerPanel. The panel
     * consists of all the JComponenets needed to maintain users, classes and
     * courses.
     */
    public void buildPanel() {
        teacherManagerPanel = new JPanel(new BorderLayout());
        teacherManagerPanel.setBackground(UIColors.lightBlue);

        buildNorthPanel();
        buildCenterPanel();
    }

    /**
     * Builds the center panel with the tabbed panels for managing exercises,
     * quizzes and reviewing classes' and users' progress.
     * 
     * @see ExerciseManagerUI
     * @see QuestionManagerUI
     */
    private void buildCenterPanel() {
        tabPanel = new JTabbedPane(JTabbedPane.TOP);
        tabPanel.add("Exercises",
                new ExerciseManagerUI().getExerciseManagerUI());
        tabPanel.add("Questions",
                new QuestionManagerUI().getQuestionManagerUI());
        tabPanel.add("Progress", new JPanel());

        teacherManagerPanel.add(tabPanel, BorderLayout.CENTER);
        teacherManagerPanel.setBackground(UIColors.darkBlue);
    }

    /**
     * Builds the north panel with the the headline of the UI.
     */
    private void buildNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(UIColors.darkBlue);

        JLabel lblTeacherManagerTitle = new JLabel(
                "Exercise, Question & Progress Manager");
        lblTeacherManagerTitle.setForeground(UIColors.white);
        lblTeacherManagerTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(lblTeacherManagerTitle);

        teacherManagerPanel.add(northPanel, BorderLayout.NORTH);
    }

}