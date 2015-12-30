package jpa.extension;

import javax.persistence.EntityManagerFactory;
import jpa.ProblemaJpaController;

/**
 *
 * @author Bleno Vale
 */
public class JpaProblema extends ProblemaJpaController {

    public JpaProblema(EntityManagerFactory emf) {
        super(emf);
    }

}
