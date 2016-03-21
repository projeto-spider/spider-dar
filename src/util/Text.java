package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Bleno Vale
 */
public class Text {
    
    public static String formatDate(Date data) {

        if (data == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE   dd / MMMM / yyyy");
        return sdf.format(data);
    }
    
    public static String formatDateForTable (Date data) {

        if (data == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd / MMMM / yyyy");
        return sdf.format(data);
    }
    
    public static String formatDateForTableHumanize(Date data)
    {
        if (data == null)
            return "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm:ss");
        
        return sdf.format(data);
    }
}
