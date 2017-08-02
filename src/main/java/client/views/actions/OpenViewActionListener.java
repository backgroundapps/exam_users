package client.views.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenViewActionListener implements ActionListener{
    private JDialog dialog;

    public OpenViewActionListener(JDialog dialog) {
        this.dialog = dialog;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(dialog != null) dialog.setVisible(true);
    }
}
