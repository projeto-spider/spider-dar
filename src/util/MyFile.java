package util;

import java.awt.Desktop;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Bleno Vale
 */
public class MyFile {

    public MyFile() {

    }

    public String chooseFile() {
        JFileChooser jFileChooser = new JFileChooser();

        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".pdf", "pdf"));
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".doc", "doc"));
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));

        int value = jFileChooser.showOpenDialog(null);

        if (value == JFileChooser.APPROVE_OPTION) {
            System.out.println(">> Noome do arquivo escolhido é:"
                    + jFileChooser.getSelectedFile().getPath());

            return jFileChooser.getSelectedFile().getPath();
        } else {
            return null;
        }
    }

    public void openFile(String path) {
        try {
            File file = new File(path);
            Desktop.getDesktop().open(file);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo.\n"
                    + "Por favor, verifique se o arquivo não foi deletado ou mudou de diretório.","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }
}
