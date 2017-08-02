package client.views.components;

import javax.swing.*;

public abstract class JDialogBean extends JDialog implements ReturnableBean{
    @Override
    public abstract Object getBean();
}
