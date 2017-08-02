package client.views.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseViewActionListener implements ActionListener{
    private JFrame frame;
    private JDialog dialog;

    public CloseViewActionListener(JFrame frame) {
        this.frame = frame;
    }
    public CloseViewActionListener(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(frame != null) frame.setVisible(false);
        if(dialog != null) dialog.setVisible(false);
    }
}
