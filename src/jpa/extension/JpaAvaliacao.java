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
    
    public List<Avaliar> findAlternativasByProblema(int idAlternativa) { 
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Avaliar> list = entityManager
                    .createQuery("SELECT a FROM Avaliar a WHERE a.alternativa.id =:idAlternativa")
                    .setParameter("idAlternativa", idAlternativa)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
}
