package client.views.components;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class JMenuItemBuilder {

    public static JMenuItem build(String name, ActionListener event){
        JMenuItem item = new JMenuItem(name, null);
        item.setMnemonic(KeyEvent.VK_E);
        item.addActionListener(event);

        return item;
    }
}
