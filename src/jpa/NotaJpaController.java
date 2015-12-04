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
import model.Criterio;
import model.Nota;

/**
 *
 * @author Spider
 */
public class NotaJpaController implements Serializable {

    public NotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nota nota) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Criterio idCriterio = nota.getIdCriterio();
            if (idCriterio != null) {
                idCriterio = em.getReference(idCriterio.getClass(), idCriterio.getId());
                nota.setIdCriterio(idCriterio);
            }
            em.persist(nota);
            if (idCriterio != null) {
                idCriterio.getNotaList().add(nota);
                idCriterio = em.merge(idCriterio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nota nota) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nota persistentNota = em.find(Nota.class, nota.getId());
            Criterio idCriterioOld = persistentNota.getIdCriterio();
            Criterio idCriterioNew = nota.getIdCriterio();
            if (idCriterioNew != null) {
                idCriterioNew = em.getReference(idCriterioNew.getClass(), idCriterioNew.getId());
                nota.setIdCriterio(idCriterioNew);
            }
            nota = em.merge(nota);
            if (idCriterioOld != null && !idCriterioOld.equals(idCriterioNew)) {
                idCriterioOld.getNotaList().remove(nota);
                idCriterioOld = em.merge(idCriterioOld);
            }
            if (idCriterioNew != null && !idCriterioNew.equals(idCriterioOld)) {
                idCriterioNew.getNotaList().add(nota);
                idCriterioNew = em.merge(idCriterioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nota.getId();
                if (findNota(id) == null) {
                    throw new NonexistentEntityException("The nota with id " + id + " no longer exists.");
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
            Nota nota;
            try {
                nota = em.getReference(Nota.class, id);
                nota.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nota with id " + id + " no longer exists.", enfe);
            }
            Criterio idCriterio = nota.getIdCriterio();
            if (idCriterio != null) {
                idCriterio.getNotaList().remove(nota);
                idCriterio = em.merge(idCriterio);
            }
            em.remove(nota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nota> findNotaEntities() {
        return findNotaEntities(true, -1, -1);
    }

    public List<Nota> findNotaEntities(int maxResults, int firstResult) {
        return findNotaEntities(false, maxResults, firstResult);
    }

    private List<Nota> findNotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nota.class));
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

    public Nota findNota(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nota.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nota> rt = cq.from(Nota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
