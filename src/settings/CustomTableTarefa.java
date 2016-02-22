package settings;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Bleno Vale
 */
public class CustomTableTarefa extends JTextPane implements TableCellRenderer {

    public CustomTableTarefa() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        this.setText(value.toString());
        this.setFont(table.getFont());

        if (isSelected) {
            this.setForeground(table.getSelectionForeground());
            this.setBackground(table.getSelectionBackground());
        } else {

            if (row % 2 == 0) {
                this.setForeground(table.getForeground());
                this.setBackground(Color.WHITE);
            } else {
                this.setForeground(table.getForeground());
                this.setBackground(new Color(229, 229, 229));
            }
        }

        this.setForeground(Color.BLACK);
        paintCell(column);

        // Aumentao tamanho da celula da tabela conforme o número de linhas que a preenche. 
        setText((value == null) ? "" : value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(),
                getPreferredSize().height);

        if (table.getRowHeight(row) < getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }

        return this;
    }

    private void paintCell(int column) {
        if (column == 3) {
            switch (this.getText()) {
                case "TRIVIAL":
                    this.setBackground(new Color(71, 235, 137));
                    break;
                case "PEQUENO":
                    this.setBackground(new Color(102, 204, 255));
                    break;
                case "MEDÍO":
                    this.setBackground(new Color(255, 255, 102));
                    break;
                case "GRANDE":
                    this.setBackground(new Color(255, 102, 102));
                    break;
            }
        }
    }
}
