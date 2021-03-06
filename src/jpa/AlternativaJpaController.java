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
import model.Problema;
import model.Decisao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Alternativa;
import model.Avaliar;

/**
 *
 * @author Sandro Bezerra
 */
public class AlternativaJpaController implements Serializable {

    public AlternativaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alternativa alternativa) {
        if (alternativa.getDecisaoList() == null) {
            alternativa.setDecisaoList(new ArrayList<Decisao>());
        }
        if (alternativa.getAvaliarList() == null) {
            alternativa.setAvaliarList(new ArrayList<Avaliar>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Problema idProblema = alternativa.getIdProblema();
            if (idProblema != null) {
                idProblema = em.getReference(idProblema.getClass(), idProblema.getId());
                alternativa.setIdProblema(idProblema);
            }
            List<Decisao> attachedDecisaoList = new ArrayList<Decisao>();
            for (Decisao decisaoListDecisaoToAttach : alternativa.getDecisaoList()) {
                decisaoListDecisaoToAttach = em.getReference(decisaoListDecisaoToAttach.getClass(), decisaoListDecisaoToAttach.getId());
                attachedDecisaoList.add(decisaoListDecisaoToAttach);
            }
            alternativa.setDecisaoList(attachedDecisaoList);
            List<Avaliar> attachedAvaliarList = new ArrayList<Avaliar>();
            for (Avaliar avaliarListAvaliarToAttach : alternativa.getAvaliarList()) {
                avaliarListAvaliarToAttach = em.getReference(avaliarListAvaliarToAttach.getClass(), avaliarListAvaliarToAttach.getId());
                attachedAvaliarList.add(avaliarListAvaliarToAttach);
            }
            alternativa.setAvaliarList(attachedAvaliarList);
            em.persist(alternativa);
            if (idProblema != null) {
                idProblema.getAlternativaList().add(alternativa);
                idProblema = em.merge(idProblema);
            }
            for (Decisao decisaoListDecisao : alternativa.getDecisaoList()) {
                Alternativa oldIdAlternativaOfDecisaoListDecisao = decisaoListDecisao.getIdAlternativa();
                decisaoListDecisao.setIdAlternativa(alternativa);
                decisaoListDecisao = em.merge(decisaoListDecisao);
                if (oldIdAlternativaOfDecisaoListDecisao != null) {
                    oldIdAlternativaOfDecisaoListDecisao.getDecisaoList().remove(decisaoListDecisao);
                    oldIdAlternativaOfDecisaoListDecisao = em.merge(oldIdAlternativaOfDecisaoListDecisao);
                }
            }
            for (Avaliar avaliarListAvaliar : alternativa.getAvaliarList()) {
                Alternativa oldIdAlternativaOfAvaliarListAvaliar = avaliarListAvaliar.getIdAlternativa();
                avaliarListAvaliar.setIdAlternativa(alternativa);
                avaliarListAvaliar = em.merge(avaliarListAvaliar);
                if (oldIdAlternativaOfAvaliarListAvaliar != null) {
                    oldIdAlternativaOfAvaliarListAvaliar.getAvaliarList().remove(avaliarListAvaliar);
                    oldIdAlternativaOfAvaliarListAvaliar = em.merge(oldIdAlternativaOfAvaliarListAvaliar);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alternativa alternativa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alternativa persistentAlternativa = em.find(Alternativa.class, alternativa.getId());
            Problema idProblemaOld = persistentAlternativa.getIdProblema();
            Problema idProblemaNew = alternativa.getIdProblema();
            List<Decisao> decisaoListOld = persistentAlternativa.getDecisaoList();
            List<Decisao> decisaoListNew = alternativa.getDecisaoList();
            List<Avaliar> avaliarListOld = persistentAlternativa.getAvaliarList();
            List<Avaliar> avaliarListNew = alternativa.getAvaliarList();
            List<String> illegalOrphanMessages = null;
            for (Decisao decisaoListOldDecisao : decisaoListOld) {
                if (!decisaoListNew.contains(decisaoListOldDecisao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Decisao " + decisaoListOldDecisao + " since its idAlternativa field is not nullable.");
                }
            }
            for (Avaliar avaliarListOldAvaliar : avaliarListOld) {
                if (!avaliarListNew.contains(avaliarListOldAvaliar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Avaliar " + avaliarListOldAvaliar + " since its idAlternativa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idProblemaNew != null) {
                idProblemaNew = em.getReference(idProblemaNew.getClass(), idProblemaNew.getId());
                alternativa.setIdProblema(idProblemaNew);
            }
            List<Decisao> attachedDecisaoListNew = new ArrayList<Decisao>();
            for (Decisao decisaoListNewDecisaoToAttach : decisaoListNew) {
                decisaoListNewDecisaoToAttach = em.getReference(decisaoListNewDecisaoToAttach.getClass(), decisaoListNewDecisaoToAttach.getId());
                attachedDecisaoListNew.add(decisaoListNewDecisaoToAttach);
            }
            decisaoListNew = attachedDecisaoListNew;
            alternativa.setDecisaoList(decisaoListNew);
            List<Avaliar> attachedAvaliarListNew = new ArrayList<Avaliar>();
            for (Avaliar avaliarListNewAvaliarToAttach : avaliarListNew) {
                avaliarListNewAvaliarToAttach = em.getReference(avaliarListNewAvaliarToAttach.getClass(), avaliarListNewAvaliarToAttach.getId());
                attachedAvaliarListNew.add(avaliarListNewAvaliarToAttach);
            }
            avaliarListNew = attachedAvaliarListNew;
            alternativa.setAvaliarList(avaliarListNew);
            alternativa = em.merge(alternativa);
            if (idProblemaOld != null && !idProblemaOld.equals(idProblemaNew)) {
                idProblemaOld.getAlternativaList().remove(alternativa);
                idProblemaOld = em.merge(idProblemaOld);
            }
            if (idProblemaNew != null && !idProblemaNew.equals(idProblemaOld)) {
                idProblemaNew.getAlternativaList().add(alternativa);
                idProblemaNew = em.merge(idProblemaNew);
            }
            for (Decisao decisaoListNewDecisao : decisaoListNew) {
                if (!decisaoListOld.contains(decisaoListNewDecisao)) {
                    Alternativa oldIdAlternativaOfDecisaoListNewDecisao = decisaoListNewDecisao.getIdAlternativa();
                    decisaoListNewDecisao.setIdAlternativa(alternativa);
                    decisaoListNewDecisao = em.merge(decisaoListNewDecisao);
                    if (oldIdAlternativaOfDecisaoListNewDecisao != null && !oldIdAlternativaOfDecisaoListNewDecisao.equals(alternativa)) {
                        oldIdAlternativaOfDecisaoListNewDecisao.getDecisaoList().remove(decisaoListNewDecisao);
                        oldIdAlternativaOfDecisaoListNewDecisao = em.merge(oldIdAlternativaOfDecisaoListNewDecisao);
                    }
                }
            }
            for (Avaliar avaliarListNewAvaliar : avaliarListNew) {
                if (!avaliarListOld.contains(avaliarListNewAvaliar)) {
                    Alternativa oldIdAlternativaOfAvaliarListNewAvaliar = avaliarListNewAvaliar.getIdAlternativa();
                    avaliarListNewAvaliar.setIdAlternativa(alternativa);
                    avaliarListNewAvaliar = em.merge(avaliarListNewAvaliar);
                    if (oldIdAlternativaOfAvaliarListNewAvaliar != null && !oldIdAlternativaOfAvaliarListNewAvaliar.equals(alternativa)) {
                        oldIdAlternativaOfAvaliarListNewAvaliar.getAvaliarList().remove(avaliarListNewAvaliar);
                        oldIdAlternativaOfAvaliarListNewAvaliar = em.merge(oldIdAlternativaOfAvaliarListNewAvaliar);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alternativa.getId();
                if (findAlternativa(id) == null) {
                    throw new NonexistentEntityException("The alternativa with id " + id + " no longer exists.");
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
            Alternativa alternativa;
            try {
                alternativa = em.getReference(Alternativa.class, id);
                alternativa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alternativa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Decisao> decisaoListOrphanCheck = alternativa.getDecisaoList();
            for (Decisao decisaoListOrphanCheckDecisao : decisaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alternativa (" + alternativa + ") cannot be destroyed since the Decisao " + decisaoListOrphanCheckDecisao + " in its decisaoList field has a non-nullable idAlternativa field.");
            }
            List<Avaliar> avaliarListOrphanCheck = alternativa.getAvaliarList();
            for (Avaliar avaliarListOrphanCheckAvaliar : avaliarListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alternativa (" + alternativa + ") cannot be destroyed since the Avaliar " + avaliarListOrphanCheckAvaliar + " in its avaliarList field has a non-nullable idAlternativa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Problema idProblema = alternativa.getIdProblema();
            if (idProblema != null) {
                idProblema.getAlternativaList().remove(alternativa);
                idProblema = em.merge(idProblema);
            }
            em.remove(alternativa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alternativa> findAlternativaEntities() {
        return findAlternativaEntities(true, -1, -1);
    }

    public List<Alternativa> findAlternativaEntities(int maxResults, int firstResult) {
        return findAlternativaEntities(false, maxResults, firstResult);
    }

    private List<Alternativa> findAlternativaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alternativa.class));
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

    public Alternativa findAlternativa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alternativa.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlternativaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alternativa> rt = cq.from(Alternativa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
