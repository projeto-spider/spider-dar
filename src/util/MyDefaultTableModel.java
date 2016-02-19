package util;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bleno Vale
 */
public class MyDefaultTableModel extends DefaultTableModel {

    private boolean isEditable;
    private boolean hasIcon;
    private int columnCheck;
    private ImageIcon icon;
    /**
     * Cria um modelo para a JTable
     *
     * @param columnNames Nomes das colunas da tabela
     * @param rowCount Numero de linhas na tabela
     * @param isEditable true se as celulas sao editaveis, caso contrario false;
     */
    public MyDefaultTableModel(Object[] columnNames, int rowCount, boolean isEditable) {
        super(columnNames, rowCount);
        this.isEditable = isEditable;
    }
    
    // Tabelas que possuem icone
    public MyDefaultTableModel(Object[] columnNames, int rowCount, boolean isEditable, boolean hasIcon, int columnCheck) {
        super(columnNames, rowCount);
        this.isEditable = isEditable;
        this.hasIcon =  hasIcon;
        this.columnCheck = columnCheck;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == columnCheck) {
            return true;
        } else {
            return isEditable;
        }
    }
    
    @Override
    public Class getColumnClass(int column){
        if (hasIcon){
            return getValueAt(0, column).getClass();
        } else{
            return Object.class;
        }
    }
    
}
