package jpa.extension;

import javax.persistence.EntityManagerFactory;
import jpa.HistoricoJpaController;

/**
 *
 * @author Bleno Vale
 */
public class JpaHistorico extends HistoricoJpaController{

    public JpaHistorico(EntityManagerFactory emf) {
        super(emf);
    }
      
}
