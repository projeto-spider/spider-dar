package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.AvaliarJpaController;
import model.Avaliar;

/**
 *
 * @author Bleno Vale
 */
public class JpaAvaliacao extends AvaliarJpaController {

    public JpaAvaliacao(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Avaliar> findAvaliarByIdAlternativa(int idAlternativa) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Avaliar> list = entityManager
                    .createQuery("SELECT a FROM Avaliar a WHERE a.idAlternativa.id =:idAlternativa")
                    .setParameter("idAlternativa", idAlternativa)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }

    public Avaliar findAvaliarByIdCriterioAndIdAlternativa(int idAlternativa, int idCriterio) {
        Avaliar avaliar = new Avaliar();
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            avaliar = (Avaliar) entityManager
                    .createQuery("SELECT a FROM Avaliar a WHERE a.idAlternativa.id =:idAlternativa AND a.idCriterio.id =:idCriterio")
                    .setParameter("idAlternativa", idAlternativa)
                    .setParameter("idCriterio", idCriterio)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return avaliar;
        } catch (Exception error) {
            return null;
        }
    }

}
