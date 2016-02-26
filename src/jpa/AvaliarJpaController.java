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
import model.Alternativa;
import model.Avaliacao;
import model.Avaliar;
import model.AvaliarPK;
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

    public void create(Avaliar avaliar) throws PreexistingEntityException, Exception {
        if (avaliar.getAvaliarPK() == null) {
            avaliar.setAvaliarPK(new AvaliarPK());
        }
        avaliar.getAvaliarPK().setIdAlternativa(avaliar.getAlternativa().getId());
        avaliar.getAvaliarPK().setIdAvaliacao(avaliar.getAvaliacao().getId());
        avaliar.getAvaliarPK().setIdCriterio(avaliar.getCriterio().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alternativa alternativa = avaliar.getAlternativa();
            if (alternativa != null) {
                alternativa = em.getReference(alternativa.getClass(), alternativa.getId());
                avaliar.setAlternativa(alternativa);
            }
            Avaliacao avaliacao = avaliar.getAvaliacao();
            if (avaliacao != null) {
                avaliacao = em.getReference(avaliacao.getClass(), avaliacao.getId());
                avaliar.setAvaliacao(avaliacao);
            }
            Criterio criterio = avaliar.getCriterio();
            if (criterio != null) {
                criterio = em.getReference(criterio.getClass(), criterio.getId());
                avaliar.setCriterio(criterio);
            }
            em.persist(avaliar);
            if (alternativa != null) {
                alternativa.getAvaliarList().add(avaliar);
                alternativa = em.merge(alternativa);
            }
            if (avaliacao != null) {
                avaliacao.getAvaliarList().add(avaliar);
                avaliacao = em.merge(avaliacao);
            }
            if (criterio != null) {
                criterio.getAvaliarList().add(avaliar);
                criterio = em.merge(criterio);
            }
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
        avaliar.getAvaliarPK().setIdAlternativa(avaliar.getAlternativa().getId());
        avaliar.getAvaliarPK().setIdAvaliacao(avaliar.getAvaliacao().getId());
        avaliar.getAvaliarPK().setIdCriterio(avaliar.getCriterio().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliar persistentAvaliar = em.find(Avaliar.class, avaliar.getAvaliarPK());
            Alternativa alternativaOld = persistentAvaliar.getAlternativa();
            Alternativa alternativaNew = avaliar.getAlternativa();
            Avaliacao avaliacaoOld = persistentAvaliar.getAvaliacao();
            Avaliacao avaliacaoNew = avaliar.getAvaliacao();
            Criterio criterioOld = persistentAvaliar.getCriterio();
            Criterio criterioNew = avaliar.getCriterio();
            if (alternativaNew != null) {
                alternativaNew = em.getReference(alternativaNew.getClass(), alternativaNew.getId());
                avaliar.setAlternativa(alternativaNew);
            }
            if (avaliacaoNew != null) {
                avaliacaoNew = em.getReference(avaliacaoNew.getClass(), avaliacaoNew.getId());
                avaliar.setAvaliacao(avaliacaoNew);
            }
            if (criterioNew != null) {
                criterioNew = em.getReference(criterioNew.getClass(), criterioNew.getId());
                avaliar.setCriterio(criterioNew);
            }
            avaliar = em.merge(avaliar);
            if (alternativaOld != null && !alternativaOld.equals(alternativaNew)) {
                alternativaOld.getAvaliarList().remove(avaliar);
                alternativaOld = em.merge(alternativaOld);
            }
            if (alternativaNew != null && !alternativaNew.equals(alternativaOld)) {
                alternativaNew.getAvaliarList().add(avaliar);
                alternativaNew = em.merge(alternativaNew);
            }
            if (avaliacaoOld != null && !avaliacaoOld.equals(avaliacaoNew)) {
                avaliacaoOld.getAvaliarList().remove(avaliar);
                avaliacaoOld = em.merge(avaliacaoOld);
            }
            if (avaliacaoNew != null && !avaliacaoNew.equals(avaliacaoOld)) {
                avaliacaoNew.getAvaliarList().add(avaliar);
                avaliacaoNew = em.merge(avaliacaoNew);
            }
            if (criterioOld != null && !criterioOld.equals(criterioNew)) {
                criterioOld.getAvaliarList().remove(avaliar);
                criterioOld = em.merge(criterioOld);
            }
            if (criterioNew != null && !criterioNew.equals(criterioOld)) {
                criterioNew.getAvaliarList().add(avaliar);
                criterioNew = em.merge(criterioNew);
            }
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
            Alternativa alternativa = avaliar.getAlternativa();
            if (alternativa != null) {
                alternativa.getAvaliarList().remove(avaliar);
                alternativa = em.merge(alternativa);
            }
            Avaliacao avaliacao = avaliar.getAvaliacao();
            if (avaliacao != null) {
                avaliacao.getAvaliarList().remove(avaliar);
                avaliacao = em.merge(avaliacao);
            }
            Criterio criterio = avaliar.getCriterio();
            if (criterio != null) {
                criterio.getAvaliarList().remove(avaliar);
                criterio = em.merge(criterio);
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
