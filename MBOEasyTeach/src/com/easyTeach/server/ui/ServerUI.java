package com.easyTeach.server.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.server.network.EasyTeachServer;

/**
 * <p>
 * The ServerUI class is one of the User Interface (UI) class for the Server. It
 * is part of the server side of the application and will be located on server.
 * </p>
 * 
 * <p>
 * The class has a private inner class implements ActionListener for handling
 * triggered events.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.2
 * @date 13. December, 2013
 */

public class ServerUI {

    public static void main(String[] args) {
        new ServerUI();
    }

    private JFrame frame;
    private JButton btnOpen;
    private JButton btnClose;
    private JButton btnQuit;
    private JTextArea textArea;

    /**
     * Constructor for creating new instances of the Server UI. The constructor
     * calls the buildUI method which creates all the JComponents for the UI.
     */
    public ServerUI() {
        buildUI();
    }

    /**
     * The buildUI method constructs the JFrame and all of the components for
     * the ServerUI. Moreover, an ActionListener that listens to events is added
     * to all fields and buttons.
     */
    public void buildUI() {
        this.frame = new JFrame("EasyTeach - Login");
        this.frame.setSize(400, 300);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        this.frame.setContentPane(contentPane);

        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        centerPanel.add(new JScrollPane(textArea));
        centerPanel.setBackground(UIColors.lightBlue);
        centerPanel.setBorder(new TitledBorder(null, "Server Communication",
                TitledBorder.CENTER, TitledBorder.TOP));

        contentPane.add(centerPanel, BorderLayout.CENTER);

        // Button panel - South border
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(UIColors.darkBlue);
        btnPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        contentPane.add(btnPanel, BorderLayout.SOUTH);

        this.btnOpen = new JButton("Open");
        btnPanel.add(this.btnOpen);

        this.btnClose = new JButton("Close");
        btnPanel.add(this.btnClose);

        this.btnQuit = new JButton("Quit");
        btnPanel.add(this.btnQuit);

        // Button listeners
        addActionListeners();

        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    /**
     * Method in charge of adding an ActionListener to the various buttons and
     * fields in the ServerUI.
     */
    private void addActionListeners() {
        ServerUIListener listener = new ServerUIListener();
        this.btnOpen.addActionListener(listener);
        this.btnClose.addActionListener(listener);
        this.btnQuit.addActionListener(listener);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    /**
     * <p>
     * The inner private ServerUIListener class is in charge of listening for
     * events happening in the ServerUI (e.g. an user clicking the help button).
     * </p>
     * 
     * @author Morten Faarkrog
     * @version 1.0
     * @see ActionListener
     * @date 13. December, 2013
     */
    private class ServerUIListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnQuit) {
                System.exit(0);
            }
            
            else if (e.getSource() == btnClose) {
                System.exit(0);
            }
            
            else if (e.getSource() == btnOpen) {
                new EasyTeachServer();
            }
        }

    }

}