package client.views.actions;

import client.Client;
import client.views.components.JDialogBean;
import common.User;
import server.process.UserProcess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class RegisterUserActionListener implements ActionListener{

    private JDialogBean frame;
    private User user;

    public RegisterUserActionListener(JDialogBean frame) {
        this.frame = frame;
        this.user = (User)frame.getBean();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        register();

    }

    public void register() {
        try {
            Client.getServer().createUser(user);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Done!");
        frame.setVisible(false);
    }
}
