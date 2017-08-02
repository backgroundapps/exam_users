package client.views.user;

import client.views.actions.RegisterUserActionListener;
import client.views.components.DefaultProperties;
import client.views.components.JDialogBean;
import common.UserImpl;
import server.process.UserProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class RegisterUserFrame extends JDialogBean {

    JLabel loginLabel = new JLabel("Login : ");
    JLabel fullNameLabel = new JLabel("Full Name : ");
    JLabel statusLabel = new JLabel("Status : ");
    JLabel currentManagerLabel = new JLabel("Current Manager : ");

    JTextField loginField = new JTextField();
    JTextField fullNameField = new JTextField();

    JRadioButton statusActiveRadioButton = new JRadioButton("ACTIVE");
    JRadioButton statusInactiveRadioButton = new JRadioButton("INACTIVE");

    JCheckBox currentManagerCheckBox = new JCheckBox();
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");


    public RegisterUserFrame() {
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

    }

    public void setupUI() {

        this.setTitle("REGISTER USER");

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        topPanel.add(loginLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(loginField, gbc);



        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        topPanel.add(fullNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(fullNameField, gbc);



        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        topPanel.add(statusLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        statusActiveRadioButton.setSelected(true);
        topPanel.add(statusActiveRadioButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(statusInactiveRadioButton, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        topPanel.add(currentManagerLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(currentManagerCheckBox, gbc);

        this.add(topPanel);

        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {

        loginField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    register();
                }
            }
        });

        statusActiveRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                statusActiveRadioButton.setSelected(true);
                statusInactiveRadioButton.setSelected(false);
            }
        });

        statusInactiveRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                statusInactiveRadioButton.setSelected(true);
                statusActiveRadioButton.setSelected(false);
            }
        });


        saveButton.addActionListener(new RegisterUserActionListener(this));

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterUserFrame.this.setVisible(false);
            }
        });
    }

    private void register() {
        try {
            new UserProcess().create(new UserImpl(
                    this.loginField.getText(),
                    this.fullNameField.getText(),
                    (this.statusActiveRadioButton.isSelected() ? "ACTIVE" : "INACTIVE"),
                    (this.currentManagerCheckBox.isSelected() ? "Y" : "N")
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Done!");
        this.setVisible(false);

    }

    private boolean isValidView(){
        boolean result = true;

        if(this.loginField.getText().isEmpty()){
            result = false;
            JOptionPane.showMessageDialog(null, "Please! Inform your login");
        }

        if(this.loginField.getText().length() > 4){
            result = false;
            JOptionPane.showMessageDialog(null, "Is not allowed more then 4 characters for logins");
        }

        return result;
    }

    @Override
    public Object getBean() {
        return new UserImpl(
                this.loginField.getText(),
                this.fullNameField.getText(),
                (this.statusActiveRadioButton.isSelected() ? "ACTIVE" : "INACTIVE"),
                (this.currentManagerCheckBox.isSelected() ? "Y" : "N"));
    }
}