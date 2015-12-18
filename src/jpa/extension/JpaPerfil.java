package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.PerfilJpaController;
import model.Funcionalidades;
import model.Perfil;

/**
 *
 * @author Bleno Vale
 */
public class JpaPerfil extends PerfilJpaController {

    public JpaPerfil(EntityManagerFactory emf) {
        super(emf);
    }

    /**
     * Busca Perfil pelo nome.
     *
     * @param name
     * @return
     */
    public Perfil findPerfilByNameAndNome(String name, int idOrg) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Perfil perfil = (Perfil) entityManager
                    .createQuery("SELECT p FROM Perfil p WHERE p.nome =:name AND p.idOrganizacao.id =:idOrg")
                    .setParameter("name", name).setParameter("idOrg", idOrg)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return perfil;
        } catch (Exception error) {
            return null;
        }
    }
    
    public Perfil findPerfilByName(String name) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Perfil perfil = (Perfil) entityManager
                    .createQuery("SELECT p FROM Perfil p WHERE p.nome =:name")
                    .setParameter("name", name)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return perfil;
        } catch (Exception error) {
            return null;
        }
    }

    public List<Perfil> findPerfisByIdOrganizacao(int idOrg) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Perfil> list = entityManager
                    .createQuery("SELECT p FROM Perfil p WHERE p.idOrganizacao.id =:idOrg")
                    .setParameter("idOrg", idOrg)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Perfil findAnotherPerfilWithSameName(String name, int id) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Perfil perfil = (Perfil) entityManager
                    .createQuery("SELECT p FROM Perfil p WHERE p.nome =:name AND p.id <>:id")
                    .setParameter("name", name).setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return perfil;
        } catch (Exception error) {
            return null;
        }
    }
    
    public Perfil findPerfilByID(int id) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Perfil perfil = (Perfil) entityManager
                    .createQuery("SELECT p FROM Perfil p WHERE p.id =:id")
                    .setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return perfil;
        } catch (Exception error) {
            throw error;
        }
    }
}
