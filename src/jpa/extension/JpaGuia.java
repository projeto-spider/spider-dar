package jpa.extension;

import javax.persistence.EntityManagerFactory;
import jpa.GuiaJpaController;

/**
 *
 * @author Bleno Vale
 */
public class JpaGuia extends GuiaJpaController{

    public JpaGuia(EntityManagerFactory emf) {
        super(emf);
    }
    
    
}
