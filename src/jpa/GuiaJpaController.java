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
import model.Organizacao;
import model.Itemguia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import model.Guia;

/**
 *
 * @author Sandro Bezerra
 */
public class GuiaJpaController implements Serializable {

    public GuiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Guia guia) throws PreexistingEntityException, Exception {
        if (guia.getItemguiaList() == null) {
            guia.setItemguiaList(new ArrayList<Itemguia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao idOrganizacao = guia.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao = em.getReference(idOrganizacao.getClass(), idOrganizacao.getId());
                guia.setIdOrganizacao(idOrganizacao);
            }
            List<Itemguia> attachedItemguiaList = new ArrayList<Itemguia>();
            for (Itemguia itemguiaListItemguiaToAttach : guia.getItemguiaList()) {
                itemguiaListItemguiaToAttach = em.getReference(itemguiaListItemguiaToAttach.getClass(), itemguiaListItemguiaToAttach.getId());
                attachedItemguiaList.add(itemguiaListItemguiaToAttach);
            }
            guia.setItemguiaList(attachedItemguiaList);
            em.persist(guia);
            if (idOrganizacao != null) {
                idOrganizacao.getGuiaList().add(guia);
                idOrganizacao = em.merge(idOrganizacao);
            }
            for (Itemguia itemguiaListItemguia : guia.getItemguiaList()) {
                Guia oldIdGuiaOfItemguiaListItemguia = itemguiaListItemguia.getIdGuia();
                itemguiaListItemguia.setIdGuia(guia);
                itemguiaListItemguia = em.merge(itemguiaListItemguia);
                if (oldIdGuiaOfItemguiaListItemguia != null) {
                    oldIdGuiaOfItemguiaListItemguia.getItemguiaList().remove(itemguiaListItemguia);
                    oldIdGuiaOfItemguiaListItemguia = em.merge(oldIdGuiaOfItemguiaListItemguia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGuia(guia.getId()) != null) {
                throw new PreexistingEntityException("Guia " + guia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Guia guia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Guia persistentGuia = em.find(Guia.class, guia.getId());
            Organizacao idOrganizacaoOld = persistentGuia.getIdOrganizacao();
            Organizacao idOrganizacaoNew = guia.getIdOrganizacao();
            List<Itemguia> itemguiaListOld = persistentGuia.getItemguiaList();
            List<Itemguia> itemguiaListNew = guia.getItemguiaList();
            List<String> illegalOrphanMessages = null;
            for (Itemguia itemguiaListOldItemguia : itemguiaListOld) {
                if (!itemguiaListNew.contains(itemguiaListOldItemguia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itemguia " + itemguiaListOldItemguia + " since its idGuia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idOrganizacaoNew != null) {
                idOrganizacaoNew = em.getReference(idOrganizacaoNew.getClass(), idOrganizacaoNew.getId());
                guia.setIdOrganizacao(idOrganizacaoNew);
            }
            List<Itemguia> attachedItemguiaListNew = new ArrayList<Itemguia>();
            for (Itemguia itemguiaListNewItemguiaToAttach : itemguiaListNew) {
                itemguiaListNewItemguiaToAttach = em.getReference(itemguiaListNewItemguiaToAttach.getClass(), itemguiaListNewItemguiaToAttach.getId());
                attachedItemguiaListNew.add(itemguiaListNewItemguiaToAttach);
            }
            itemguiaListNew = attachedItemguiaListNew;
            guia.setItemguiaList(itemguiaListNew);
            guia = em.merge(guia);
            if (idOrganizacaoOld != null && !idOrganizacaoOld.equals(idOrganizacaoNew)) {
                idOrganizacaoOld.getGuiaList().remove(guia);
                idOrganizacaoOld = em.merge(idOrganizacaoOld);
            }
            if (idOrganizacaoNew != null && !idOrganizacaoNew.equals(idOrganizacaoOld)) {
                idOrganizacaoNew.getGuiaList().add(guia);
                idOrganizacaoNew = em.merge(idOrganizacaoNew);
            }
            for (Itemguia itemguiaListNewItemguia : itemguiaListNew) {
                if (!itemguiaListOld.contains(itemguiaListNewItemguia)) {
                    Guia oldIdGuiaOfItemguiaListNewItemguia = itemguiaListNewItemguia.getIdGuia();
                    itemguiaListNewItemguia.setIdGuia(guia);
                    itemguiaListNewItemguia = em.merge(itemguiaListNewItemguia);
                    if (oldIdGuiaOfItemguiaListNewItemguia != null && !oldIdGuiaOfItemguiaListNewItemguia.equals(guia)) {
                        oldIdGuiaOfItemguiaListNewItemguia.getItemguiaList().remove(itemguiaListNewItemguia);
                        oldIdGuiaOfItemguiaListNewItemguia = em.merge(oldIdGuiaOfItemguiaListNewItemguia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = guia.getId();
                if (findGuia(id) == null) {
                    throw new NonexistentEntityException("The guia with id " + id + " no longer exists.");
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
            Guia guia;
            try {
                guia = em.getReference(Guia.class, id);
                guia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The guia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Itemguia> itemguiaListOrphanCheck = guia.getItemguiaList();
            for (Itemguia itemguiaListOrphanCheckItemguia : itemguiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Guia (" + guia + ") cannot be destroyed since the Itemguia " + itemguiaListOrphanCheckItemguia + " in its itemguiaList field has a non-nullable idGuia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Organizacao idOrganizacao = guia.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao.getGuiaList().remove(guia);
                idOrganizacao = em.merge(idOrganizacao);
            }
            em.remove(guia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Guia> findGuiaEntities() {
        return findGuiaEntities(true, -1, -1);
    }

    public List<Guia> findGuiaEntities(int maxResults, int firstResult) {
        return findGuiaEntities(false, maxResults, firstResult);
    }

    private List<Guia> findGuiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Guia.class));
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

    public Guia findGuia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Guia.class, id);
        } finally {
            em.close();
        }
    }

    public int getGuiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Guia> rt = cq.from(Guia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
