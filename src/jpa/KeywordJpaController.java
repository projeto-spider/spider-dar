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
import model.Keyword;
import model.KeywordPK;
import model.Problema;

/**
 *
 * @author Sandro Bezerra
 */
public class KeywordJpaController implements Serializable {

    public KeywordJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Keyword keyword) throws PreexistingEntityException, Exception {
        if (keyword.getKeywordPK() == null) {
            keyword.setKeywordPK(new KeywordPK());
        }
        keyword.getKeywordPK().setIdForeignKey(keyword.getProblema().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Problema problema = keyword.getProblema();
            if (problema != null) {
                problema = em.getReference(problema.getClass(), problema.getId());
                keyword.setProblema(problema);
            }
            em.persist(keyword);
            if (problema != null) {
                problema.getKeywordList().add(keyword);
                problema = em.merge(problema);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKeyword(keyword.getKeywordPK()) != null) {
                throw new PreexistingEntityException("Keyword " + keyword + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Keyword keyword) throws NonexistentEntityException, Exception {
        keyword.getKeywordPK().setIdForeignKey(keyword.getProblema().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Keyword persistentKeyword = em.find(Keyword.class, keyword.getKeywordPK());
            Problema problemaOld = persistentKeyword.getProblema();
            Problema problemaNew = keyword.getProblema();
            if (problemaNew != null) {
                problemaNew = em.getReference(problemaNew.getClass(), problemaNew.getId());
                keyword.setProblema(problemaNew);
            }
            keyword = em.merge(keyword);
            if (problemaOld != null && !problemaOld.equals(problemaNew)) {
                problemaOld.getKeywordList().remove(keyword);
                problemaOld = em.merge(problemaOld);
            }
            if (problemaNew != null && !problemaNew.equals(problemaOld)) {
                problemaNew.getKeywordList().add(keyword);
                problemaNew = em.merge(problemaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                KeywordPK id = keyword.getKeywordPK();
                if (findKeyword(id) == null) {
                    throw new NonexistentEntityException("The keyword with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(KeywordPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Keyword keyword;
            try {
                keyword = em.getReference(Keyword.class, id);
                keyword.getKeywordPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The keyword with id " + id + " no longer exists.", enfe);
            }
            Problema problema = keyword.getProblema();
            if (problema != null) {
                problema.getKeywordList().remove(keyword);
                problema = em.merge(problema);
            }
            em.remove(keyword);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Keyword> findKeywordEntities() {
        return findKeywordEntities(true, -1, -1);
    }

    public List<Keyword> findKeywordEntities(int maxResults, int firstResult) {
        return findKeywordEntities(false, maxResults, firstResult);
    }

    private List<Keyword> findKeywordEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Keyword.class));
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

    public Keyword findKeyword(KeywordPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Keyword.class, id);
        } finally {
            em.close();
        }
    }

    public int getKeywordCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Keyword> rt = cq.from(Keyword.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
