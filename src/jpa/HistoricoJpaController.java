/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import model.Historico;
import model.Problema;

/**
 *
 * @author Sandro Bezerra
 */
public class HistoricoJpaController implements Serializable {

    public HistoricoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historico historico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Problema idProblema = historico.getIdProblema();
            if (idProblema != null) {
                idProblema = em.getReference(idProblema.getClass(), idProblema.getId());
                historico.setIdProblema(idProblema);
            }
            em.persist(historico);
            if (idProblema != null) {
                idProblema.getHistoricoList().add(historico);
                idProblema = em.merge(idProblema);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historico historico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historico persistentHistorico = em.find(Historico.class, historico.getId());
            Problema idProblemaOld = persistentHistorico.getIdProblema();
            Problema idProblemaNew = historico.getIdProblema();
            if (idProblemaNew != null) {
                idProblemaNew = em.getReference(idProblemaNew.getClass(), idProblemaNew.getId());
                historico.setIdProblema(idProblemaNew);
            }
            historico = em.merge(historico);
            if (idProblemaOld != null && !idProblemaOld.equals(idProblemaNew)) {
                idProblemaOld.getHistoricoList().remove(historico);
                idProblemaOld = em.merge(idProblemaOld);
            }
            if (idProblemaNew != null && !idProblemaNew.equals(idProblemaOld)) {
                idProblemaNew.getHistoricoList().add(historico);
                idProblemaNew = em.merge(idProblemaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historico.getId();
                if (findHistorico(id) == null) {
                    throw new NonexistentEntityException("The historico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historico historico;
            try {
                historico = em.getReference(Historico.class, id);
                historico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historico with id " + id + " no longer exists.", enfe);
            }
            Problema idProblema = historico.getIdProblema();
            if (idProblema != null) {
                idProblema.getHistoricoList().remove(historico);
                idProblema = em.merge(idProblema);
            }
            em.remove(historico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historico> findHistoricoEntities() {
        return findHistoricoEntities(true, -1, -1);
    }

    public List<Historico> findHistoricoEntities(int maxResults, int firstResult) {
        return findHistoricoEntities(false, maxResults, firstResult);
    }

    private List<Historico> findHistoricoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historico.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Historico findHistorico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historico.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historico> rt = cq.from(Historico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
