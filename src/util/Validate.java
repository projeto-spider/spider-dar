package util;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Validate
{
    private final Request request;
    
    static class fieldTypeValue
    {
        public static final int TEXT = 1;
        public static final int TEXTAREA = 2;
        public static final int HIDDEN = 3;
        public static final int LIST = 4;
    }
    
    public Validate(Request request)
    {
        this.request = request;
    }
    
    public boolean isValidData()
    {
        HashMap<String, Input> fields = this.request.getAllHashMapDataInputs();
        int fieldsSize = fields.size();

        for (int index = 1; index <= fieldsSize; index++)
        {
            for (Map.Entry<String, Input> entry: fields.entrySet())
            {
                Input inputToValidate = entry.getValue();

                if (index == inputToValidate.getOrdem())
                {
                    HashMap<String,Integer> caseHash = new HashMap<>();
                    caseHash.put("text", 1);
                    caseHash.put("textarea", 2);
                    caseHash.put("hidden", 3);
                    caseHash.put("list", 4);
                    
                    String fieldType = inputToValidate.getTipo();
                    
                    int caseThisValue = caseHash.containsKey(fieldType) ? caseHash.get(fieldType) : -1;
                    
                    switch (caseThisValue)
                    {
                        case fieldTypeValue.TEXT:
                            if(!this.isValidText(inputToValidate))
                                return false;
                            break;
                        case fieldTypeValue.TEXTAREA:
                            if(!this.isValidTextarea(inputToValidate))
                                return false;
                            break;
                        case fieldTypeValue.HIDDEN:
                            break;
                        case fieldTypeValue.LIST:
                            if (!this.isValidListField(inputToValidate))
                                return false;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(),"ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
                            return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean isValidText(Input inputToValidate)
    {
        if (inputToValidate.getValor().trim().length() <= 0)
        {
            JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(),"ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean isValidTextarea(Input inputToValidate)
    {
        String newline = System.getProperty("line.separator");
        
        boolean hasNewline = inputToValidate.getValor().contains(newline);
        
        if (inputToValidate.getValor().trim().length() <= 0 || hasNewline)
        {
            JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(),"ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean isValidListField(Input inputToValidate)
    {
        if (inputToValidate.getValor().trim().length() <= 0)
        {
            JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(),"ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
