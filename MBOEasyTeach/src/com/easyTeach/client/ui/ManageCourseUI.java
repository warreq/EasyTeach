package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.HelpPresenter;

/**
 * The ManageCourseUI class constructs a JPanel with all the different
 * JComponents needed to add new courses and manage old ones.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 7. December, 2013
 */

public class ManageCourseUI {

    private final String[] FILTER = { "Class No.", "Class name", "Year",
            "Date added" };
    private JPanel manageCoursePanel;
    private JButton btnHelp;
    private JButton btnDiscard;
    private JButton btnSaveCourse;
    private JTable associatedClassesTable;
    private JTable allClassesTable;
    private JPanel centerPanel;
    private JButton btnRemoveCourse;
    private JButton btnAddCourse;
    private JTextField txtCourseName;
    private JComboBox filterBox;
    private JTextField txtFilter;
    private JButton btnFilter;

    /**
     * Constructor for building the manageCoursePanel. The panel is built by
     * calling the buildPanel method. The constructor also calls a method adding
     * actionListeners to all buttons.
     */
    public ManageCourseUI() {
        buildPanel();
        addActionListeners();
    }

    /**
     * Returns the ManageCoursePanel with all of the JComponents within.
     * 
     * @return the JPanel manageCoursePanel
     */
    public JPanel getManageCourseUI() {
        return manageCoursePanel;
    }

    /**
     * Builds the ManageCoursePanel with all of the JComponenets within.
     */
    public void buildPanel() {
        manageCoursePanel = new JPanel(new BorderLayout());

        buildNorthPanel();
        buildCenterPanel();
        buildSouthPanel();
    }

    /**
     * Builds the north panel with the the headline of the UI.
     */
    private void buildNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(UIColors.darkBlue);

        JLabel lblManageCourseTitle = new JLabel("Manage Course");
        lblManageCourseTitle.setForeground(UIColors.white);
        lblManageCourseTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(lblManageCourseTitle);

        manageCoursePanel.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Builds the center panel with the needed information to create/edit a
     * course (courseName and a JTable with classes associated with the course).
     */
    private void buildCenterPanel() {
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(UIColors.lightBlue);

        buildInfoPanel();
        buildClassPanel();

        manageCoursePanel.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Builds the Course panel where a Class can be associated to the course
     * being created/edited or be removed from the class. The panel consists of
     * two tables showing what classes exist in the system and what classes are
     * part of the course under discussion.
     */
    private void buildClassPanel() {
        JPanel classPanel = new JPanel(new GridLayout(2, 1));
        classPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null),
                "Classes in Course", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Tahoma", Font.PLAIN, 20)));
        classPanel.setBackground(UIColors.lightBlue);
        centerPanel.add(classPanel, BorderLayout.CENTER);

        // The panel showing the classes currently in the course
        JPanel associatedClassesPanel = new JPanel(new BorderLayout());
        associatedClassesPanel.setBorder(new TitledBorder(null,
                "Associated Classes", TitledBorder.CENTER, TitledBorder.TOP));
        associatedClassesPanel.setBackground(UIColors.lightBlue);

        // Table showing all associated classes
        associatedClassesTable = new JTable();
        DefaultTableModel1 associatedModel = new DefaultTableModel1();
        String[] associatedHeads = { "Class No.", "Class name", "Year" };
        associatedModel.setRowCount(1);
        associatedModel.setColumnIdentifiers(associatedHeads);
        associatedModel.setValueAt("1", 0, 0);
        associatedModel.setValueAt("DAT13W", 0, 1);
        associatedModel.setValueAt("2013", 0, 2);
        associatedClassesTable.setModel(associatedModel);

        JScrollPane associatedClassesScroll = new JScrollPane(
                associatedClassesTable);
        associatedClassesScroll.getViewport().setBackground(UIColors.white);
        associatedClassesPanel
                .add(associatedClassesScroll, BorderLayout.CENTER);

        classPanel.add(associatedClassesPanel);

        // The panel showing all classes in the system
        JPanel allClassesPanel = new JPanel(new BorderLayout());

        allClassesPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null),
                "Available Classes", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Tahoma", Font.PLAIN, 20)));
        allClassesPanel.setBackground(UIColors.lightBlue);
        classPanel.add(allClassesPanel);

        JPanel addRemovePanel = new JPanel();
        addRemovePanel.setBackground(UIColors.lightBlue);
        addRemovePanel.setLayout(new GridLayout(1, 2));

        btnRemoveCourse = new JButton("Remove Class From Course");
        addRemovePanel.add(btnRemoveCourse);

        btnAddCourse = new JButton("Add Class To Course");
        addRemovePanel.add(btnAddCourse);

        associatedClassesPanel.add(addRemovePanel, BorderLayout.SOUTH);

        // Table showing all classes
        allClassesTable = new JTable();
        DefaultTableModel1 allClassesModel = new DefaultTableModel1();

        String[] classHeads = { "Class No.", "Class name", "Year" };
        allClassesModel.setRowCount(1);
        allClassesModel.setColumnIdentifiers(classHeads);
        allClassesModel.setValueAt("1", 0, 0);
        allClassesModel.setValueAt("DAT13W", 0, 1);
        allClassesModel.setValueAt("2013", 0, 2);
        allClassesTable.setModel(allClassesModel);

        JScrollPane allClassesScroll = new JScrollPane(allClassesTable);
        allClassesScroll.getViewport().setBackground(UIColors.white);
        allClassesPanel.add(allClassesScroll, BorderLayout.CENTER);

        JPanel filterPanel = new JPanel();
        filterPanel.setBorder(new TitledBorder(null,
                "Filter Available Classes", TitledBorder.CENTER,
                TitledBorder.TOP));
        filterPanel.setBackground(UIColors.lightBlue);

        JLabel lblFilter = new JLabel("Filter");
        filterPanel.add(lblFilter);

        filterBox = new JComboBox();
        filterBox.setModel(new DefaultComboBoxModel(FILTER));
        filterPanel.add(filterBox);

        JLabel lblBy = new JLabel("by");
        filterPanel.add(lblBy);

        txtFilter = new JTextField();
        filterPanel.add(txtFilter);
        txtFilter.setColumns(14);

        btnFilter = new JButton("Filter");
        filterPanel.add(btnFilter);
        allClassesPanel.add(filterPanel, BorderLayout.SOUTH);
    }

    /**
     * Builds the Info Panel which is part of the centerPanel. The Info Panel
     * contains JComponents for inserting the course's name.
     * */
    private void buildInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        infoPanel.setBackground(UIColors.lightBlue);
        infoPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null),
                "Course Information", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Tahoma", Font.PLAIN, 20)));

        JLabel lblCourseName = new JLabel("Course Name:");
        lblCourseName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblCourseName.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(lblCourseName);

        txtCourseName = new JTextField();
        infoPanel.add(txtCourseName);

        centerPanel.add(infoPanel, BorderLayout.NORTH);
    }

    /**
     * Builds the South Panel which has buttons for Help, Discard and Save
     * Course.
     */
    private void buildSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setBackground(UIColors.darkBlue);

        btnHelp = new JButton("Help");
        southPanel.add(btnHelp);

        btnDiscard = new JButton("Discard");
        southPanel.add(btnDiscard);

        btnSaveCourse = new JButton("Save Course");
        southPanel.add(btnSaveCourse);

        manageCoursePanel.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds an ActionListener of type ManageCourseListener to all the JButtons
     * from the manageCoursePanel.
     */
    private void addActionListeners() {
        ManageCourseListener listener = new ManageCourseListener();
        btnDiscard.addActionListener(listener);
        btnHelp.addActionListener(listener);
        btnSaveCourse.addActionListener(listener);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    /**
     * The ManageCourseListener class is the class in charge of listening for
     * events happening in the ManageCourseUI (e.g. a user clicking the help
     * button). When an event occurs the ManageCourseListener will send a signal
     * to the ManageCoursePresenter which will in return act upon the event.
     * 
     * @author Morten Faarkrog
     * @version 0.1
     * @see ActionListener
     * @date 7. December, 2013
     */
    private class ManageCourseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDiscard) {
                int reply = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want discard what you have made?\n"
                                + "Warning: It will not be saved.",
                        "Discard Message", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    MainFrame.updateFrame(new AdminManagerUI().getAdminManagerUI(),
                            "Admin Manager");
                }
            }

            else if (e.getSource() == btnSaveCourse) {

            }

            else if (e.getSource() == btnAddCourse) {

            }

            else if (e.getSource() == btnRemoveCourse) {

            }

            else if (e.getSource() == btnFilter) {

            }

            else if (e.getSource() == btnHelp) {
                JOptionPane.showMessageDialog(null,
                        HelpPresenter.getManageCourseHelp(),
                        HelpPresenter.getManageCourseTitle(),
                        JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
            }
        }
    }

}