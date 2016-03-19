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
import model.Avaliar;
import model.Criterio;

/**
 *
 * @author Bleno Vale
 */
public class AvaliarJpaController implements Serializable {

    public AvaliarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Avaliar avaliar) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alternativa idAlternativa = avaliar.getIdAlternativa();
            if (idAlternativa != null) {
                idAlternativa = em.getReference(idAlternativa.getClass(), idAlternativa.getId());
                avaliar.setIdAlternativa(idAlternativa);
            }
            Criterio idCriterio = avaliar.getIdCriterio();
            if (idCriterio != null) {
                idCriterio = em.getReference(idCriterio.getClass(), idCriterio.getId());
                avaliar.setIdCriterio(idCriterio);
            }
            em.persist(avaliar);
            if (idAlternativa != null) {
                idAlternativa.getAvaliarList().add(avaliar);
                idAlternativa = em.merge(idAlternativa);
            }
            if (idCriterio != null) {
                idCriterio.getAvaliarList().add(avaliar);
                idCriterio = em.merge(idCriterio);
            }
            em.getTransaction().commit();
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
            Avaliar persistentAvaliar = em.find(Avaliar.class, avaliar.getId());
            Alternativa idAlternativaOld = persistentAvaliar.getIdAlternativa();
            Alternativa idAlternativaNew = avaliar.getIdAlternativa();
            Criterio idCriterioOld = persistentAvaliar.getIdCriterio();
            Criterio idCriterioNew = avaliar.getIdCriterio();
            if (idAlternativaNew != null) {
                idAlternativaNew = em.getReference(idAlternativaNew.getClass(), idAlternativaNew.getId());
                avaliar.setIdAlternativa(idAlternativaNew);
            }
            if (idCriterioNew != null) {
                idCriterioNew = em.getReference(idCriterioNew.getClass(), idCriterioNew.getId());
                avaliar.setIdCriterio(idCriterioNew);
            }
            avaliar = em.merge(avaliar);
            if (idAlternativaOld != null && !idAlternativaOld.equals(idAlternativaNew)) {
                idAlternativaOld.getAvaliarList().remove(avaliar);
                idAlternativaOld = em.merge(idAlternativaOld);
            }
            if (idAlternativaNew != null && !idAlternativaNew.equals(idAlternativaOld)) {
                idAlternativaNew.getAvaliarList().add(avaliar);
                idAlternativaNew = em.merge(idAlternativaNew);
            }
            if (idCriterioOld != null && !idCriterioOld.equals(idCriterioNew)) {
                idCriterioOld.getAvaliarList().remove(avaliar);
                idCriterioOld = em.merge(idCriterioOld);
            }
            if (idCriterioNew != null && !idCriterioNew.equals(idCriterioOld)) {
                idCriterioNew.getAvaliarList().add(avaliar);
                idCriterioNew = em.merge(idCriterioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = avaliar.getId();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliar avaliar;
            try {
                avaliar = em.getReference(Avaliar.class, id);
                avaliar.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliar with id " + id + " no longer exists.", enfe);
            }
            Alternativa idAlternativa = avaliar.getIdAlternativa();
            if (idAlternativa != null) {
                idAlternativa.getAvaliarList().remove(avaliar);
                idAlternativa = em.merge(idAlternativa);
            }
            Criterio idCriterio = avaliar.getIdCriterio();
            if (idCriterio != null) {
                idCriterio.getAvaliarList().remove(avaliar);
                idCriterio = em.merge(idCriterio);
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

    public Avaliar findAvaliar(Integer id) {
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
