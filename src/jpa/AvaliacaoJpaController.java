/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Avaliar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Avaliacao;

/**
 *
 * @author Sandro Bezerra
 */
public class AvaliacaoJpaController implements Serializable {

    public AvaliacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Avaliacao avaliacao) {
        if (avaliacao.getAvaliarList() == null) {
            avaliacao.setAvaliarList(new ArrayList<Avaliar>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Avaliar> attachedAvaliarList = new ArrayList<Avaliar>();
            for (Avaliar avaliarListAvaliarToAttach : avaliacao.getAvaliarList()) {
                avaliarListAvaliarToAttach = em.getReference(avaliarListAvaliarToAttach.getClass(), avaliarListAvaliarToAttach.getAvaliarPK());
                attachedAvaliarList.add(avaliarListAvaliarToAttach);
            }
            avaliacao.setAvaliarList(attachedAvaliarList);
            em.persist(avaliacao);
            for (Avaliar avaliarListAvaliar : avaliacao.getAvaliarList()) {
                Avaliacao oldAvaliacaoOfAvaliarListAvaliar = avaliarListAvaliar.getAvaliacao();
                avaliarListAvaliar.setAvaliacao(avaliacao);
                avaliarListAvaliar = em.merge(avaliarListAvaliar);
                if (oldAvaliacaoOfAvaliarListAvaliar != null) {
                    oldAvaliacaoOfAvaliarListAvaliar.getAvaliarList().remove(avaliarListAvaliar);
                    oldAvaliacaoOfAvaliarListAvaliar = em.merge(oldAvaliacaoOfAvaliarListAvaliar);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Avaliacao avaliacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliacao persistentAvaliacao = em.find(Avaliacao.class, avaliacao.getId());
            List<Avaliar> avaliarListOld = persistentAvaliacao.getAvaliarList();
            List<Avaliar> avaliarListNew = avaliacao.getAvaliarList();
            List<String> illegalOrphanMessages = null;
            for (Avaliar avaliarListOldAvaliar : avaliarListOld) {
                if (!avaliarListNew.contains(avaliarListOldAvaliar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Avaliar " + avaliarListOldAvaliar + " since its avaliacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Avaliar> attachedAvaliarListNew = new ArrayList<Avaliar>();
            for (Avaliar avaliarListNewAvaliarToAttach : avaliarListNew) {
                avaliarListNewAvaliarToAttach = em.getReference(avaliarListNewAvaliarToAttach.getClass(), avaliarListNewAvaliarToAttach.getAvaliarPK());
                attachedAvaliarListNew.add(avaliarListNewAvaliarToAttach);
            }
            avaliarListNew = attachedAvaliarListNew;
            avaliacao.setAvaliarList(avaliarListNew);
            avaliacao = em.merge(avaliacao);
            for (Avaliar avaliarListNewAvaliar : avaliarListNew) {
                if (!avaliarListOld.contains(avaliarListNewAvaliar)) {
                    Avaliacao oldAvaliacaoOfAvaliarListNewAvaliar = avaliarListNewAvaliar.getAvaliacao();
                    avaliarListNewAvaliar.setAvaliacao(avaliacao);
                    avaliarListNewAvaliar = em.merge(avaliarListNewAvaliar);
                    if (oldAvaliacaoOfAvaliarListNewAvaliar != null && !oldAvaliacaoOfAvaliarListNewAvaliar.equals(avaliacao)) {
                        oldAvaliacaoOfAvaliarListNewAvaliar.getAvaliarList().remove(avaliarListNewAvaliar);
                        oldAvaliacaoOfAvaliarListNewAvaliar = em.merge(oldAvaliacaoOfAvaliarListNewAvaliar);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = avaliacao.getId();
                if (findAvaliacao(id) == null) {
                    throw new NonexistentEntityException("The avaliacao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliacao avaliacao;
            try {
                avaliacao = em.getReference(Avaliacao.class, id);
                avaliacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliacao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Avaliar> avaliarListOrphanCheck = avaliacao.getAvaliarList();
            for (Avaliar avaliarListOrphanCheckAvaliar : avaliarListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Avaliacao (" + avaliacao + ") cannot be destroyed since the Avaliar " + avaliarListOrphanCheckAvaliar + " in its avaliarList field has a non-nullable avaliacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(avaliacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Avaliacao> findAvaliacaoEntities() {
        return findAvaliacaoEntities(true, -1, -1);
    }

    public List<Avaliacao> findAvaliacaoEntities(int maxResults, int firstResult) {
        return findAvaliacaoEntities(false, maxResults, firstResult);
    }

    private List<Avaliacao> findAvaliacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Avaliacao.class));
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

    public Avaliacao findAvaliacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Avaliacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvaliacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Avaliacao> rt = cq.from(Avaliacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
