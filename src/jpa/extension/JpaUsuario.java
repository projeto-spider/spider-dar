package jpa.extension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.UsuarioJpaController;
import model.Usuario;

/**
 *
 * @author Bleno Vale
 */
public class JpaUsuario extends UsuarioJpaController {

    public JpaUsuario(EntityManagerFactory emf) {
        super(emf);
    }

    public Usuario findUsuarioByNome(String nome) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Usuario usuario = (Usuario) entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nome =:nome")
                    .setParameter("nome", nome).getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return usuario;
        } catch (Exception error) {
            return null;
        }
    }

}
