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
import jpa.exceptions.PreexistingEntityException;
import model.Avaliar;
import model.AvaliarPK;

/**
 *
 * @author Sandro Bezerra
 */
public class AvaliarJpaController implements Serializable {

    public AvaliarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Avaliar avaliar) throws PreexistingEntityException, Exception {
        if (avaliar.getAvaliarPK() == null) {
            avaliar.setAvaliarPK(new AvaliarPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(avaliar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAvaliar(avaliar.getAvaliarPK()) != null) {
                throw new PreexistingEntityException("Avaliar " + avaliar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Avaliar avaliar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            avaliar = em.merge(avaliar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AvaliarPK id = avaliar.getAvaliarPK();
                if (findAvaliar(id) == null) {
                    throw new NonexistentEntityException("The avaliar with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AvaliarPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliar avaliar;
            try {
                avaliar = em.getReference(Avaliar.class, id);
                avaliar.getAvaliarPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliar with id " + id + " no longer exists.", enfe);
            }
            em.remove(avaliar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Avaliar> findAvaliarEntities() {
        return findAvaliarEntities(true, -1, -1);
    }

    public List<Avaliar> findAvaliarEntities(int maxResults, int firstResult) {
        return findAvaliarEntities(false, maxResults, firstResult);
    }

    private List<Avaliar> findAvaliarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Avaliar.class));
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

    public Avaliar findAvaliar(AvaliarPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Avaliar.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvaliarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Avaliar> rt = cq.from(Avaliar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
