package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.AlternativaJpaController;
import model.Alternativa;

/**
 *
 * @author Bleno Vale
 */
public class JpaAlternativa extends AlternativaJpaController{

    public JpaAlternativa(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<Alternativa> findAlternativasByProblema(int idProblema){
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Alternativa> list = entityManager
                    .createQuery("SELECT a FROM Alternativa a WHERE a.idProblema.id =:idProblema ORDER BY a.id ASC")
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
