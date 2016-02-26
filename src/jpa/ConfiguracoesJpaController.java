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
import model.Configuracoes;

/**
 *
 * @author Bleno Vale
 */
public class ConfiguracoesJpaController implements Serializable {

    public ConfiguracoesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Configuracoes configuracoes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(configuracoes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Configuracoes configuracoes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            configuracoes = em.merge(configuracoes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configuracoes.getId();
                if (findConfiguracoes(id) == null) {
                    throw new NonexistentEntityException("The configuracoes with id " + id + " no longer exists.");
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
            Configuracoes configuracoes;
            try {
                configuracoes = em.getReference(Configuracoes.class, id);
                configuracoes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configuracoes with id " + id + " no longer exists.", enfe);
            }
            em.remove(configuracoes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Configuracoes> findConfiguracoesEntities() {
        return findConfiguracoesEntities(true, -1, -1);
    }

    public List<Configuracoes> findConfiguracoesEntities(int maxResults, int firstResult) {
        return findConfiguracoesEntities(false, maxResults, firstResult);
    }

    private List<Configuracoes> findConfiguracoesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Configuracoes.class));
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

    public Configuracoes findConfiguracoes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Configuracoes.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfiguracoesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Configuracoes> rt = cq.from(Configuracoes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
