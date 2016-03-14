package util.swing;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author Bleno Vale
 */
public class MyTable {

    private JTable table;

    public MyTable(JTable table) {
        this.table = table;
    }

    public void putButton() {
        table.getColumn("Avaliar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Avaliar").setCellEditor(
                new ButtonEditor(new JCheckBox()));
    }

    public void putCombobox(int Column, JComboBox comboBox) {
        TableColumn tableColumn = table.getColumnModel().getColumn(Column);
        tableColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }
}
