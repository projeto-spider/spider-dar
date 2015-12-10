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
import model.Alternativa;
import model.Decisao;

/**
 *
 * @author Sandro Bezerra
 */
public class DecisaoJpaController implements Serializable {

    public DecisaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Decisao decisao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alternativa idAlternativa = decisao.getIdAlternativa();
            if (idAlternativa != null) {
                idAlternativa = em.getReference(idAlternativa.getClass(), idAlternativa.getId());
                decisao.setIdAlternativa(idAlternativa);
            }
            em.persist(decisao);
            if (idAlternativa != null) {
                idAlternativa.getDecisaoList().add(decisao);
                idAlternativa = em.merge(idAlternativa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Decisao decisao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Decisao persistentDecisao = em.find(Decisao.class, decisao.getId());
            Alternativa idAlternativaOld = persistentDecisao.getIdAlternativa();
            Alternativa idAlternativaNew = decisao.getIdAlternativa();
            if (idAlternativaNew != null) {
                idAlternativaNew = em.getReference(idAlternativaNew.getClass(), idAlternativaNew.getId());
                decisao.setIdAlternativa(idAlternativaNew);
            }
            decisao = em.merge(decisao);
            if (idAlternativaOld != null && !idAlternativaOld.equals(idAlternativaNew)) {
                idAlternativaOld.getDecisaoList().remove(decisao);
                idAlternativaOld = em.merge(idAlternativaOld);
            }
            if (idAlternativaNew != null && !idAlternativaNew.equals(idAlternativaOld)) {
                idAlternativaNew.getDecisaoList().add(decisao);
                idAlternativaNew = em.merge(idAlternativaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = decisao.getId();
                if (findDecisao(id) == null) {
                    throw new NonexistentEntityException("The decisao with id " + id + " no longer exists.");
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
            Decisao decisao;
            try {
                decisao = em.getReference(Decisao.class, id);
                decisao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The decisao with id " + id + " no longer exists.", enfe);
            }
            Alternativa idAlternativa = decisao.getIdAlternativa();
            if (idAlternativa != null) {
                idAlternativa.getDecisaoList().remove(decisao);
                idAlternativa = em.merge(idAlternativa);
            }
            em.remove(decisao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Decisao> findDecisaoEntities() {
        return findDecisaoEntities(true, -1, -1);
    }

    public List<Decisao> findDecisaoEntities(int maxResults, int firstResult) {
        return findDecisaoEntities(false, maxResults, firstResult);
    }

    private List<Decisao> findDecisaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Decisao.class));
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

    public Decisao findDecisao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Decisao.class, id);
        } finally {
            em.close();
        }
    }

    public int getDecisaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Decisao> rt = cq.from(Decisao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
