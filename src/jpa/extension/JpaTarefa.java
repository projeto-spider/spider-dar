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
public class JpaTarefa extends TarefaJpaController {

    public JpaTarefa(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Tarefa> findTarefaByIdProblema(int idProblema) {
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

    public List<Tarefa> findTarefaByPartNomeAndFeitoAndIdProjeto(String nome, boolean feito, int idProblema) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Tarefa> list = entityManager
                    .createQuery("SELECT t FROM Tarefa t WHERE t.idProblema.id =:idProblema AND (t.nome LIKE :nome AND t.feito =:feito) ORDER BY t.data DESC")
                    .setParameter("nome", "%" + nome + "%")
                    .setParameter("feito", feito)
                    .setParameter("idProblema", idProblema)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
    public List<Tarefa> findTarefaByPartNomeAndIdProjeto(String nome, int idProblema) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Tarefa> list = entityManager
                    .createQuery("SELECT t FROM Tarefa t WHERE t.idProblema.id =:idProblema AND t.nome LIKE :nome ORDER BY t.data DESC")
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
