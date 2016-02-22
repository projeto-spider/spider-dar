package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.TarefaJpaController;
import model.Tarefa;

/**
 *
 * @author Bleno Vale
 */
public class JpaTarefa extends TarefaJpaController{

    public JpaTarefa(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<Tarefa> findTarefaByIdProblema(int idProblema){
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();
            
            List<Tarefa> list = entityManager
                    .createQuery("SELECT t FROM Tarefa t WHERE t.idProblema.id =:idProblema ORDER BY t.data DESC")
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
