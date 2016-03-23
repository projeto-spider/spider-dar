package jpa.extension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.DecisaoJpaController;
import model.Decisao;

/**
 *
 * @author Bleno Vale
 */
public class JpaDecisao extends DecisaoJpaController {

    public JpaDecisao(EntityManagerFactory emf) {
        super(emf);
    }

    public Decisao findDecisaoByProblema(int idProblema) {
        Decisao decisao = null;
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            decisao = (Decisao) entityManager
                    .createQuery("SELECT d FROM Decisao d WHERE d.idProblema =:idProblema")
                    .setParameter("idProblema", idProblema)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return decisao;
        } catch (Exception error) {
            return decisao;
        }
    }

}
