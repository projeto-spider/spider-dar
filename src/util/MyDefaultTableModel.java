package util;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bleno Vale
 */
public class MyDefaultTableModel extends DefaultTableModel {
    
    private boolean isEditable;
    
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
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return isEditable;
    }
}
