package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.client.presenter.LoginPresenter;

/** 
 * <p>
 * The LoginUI class is one of the User Interface (UI) classes for the 
 * "MBO EasyTeach "application. It is part of the client side of the 
 * application and will be located on users' computers.
 * </p>
 * 
 * <p>
 * The class has a private inner class implements ActionListener for 
 * handling triggered events.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 28. November, 2013
 */

public class LoginUI {

    private LoginPresenter loginPresenter;
    private HelpPresenter helpPresenter;
    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnHelp;    

    /**
     * Constructor for creating new instances of the Login UI.
     * The constructor calls the buildUI method which creates all the 
     * JComponents for the UI. 
     */
    public LoginUI() {
        loginPresenter = new LoginPresenter();
        helpPresenter = new HelpPresenter();
        buildUI();
    }
    
    /**
     * The buildUI method constructs the JFrame and all of the components
     * for the LoginUI. Moreover, an ActionListener that listens to events
     * is added to all fields and buttons.
     */
    public void buildUI() {
        frame = new JFrame("EasyTeach - Login");
        frame.setSize(280, 140);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        frame.setContentPane(contentPane);
        
        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(UIColors.lightBlue);
        centerPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP));
        // GridBagLayout is used to position things more precisely. 
        GridBagLayout gbl_centerPanel = new GridBagLayout();
        gbl_centerPanel.columnWeights = new double[]{0, 0, 1, Double.MIN_VALUE};
        gbl_centerPanel.rowWeights = new double[]{0, 0, Double.MIN_VALUE};
        centerPanel.setLayout(gbl_centerPanel);
        
        JLabel lblUsername = new JLabel("Username");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.gridx = 0;
        gbc_lblUsername.gridy = 0;
        centerPanel.add(lblUsername, gbc_lblUsername);
        
        txtUsername = new JTextField();
        GridBagConstraints gbc_txtUsername = new GridBagConstraints();
        gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
        gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtUsername.gridx = 2;
        gbc_txtUsername.gridy = 0;
        centerPanel.add(txtUsername, gbc_txtUsername);
        txtUsername.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 1;
        centerPanel.add(lblPassword, gbc_lblPassword);
        
        txtPassword = new JPasswordField();
        GridBagConstraints gbc_txtPassword = new GridBagConstraints();
        gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPassword.gridx = 2;
        gbc_txtPassword.gridy = 1;
        centerPanel.add(txtPassword, gbc_txtPassword);

        contentPane.add(centerPanel, BorderLayout.CENTER);
        
        // Button panel - South border
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(UIColors.darkBlue);
        btnPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        contentPane.add(btnPanel, BorderLayout.SOUTH);
        
        btnLogin = new JButton("Login");
        btnPanel.add(btnLogin);
        
        btnHelp = new JButton("Help");
        btnPanel.add(btnHelp);
        
        // Button listeners
        LoginUIListener listener = new LoginUIListener();
        btnLogin.addActionListener(listener);
        btnHelp.addActionListener(listener);
        txtUsername.addActionListener(listener);
        txtPassword.addActionListener(listener);
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * Explain here
     */
    public synchronized void loadFromPresenter() {
        
    }
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - // 
    
    /**
     * <p>
     * The inner private LoginUIListener class is in charge of listening
     * for events happening in the LoginUI (e.g. an user clicking the help
     * button). When an event occurs the LoginUIListener will send a signal
     * to the LoginPresenter which will in return act upon the event.
     * </p>
     * 
     * @author Morten Faarkrog
     * @version 1.0
     * @see ActionListener
     * @date 28. November, 2013
     */
    private class LoginUIListener implements ActionListener {
       
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnLogin) {
                String username = txtUsername.getText();
                char[] password = txtPassword.getPassword();
                
                if (loginPresenter.validateUsername(username)) {
                    if (loginPresenter.canLogin(username, password)) {
                        new MainFrame();
                        frame.dispose();
                    } 
                    else {
                        // Show suiting message here
                    }
                }
                Arrays.fill(password, ' ');
            }
            
            else if (e.getSource() == txtPassword) {
                btnLogin.doClick();
            }
            
            else if (e.getSource() == txtUsername) {
                btnLogin.doClick();                
            }
            
            else if (e.getSource() == btnHelp) {
                JOptionPane.showMessageDialog(null, helpPresenter.getLoginHelp(), 
                        helpPresenter.getLoginTitle(), JOptionPane.PLAIN_MESSAGE, 
                        helpPresenter.getHelpIcon());
            }
        }
        
    }

}
