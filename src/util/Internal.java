package util;

import javax.swing.JInternalFrame;

/**
 *
 * @author Bleno Vale
 */
public class Internal {

    public static void retiraBorda(JInternalFrame internal) {
        ((javax.swing.plaf.basic.BasicInternalFrameUI) internal.getUI()).setNorthPane(null);//retirar o painel superior  
        internal.setBorder(null);//retirar bordas 
    }
}
