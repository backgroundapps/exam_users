package client.views.user;

import client.views.components.DefaultProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultUserFrame extends JDialog {
    JButton cancelButton = new JButton("Cancel");
    private Object[][] data;
    private Long userId;


    public ResultUserFrame(Object[][] data, Long userId) {
        this.userId = userId;
        this.data = data;
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

    }

    public void setupUI() {

        this.setTitle("RESULT USER");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(new JLabel("To sort the result click on the column title."));
        buttonPanel.add(cancelButton);
        this.add(new JScrollUserPanelBuilder(new UserTableModel(data), this).getTable());
        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultUserFrame.this.setVisible(false);
            }
        });
    }

}