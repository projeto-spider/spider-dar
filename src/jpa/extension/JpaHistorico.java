package jpa.extension;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.HistoricoJpaController;
import model.Historico;

/**
 *
 * @author Bleno Vale
 */
public class JpaHistorico extends HistoricoJpaController {

    public JpaHistorico(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Historico> findHistoricoByProblema(int idProblema) {
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

    public List<Historico> findHistoricoByDescricao(String descricao, int idProblema) {
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

    public List<Historico> findHistoricoByPartDescricaoAndDates(String descricao, int idProblema, Date dataInicio, Date dataFim) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Historico> list = entityManager
                    .createQuery("SELECT h FROM Historico h WHERE h.idProblema.id =:idProblema AND h.descricao LIKE :descricao AND (h.created >=:dataInicio AND h.created <=:dataFim) ORDER BY h.id DESC")
                    .setParameter("idProblema", idProblema)
                    .setParameter("descricao", "%" + descricao + "%")
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
    public List<Historico> findHistoricoByPartDescricaoAndDatesAndType(String descricao, int idProblema, Date dataInicio, Date dataFim, int tipo) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Historico> list = entityManager
                    .createQuery("SELECT h FROM Historico h WHERE h.idProblema.id =:idProblema AND h.descricao LIKE :descricao AND (h.created >= :dataInicio AND h.created <= :dataFim) AND h.tipo =:tipo ORDER BY h.id DESC")
                    .setParameter("idProblema", idProblema)
                    .setParameter("descricao", "%" + descricao + "%")
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .setParameter("tipo", tipo)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
}
