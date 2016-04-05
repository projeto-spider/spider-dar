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
import model.Guia;
import model.Itemguia;

/**
 *
 * @author Sandro Bezerra
 */
public class ItemguiaJpaController implements Serializable {

    public ItemguiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itemguia itemguia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Guia idGuia = itemguia.getIdGuia();
            if (idGuia != null) {
                idGuia = em.getReference(idGuia.getClass(), idGuia.getId());
                itemguia.setIdGuia(idGuia);
            }
            em.persist(itemguia);
            if (idGuia != null) {
                idGuia.getItemguiaList().add(itemguia);
                idGuia = em.merge(idGuia);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itemguia itemguia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itemguia persistentItemguia = em.find(Itemguia.class, itemguia.getId());
            Guia idGuiaOld = persistentItemguia.getIdGuia();
            Guia idGuiaNew = itemguia.getIdGuia();
            if (idGuiaNew != null) {
                idGuiaNew = em.getReference(idGuiaNew.getClass(), idGuiaNew.getId());
                itemguia.setIdGuia(idGuiaNew);
            }
            itemguia = em.merge(itemguia);
            if (idGuiaOld != null && !idGuiaOld.equals(idGuiaNew)) {
                idGuiaOld.getItemguiaList().remove(itemguia);
                idGuiaOld = em.merge(idGuiaOld);
            }
            if (idGuiaNew != null && !idGuiaNew.equals(idGuiaOld)) {
                idGuiaNew.getItemguiaList().add(itemguia);
                idGuiaNew = em.merge(idGuiaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itemguia.getId();
                if (findItemguia(id) == null) {
                    throw new NonexistentEntityException("The itemguia with id " + id + " no longer exists.");
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
            Itemguia itemguia;
            try {
                itemguia = em.getReference(Itemguia.class, id);
                itemguia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itemguia with id " + id + " no longer exists.", enfe);
            }
            Guia idGuia = itemguia.getIdGuia();
            if (idGuia != null) {
                idGuia.getItemguiaList().remove(itemguia);
                idGuia = em.merge(idGuia);
            }
            em.remove(itemguia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itemguia> findItemguiaEntities() {
        return findItemguiaEntities(true, -1, -1);
    }

    public List<Itemguia> findItemguiaEntities(int maxResults, int firstResult) {
        return findItemguiaEntities(false, maxResults, firstResult);
    }

    private List<Itemguia> findItemguiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itemguia.class));
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

    public Itemguia findItemguia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itemguia.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemguiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itemguia> rt = cq.from(Itemguia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
