package client.views.user;

import client.Client;
import client.views.components.DefaultProperties;
import server.process.UserProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

public class SearchUserFrame extends JDialog {

    JLabel loginLabel = new JLabel("Login : ");
    JLabel fullNameLabel = new JLabel("Full Name : ");
    JLabel statusLabel = new JLabel("Status : ");
    JLabel currentManagerLabel = new JLabel("Current Manager : ");
    JLabel pluginsLabel = new JLabel("Plugins : ");
    JLabel functionalitiesLabel = new JLabel("Functionalities : ");


    JTextField loginField = new JTextField();
    JTextField fullNameField = new JTextField();
    JComboBox<String> pluginsComboBox;

    JComboBox<String> functionalitiesComboBox;

    JRadioButton statusActiveRadioButton = new JRadioButton("ACTIVE");
    JRadioButton statusInactiveRadioButton = new JRadioButton("INACTIVE");

    JCheckBox currentManagerCheckBox = new JCheckBox();
    JButton searchButton = new JButton("Search");
    JButton cancelButton = new JButton("Cancel");


    public SearchUserFrame() {
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, 300);

    }

    public void setupUI() {
        this.setTitle("SEARCH USER");

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(searchButton);
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


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        topPanel.add(pluginsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        //TODO Update root method
        Vector<String> vp = new Vector<>();
        vp.add("");
        vp.addAll(Arrays.asList(getPluginNames()));
        pluginsComboBox = new JComboBox<>(vp);
        topPanel.add(pluginsComboBox, gbc);



        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        topPanel.add(functionalitiesLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        //TODO Update root method
        Vector<String> vf = new Vector<>();
        vf.add("");
        vf.addAll(Arrays.asList(getFunctionalityNames()));
        functionalitiesComboBox = new JComboBox<>(vf);

        topPanel.add(functionalitiesComboBox, gbc);


        this.add(topPanel);

        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {

        loginField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    search();
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


        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SearchUserFrame.this.setVisible(false);
            }
        });
    }


    private void search()  {
        try {

            Object[][] data = new UserProcess().getFullUserData(
                    this.loginField.getText(),
                    this.fullNameField.getText(),
                    (this.statusActiveRadioButton.isSelected() ? "ACTIVE" : "INACTIVE"),
                    (this.currentManagerCheckBox.isSelected() ? "Y" : "N"),
                    this.pluginsComboBox.getSelectedItem().toString(),
                    this.functionalitiesComboBox.getSelectedItem().toString()

            );

            if(data != null && data.length > 0){
                new ResultUserFrame(data, null).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String[] getPluginNames(){
        return new String[]{"A", "B"};
    }

    public String[] getFunctionalityNames() {
        return new String[]{"A", "B"};
    }
}