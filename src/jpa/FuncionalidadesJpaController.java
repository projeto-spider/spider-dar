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
import model.Perfil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;
import model.Funcionalidades;

/**
 *
 * @author Sandro Bezerra
 */
public class FuncionalidadesJpaController implements Serializable {

    public FuncionalidadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionalidades funcionalidades) {
        if (funcionalidades.getPerfilList() == null) {
            funcionalidades.setPerfilList(new ArrayList<Perfil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Perfil> attachedPerfilList = new ArrayList<Perfil>();
            for (Perfil perfilListPerfilToAttach : funcionalidades.getPerfilList()) {
                perfilListPerfilToAttach = em.getReference(perfilListPerfilToAttach.getClass(), perfilListPerfilToAttach.getId());
                attachedPerfilList.add(perfilListPerfilToAttach);
            }
            funcionalidades.setPerfilList(attachedPerfilList);
            em.persist(funcionalidades);
            for (Perfil perfilListPerfil : funcionalidades.getPerfilList()) {
                perfilListPerfil.getFuncionalidadesList().add(funcionalidades);
                perfilListPerfil = em.merge(perfilListPerfil);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionalidades funcionalidades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionalidades persistentFuncionalidades = em.find(Funcionalidades.class, funcionalidades.getId());
            List<Perfil> perfilListOld = persistentFuncionalidades.getPerfilList();
            List<Perfil> perfilListNew = funcionalidades.getPerfilList();
            List<Perfil> attachedPerfilListNew = new ArrayList<Perfil>();
            for (Perfil perfilListNewPerfilToAttach : perfilListNew) {
                perfilListNewPerfilToAttach = em.getReference(perfilListNewPerfilToAttach.getClass(), perfilListNewPerfilToAttach.getId());
                attachedPerfilListNew.add(perfilListNewPerfilToAttach);
            }
            perfilListNew = attachedPerfilListNew;
            funcionalidades.setPerfilList(perfilListNew);
            funcionalidades = em.merge(funcionalidades);
            for (Perfil perfilListOldPerfil : perfilListOld) {
                if (!perfilListNew.contains(perfilListOldPerfil)) {
                    perfilListOldPerfil.getFuncionalidadesList().remove(funcionalidades);
                    perfilListOldPerfil = em.merge(perfilListOldPerfil);
                }
            }
            for (Perfil perfilListNewPerfil : perfilListNew) {
                if (!perfilListOld.contains(perfilListNewPerfil)) {
                    perfilListNewPerfil.getFuncionalidadesList().add(funcionalidades);
                    perfilListNewPerfil = em.merge(perfilListNewPerfil);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionalidades.getId();
                if (findFuncionalidades(id) == null) {
                    throw new NonexistentEntityException("The funcionalidades with id " + id + " no longer exists.");
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
            Funcionalidades funcionalidades;
            try {
                funcionalidades = em.getReference(Funcionalidades.class, id);
                funcionalidades.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionalidades with id " + id + " no longer exists.", enfe);
            }
            List<Perfil> perfilList = funcionalidades.getPerfilList();
            for (Perfil perfilListPerfil : perfilList) {
                perfilListPerfil.getFuncionalidadesList().remove(funcionalidades);
                perfilListPerfil = em.merge(perfilListPerfil);
            }
            em.remove(funcionalidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionalidades> findFuncionalidadesEntities() {
        return findFuncionalidadesEntities(true, -1, -1);
    }

    public List<Funcionalidades> findFuncionalidadesEntities(int maxResults, int firstResult) {
        return findFuncionalidadesEntities(false, maxResults, firstResult);
    }

    private List<Funcionalidades> findFuncionalidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionalidades.class));
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

    public Funcionalidades findFuncionalidades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionalidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionalidadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionalidades> rt = cq.from(Funcionalidades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
