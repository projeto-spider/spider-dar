package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.UsuarioJpaController;
import model.Acessar;
import model.Usuario;

/**
 *
 * @author Bleno Vale
 */
public class JpaUsuario extends UsuarioJpaController {

    public JpaUsuario(EntityManagerFactory emf) {
        super(emf);
    }

    public Usuario findUsuarioByNome(String name) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Usuario usuario = (Usuario) entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nome =:nome")
                    .setParameter("nome", name).getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return usuario;
        } catch (Exception error) {
            return null;
        }
    }

    public Usuario findUsuarioByLogin(String login) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Usuario usuario = (Usuario) entityManager.createQuery("SELECT u FROM Usuario u WHERE u.login = :login")
                    .setParameter("login", login).getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return usuario;
        } catch (Exception error) {
            return null;
        }
    }

    public List<Usuario> findUsuarioByPartName(String name) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Usuario> list = entityManager
                    .createQuery("SELECT u FROM Usuario u WHERE u.nome LIKE :name ORDER BY u.nome ASC")
                    .setParameter("name", name + "%")
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Usuario findAnotherPerfilWithSameName(String name, int id) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Usuario usuario = (Usuario) entityManager
                    .createQuery("SELECT u FROM Usuario u WHERE u.nome =:name AND u.id <>:id")
                    .setParameter("name", name).setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return usuario;
        } catch (Exception error) {
            return null;
        }
    }
    
    public List<Usuario> findUsuarioByEmail(String email) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email").setParameter("email", email).getResultList();
        } catch (Exception error) {
            throw error;
        }
    }
    
    public List<Acessar> findAcessoByUsuario(int id) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Acessar> list = entityManager
                    .createQuery("SELECT a FROM Acessar a WHERE a.usuario.id =:id")
                    .setParameter("id", id)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
}
