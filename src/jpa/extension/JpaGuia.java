package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.GuiaJpaController;
import model.Guia;
import model.Itemguia;

/**
 *
 * @author Bleno Vale
 */
public class JpaGuia extends GuiaJpaController {

    public JpaGuia(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Itemguia> findListItemGuiaByIdGuia(int idGuia) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Itemguia> list = entityManager.createQuery("SELECT i FROM Itemguia i WHERE i.idGuia.id =:idGuia ORDER BY i.id ASC")
                    .setParameter("idGuia", idGuia)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }

    public Guia findGuiaByIdOrganizacao(int idOrg) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Guia guia = (Guia) entityManager.createQuery("SELECT g FROM Guia g WHERE g.idOrganizacao.id =:idOrg")
                    .setParameter("idOrg", idOrg)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return guia;
        } catch (Exception error) {
            return null;
        }
    }

    public Guia findGuiaByIdOrganizacaoAndTipo(int idOrg, String tipo) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Guia guia = (Guia) entityManager.createQuery("SELECT g FROM Guia g WHERE g.idOrganizacao.id =:idOrg AND g.tipo =:tipo")
                    .setParameter("idOrg", idOrg).setParameter("tipo", tipo)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return guia;
        } catch (Exception error) {
            throw error;
        }
    }
}
