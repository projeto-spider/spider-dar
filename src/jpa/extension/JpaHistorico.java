package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.HistoricoJpaController;
import model.Historico;

/**
 *
 * @author Bleno Vale
 */
public class JpaHistorico extends HistoricoJpaController{

    public JpaHistorico(EntityManagerFactory emf) {
        super(emf);
    }
      
    public List<Historico> findHistoricoByProblema(int idProblema){
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();
            
            List<Historico> list = entityManager
                    .createQuery("SELECT h FROM Historico h WHERE h.idProblema.id =:idProblema ORDER BY h.id DESC")
                    .setParameter("idProblema", idProblema)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
    public List<Historico> findHistoricoByDescricao(String descricao, int idProblema){
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();
            
            List<Historico> list = entityManager
                    .createQuery("SELECT h FROM Historico h WHERE h.idProblema.id =:idProblema AND h.descricao LIKE :descricao ORDER BY h.id DESC")
                    .setParameter("idProblema", idProblema)
                    .setParameter("descricao", "%" + descricao + "%")
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
}
