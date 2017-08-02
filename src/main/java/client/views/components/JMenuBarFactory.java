package client.views.components;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

public class JMenuBarFactory {

    public static JMenuBar getJMenuBarFromMappedElements(Map<String, List<JMenuItem>> elements){
        JMenuBar menuBar = new JMenuBar();

        for (Map.Entry<String, List<JMenuItem>> element : elements.entrySet()){
            JMenu menu = new JMenu(element.getKey());
            menu.setMnemonic(KeyEvent.VK_F);

            for (JMenuItem menuItem : element.getValue()) {
                menu.add(menuItem);
            }

            menuBar.add(menu);
        }

        return menuBar;
    }


}
