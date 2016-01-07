package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.ProblemaJpaController;
import model.Problema;

/**
 *
 * @author Bleno Vale
 */
public class JpaProblema extends ProblemaJpaController {

    public JpaProblema(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Problema> findProblemasByIdOrganizacao(int idOrg) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Problema> list = entityManager
                    .createQuery("SELECT p FROM Problema p WHERE p.idOrganizacao.id =:idOrg")
                    .setParameter("idOrg", idOrg)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }

    public Problema findProblemasByNameANDIdOrg(String name, int idOrg) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Problema problema = (Problema) entityManager
                    .createQuery("SELECT p FROM Problema p WHERE p.nome =:nome AND p.idOrganizacao.id =:idOrg")
                    .setParameter("nome", name).setParameter("idOrg", idOrg)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return problema;
        } catch (Exception error) {
            throw null;
        }
    }

}
