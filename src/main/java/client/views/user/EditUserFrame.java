package client.views.user;

import client.views.components.DefaultProperties;
import common.User;
import common.UserImpl;
import server.process.UserProcess;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class EditUserFrame extends JDialog {

    private Long userId;
    private User user;

    JLabel loginLabel = new JLabel("Login : ");
    JLabel fullNameLabel = new JLabel("Full Name : ");
    JLabel statusLabel = new JLabel("Status : ");
    JLabel currentManagerLabel = new JLabel("Current Manager : ");

    JTextField loginField = new JTextField();
    JTextField fullNameField = new JTextField();

    JRadioButton statusActiveRadioButton = new JRadioButton("ACTIVE");
    JRadioButton statusInactiveRadioButton = new JRadioButton("INACTIVE");

    JCheckBox currentManagerCheckBox = new JCheckBox();
    JButton deleteButton = new JButton("Remove");
    JButton permissionButton = new JButton("Update Permissions");
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");


    public EditUserFrame(Long userId) {
        this.userId = userId;

        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

        setUser();


    }

    private void setUser(){
        try {
            user = new UserProcess().findById(this.userId);
            addUserViewValues();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addUserViewValues() {
        loginField.setText(user.getLogin());
        fullNameField.setText(user.getFullName());
        if("Y".equals(user.getCurrentManagement())) currentManagerCheckBox.setSelected(true);

        if("INACTIVATE".equals(user.getStatus())){
            statusActiveRadioButton.setSelected(false);
            statusInactiveRadioButton.setSelected(true);
        }
    }

    public void setupUI() {

        this.setTitle("EDIT USER");

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButton.setBackground(Color.RED);
        permissionButton.setBackground(Color.BLUE);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(permissionButton);
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


        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(isValidView()){
                    register();
                }

            }
        });

        permissionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listPermisions();
            }
        });

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure about removing this user?", "Attention", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) delete();
            }

        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditUserFrame.this.setVisible(false);
            }
        });
    }

    private void register() {
        try {
            new UserProcess().update(new UserImpl(
                    this.loginField.getText(),
                    this.fullNameField.getText(),
                    (this.statusActiveRadioButton.isSelected() ? "ACTIVE" : "INACTIVE"),
                    (this.currentManagerCheckBox.isSelected() ? "Y" : "N")
            ), userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Done!");
        this.setVisible(false);

    }

    private void delete() {
        try {
            new UserProcess().delete(userId);

        } catch (SQLException e1) {
            e1.printStackTrace();
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


    private void listPermisions()  {
        /*
        try {

            Object[][] data = new FunctionalityProcess().getFullFunctionalityDataByUserId(userId);

            if(data != null && data.length > 0){
                new ResultPermissionFrame(data, userId).setVisible(true);
            } else {
                new AddPermissionFrame(userId).setVisible(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        */
    }

}