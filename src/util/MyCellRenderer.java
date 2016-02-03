package util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Bleno Vale
 */
public class MyCellRenderer extends JTextPane implements TableCellRenderer {

    public MyCellRenderer() {
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
                this.setBackground(new Color(204,204,204));
            }
        }

        // Centraliza verticalmente o Texto na celula da tabela.
        StyledDocument doc = this.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        this.setDocument(doc);

        // Aumentao tamanho da celula da tabela conforme o número de linhas que a preenche. 
        setText((value == null) ? "" : value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(),
                getPreferredSize().height);

        if (table.getRowHeight(row) < getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }

        table.getTableHeader().setResizingAllowed(false);

        return this;
    }

    public void ComponentsRenderer(JTable table) {
        // Centraliza o nome das colunas da tabela.
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

    }

}
