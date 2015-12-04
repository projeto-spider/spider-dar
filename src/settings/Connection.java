package settings;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bleno Vale
 */
public class Connection {
    
    /**
     * Método de conexão com o banco.
     * @return 
     */
    public static EntityManagerFactory connect(){
        return Persistence.createEntityManagerFactory("Spider-DARPU");
    }
}
