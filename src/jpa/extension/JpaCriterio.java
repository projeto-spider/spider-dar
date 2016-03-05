package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.CriterioJpaController;
import model.Criterio;

/**
 *
 * @author Bleno Vale
 */
public class JpaCriterio extends CriterioJpaController{

    public JpaCriterio(EntityManagerFactory emf) {
        super(emf);
    }
    
    
    public List<Criterio> findCriterioByIdProblema(int idProblema) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Criterio> list = entityManager
                    .createQuery("SELECT c FROM Criterio c WHERE c.idProblema.id =:idProblema ORDER BY c.created DESC")
                    .setParameter("idProblema", idProblema)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
    public List<Criterio> findCriterioByPartnomeIdProblema(String nome, int idProblema) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Criterio> list = entityManager
                    .createQuery("SELECT c FROM Criterio c WHERE c.nome LIKE :nome AND c.idProblema.id =:idProblema ORDER BY c.created DESC")
                    .setParameter("nome", "%" + nome + "%")
                    .setParameter("idProblema", idProblema)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
}
