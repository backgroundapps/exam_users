package client.views.user;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class JScrollUserPanelBuilder<T> {

    private AbstractTableModel tableModel;
    private JTable table;
    private ResultUserFrame resultUserFrame;
    public JScrollUserPanelBuilder(AbstractTableModel tableModel, ResultUserFrame resultUserFrame) {
        this.tableModel = tableModel;
        this.resultUserFrame = resultUserFrame;
    }

    public JScrollPane getTable(){
        resultUserFrame.setVisible(false);
        table = new JTable(tableModel);

        //Set the column sorting functionality on
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    new EditUserFrame((Long)table.getValueAt(row, 0)).setVisible(true);
                }


            }
        });

        //Place the JTable object in a JScrollPane for a scrolling table
        JScrollPane tableScrollPane = new JScrollPane(table);

        return tableScrollPane;
    }
}
