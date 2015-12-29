package jpa.extension;

import javax.persistence.EntityManagerFactory;
import jpa.UsuarioJpaController;

/**
 *
 * @author Bleno Vale
 */
public class JpaUsuario extends UsuarioJpaController{

    public JpaUsuario(EntityManagerFactory emf) {
        super(emf);
    }
    
}
