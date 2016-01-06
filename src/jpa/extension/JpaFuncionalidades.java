package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.FuncionalidadesJpaController;
import model.Funcionalidades;

/**
 *
 * @author Bleno Vale
 */
public class JpaFuncionalidades extends FuncionalidadesJpaController {

    public JpaFuncionalidades(EntityManagerFactory emf) {
        super(emf);
    }
}
