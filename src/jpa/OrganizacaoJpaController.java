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
import model.Guia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Organizacao;

/**
 *
 * @author Sandro Bezerra
 */
public class OrganizacaoJpaController implements Serializable {

    public OrganizacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Organizacao organizacao) {
        if (organizacao.getGuiaList() == null) {
            organizacao.setGuiaList(new ArrayList<Guia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Guia> attachedGuiaList = new ArrayList<Guia>();
            for (Guia guiaListGuiaToAttach : organizacao.getGuiaList()) {
                guiaListGuiaToAttach = em.getReference(guiaListGuiaToAttach.getClass(), guiaListGuiaToAttach.getId());
                attachedGuiaList.add(guiaListGuiaToAttach);
            }
            organizacao.setGuiaList(attachedGuiaList);
            em.persist(organizacao);
            for (Guia guiaListGuia : organizacao.getGuiaList()) {
                Organizacao oldIdOrganizacaoOfGuiaListGuia = guiaListGuia.getIdOrganizacao();
                guiaListGuia.setIdOrganizacao(organizacao);
                guiaListGuia = em.merge(guiaListGuia);
                if (oldIdOrganizacaoOfGuiaListGuia != null) {
                    oldIdOrganizacaoOfGuiaListGuia.getGuiaList().remove(guiaListGuia);
                    oldIdOrganizacaoOfGuiaListGuia = em.merge(oldIdOrganizacaoOfGuiaListGuia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Organizacao organizacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao persistentOrganizacao = em.find(Organizacao.class, organizacao.getId());
            List<Guia> guiaListOld = persistentOrganizacao.getGuiaList();
            List<Guia> guiaListNew = organizacao.getGuiaList();
            List<String> illegalOrphanMessages = null;
            for (Guia guiaListOldGuia : guiaListOld) {
                if (!guiaListNew.contains(guiaListOldGuia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Guia " + guiaListOldGuia + " since its idOrganizacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Guia> attachedGuiaListNew = new ArrayList<Guia>();
            for (Guia guiaListNewGuiaToAttach : guiaListNew) {
                guiaListNewGuiaToAttach = em.getReference(guiaListNewGuiaToAttach.getClass(), guiaListNewGuiaToAttach.getId());
                attachedGuiaListNew.add(guiaListNewGuiaToAttach);
            }
            guiaListNew = attachedGuiaListNew;
            organizacao.setGuiaList(guiaListNew);
            organizacao = em.merge(organizacao);
            for (Guia guiaListNewGuia : guiaListNew) {
                if (!guiaListOld.contains(guiaListNewGuia)) {
                    Organizacao oldIdOrganizacaoOfGuiaListNewGuia = guiaListNewGuia.getIdOrganizacao();
                    guiaListNewGuia.setIdOrganizacao(organizacao);
                    guiaListNewGuia = em.merge(guiaListNewGuia);
                    if (oldIdOrganizacaoOfGuiaListNewGuia != null && !oldIdOrganizacaoOfGuiaListNewGuia.equals(organizacao)) {
                        oldIdOrganizacaoOfGuiaListNewGuia.getGuiaList().remove(guiaListNewGuia);
                        oldIdOrganizacaoOfGuiaListNewGuia = em.merge(oldIdOrganizacaoOfGuiaListNewGuia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = organizacao.getId();
                if (findOrganizacao(id) == null) {
                    throw new NonexistentEntityException("The organizacao with id " + id + " no longer exists.");
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
            Organizacao organizacao;
            try {
                organizacao = em.getReference(Organizacao.class, id);
                organizacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The organizacao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Guia> guiaListOrphanCheck = organizacao.getGuiaList();
            for (Guia guiaListOrphanCheckGuia : guiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Organizacao (" + organizacao + ") cannot be destroyed since the Guia " + guiaListOrphanCheckGuia + " in its guiaList field has a non-nullable idOrganizacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(organizacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Organizacao> findOrganizacaoEntities() {
        return findOrganizacaoEntities(true, -1, -1);
    }

    public List<Organizacao> findOrganizacaoEntities(int maxResults, int firstResult) {
        return findOrganizacaoEntities(false, maxResults, firstResult);
    }

    private List<Organizacao> findOrganizacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Organizacao.class));
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

    public Organizacao findOrganizacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Organizacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrganizacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Organizacao> rt = cq.from(Organizacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
