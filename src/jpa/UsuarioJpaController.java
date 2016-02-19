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
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Acessar;
import model.Usuario;

/**
 *
 * @author Bleno Vale
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getPerfilList() == null) {
            usuario.setPerfilList(new ArrayList<Perfil>());
        }
        if (usuario.getAcessarList() == null) {
            usuario.setAcessarList(new ArrayList<Acessar>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Perfil> attachedPerfilList = new ArrayList<Perfil>();
            for (Perfil perfilListPerfilToAttach : usuario.getPerfilList()) {
                perfilListPerfilToAttach = em.getReference(perfilListPerfilToAttach.getClass(), perfilListPerfilToAttach.getId());
                attachedPerfilList.add(perfilListPerfilToAttach);
            }
            usuario.setPerfilList(attachedPerfilList);
            List<Acessar> attachedAcessarList = new ArrayList<Acessar>();
            for (Acessar acessarListAcessarToAttach : usuario.getAcessarList()) {
                acessarListAcessarToAttach = em.getReference(acessarListAcessarToAttach.getClass(), acessarListAcessarToAttach.getAcessarPK());
                attachedAcessarList.add(acessarListAcessarToAttach);
            }
            usuario.setAcessarList(attachedAcessarList);
            em.persist(usuario);
            for (Perfil perfilListPerfil : usuario.getPerfilList()) {
                perfilListPerfil.getUsuarioList().add(usuario);
                perfilListPerfil = em.merge(perfilListPerfil);
            }
            for (Acessar acessarListAcessar : usuario.getAcessarList()) {
                Usuario oldUsuarioOfAcessarListAcessar = acessarListAcessar.getUsuario();
                acessarListAcessar.setUsuario(usuario);
                acessarListAcessar = em.merge(acessarListAcessar);
                if (oldUsuarioOfAcessarListAcessar != null) {
                    oldUsuarioOfAcessarListAcessar.getAcessarList().remove(acessarListAcessar);
                    oldUsuarioOfAcessarListAcessar = em.merge(oldUsuarioOfAcessarListAcessar);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Perfil> perfilListOld = persistentUsuario.getPerfilList();
            List<Perfil> perfilListNew = usuario.getPerfilList();
            List<Acessar> acessarListOld = persistentUsuario.getAcessarList();
            List<Acessar> acessarListNew = usuario.getAcessarList();
            List<String> illegalOrphanMessages = null;
            for (Acessar acessarListOldAcessar : acessarListOld) {
                if (!acessarListNew.contains(acessarListOldAcessar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Acessar " + acessarListOldAcessar + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Perfil> attachedPerfilListNew = new ArrayList<Perfil>();
            for (Perfil perfilListNewPerfilToAttach : perfilListNew) {
                perfilListNewPerfilToAttach = em.getReference(perfilListNewPerfilToAttach.getClass(), perfilListNewPerfilToAttach.getId());
                attachedPerfilListNew.add(perfilListNewPerfilToAttach);
            }
            perfilListNew = attachedPerfilListNew;
            usuario.setPerfilList(perfilListNew);
            List<Acessar> attachedAcessarListNew = new ArrayList<Acessar>();
            for (Acessar acessarListNewAcessarToAttach : acessarListNew) {
                acessarListNewAcessarToAttach = em.getReference(acessarListNewAcessarToAttach.getClass(), acessarListNewAcessarToAttach.getAcessarPK());
                attachedAcessarListNew.add(acessarListNewAcessarToAttach);
            }
            acessarListNew = attachedAcessarListNew;
            usuario.setAcessarList(acessarListNew);
            usuario = em.merge(usuario);
            for (Perfil perfilListOldPerfil : perfilListOld) {
                if (!perfilListNew.contains(perfilListOldPerfil)) {
                    perfilListOldPerfil.getUsuarioList().remove(usuario);
                    perfilListOldPerfil = em.merge(perfilListOldPerfil);
                }
            }
            for (Perfil perfilListNewPerfil : perfilListNew) {
                if (!perfilListOld.contains(perfilListNewPerfil)) {
                    perfilListNewPerfil.getUsuarioList().add(usuario);
                    perfilListNewPerfil = em.merge(perfilListNewPerfil);
                }
            }
            for (Acessar acessarListNewAcessar : acessarListNew) {
                if (!acessarListOld.contains(acessarListNewAcessar)) {
                    Usuario oldUsuarioOfAcessarListNewAcessar = acessarListNewAcessar.getUsuario();
                    acessarListNewAcessar.setUsuario(usuario);
                    acessarListNewAcessar = em.merge(acessarListNewAcessar);
                    if (oldUsuarioOfAcessarListNewAcessar != null && !oldUsuarioOfAcessarListNewAcessar.equals(usuario)) {
                        oldUsuarioOfAcessarListNewAcessar.getAcessarList().remove(acessarListNewAcessar);
                        oldUsuarioOfAcessarListNewAcessar = em.merge(oldUsuarioOfAcessarListNewAcessar);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Acessar> acessarListOrphanCheck = usuario.getAcessarList();
            for (Acessar acessarListOrphanCheckAcessar : acessarListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Acessar " + acessarListOrphanCheckAcessar + " in its acessarList field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Perfil> perfilList = usuario.getPerfilList();
            for (Perfil perfilListPerfil : perfilList) {
                perfilListPerfil.getUsuarioList().remove(usuario);
                perfilListPerfil = em.merge(perfilListPerfil);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
