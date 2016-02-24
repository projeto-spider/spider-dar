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
import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Funcionalidades;
import model.Acessar;
import model.Perfil;

/**
 *
 * @author Spider
 */
public class PerfilJpaController implements Serializable {

    public PerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfil perfil) {
        if (perfil.getUsuarioList() == null) {
            perfil.setUsuarioList(new ArrayList<Usuario>());
        }
        if (perfil.getFuncionalidadesList() == null) {
            perfil.setFuncionalidadesList(new ArrayList<Funcionalidades>());
        }
        if (perfil.getAcessarList() == null) {
            perfil.setAcessarList(new ArrayList<Acessar>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao idOrganizacao = perfil.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao = em.getReference(idOrganizacao.getClass(), idOrganizacao.getId());
                perfil.setIdOrganizacao(idOrganizacao);
            }
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : perfil.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getId());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            perfil.setUsuarioList(attachedUsuarioList);
            List<Funcionalidades> attachedFuncionalidadesList = new ArrayList<Funcionalidades>();
            for (Funcionalidades funcionalidadesListFuncionalidadesToAttach : perfil.getFuncionalidadesList()) {
                funcionalidadesListFuncionalidadesToAttach = em.getReference(funcionalidadesListFuncionalidadesToAttach.getClass(), funcionalidadesListFuncionalidadesToAttach.getId());
                attachedFuncionalidadesList.add(funcionalidadesListFuncionalidadesToAttach);
            }
            perfil.setFuncionalidadesList(attachedFuncionalidadesList);
            List<Acessar> attachedAcessarList = new ArrayList<Acessar>();
            for (Acessar acessarListAcessarToAttach : perfil.getAcessarList()) {
                acessarListAcessarToAttach = em.getReference(acessarListAcessarToAttach.getClass(), acessarListAcessarToAttach.getAcessarPK());
                attachedAcessarList.add(acessarListAcessarToAttach);
            }
            perfil.setAcessarList(attachedAcessarList);
            em.persist(perfil);
            if (idOrganizacao != null) {
                idOrganizacao.getPerfilList().add(perfil);
                idOrganizacao = em.merge(idOrganizacao);
            }
            for (Usuario usuarioListUsuario : perfil.getUsuarioList()) {
                usuarioListUsuario.getPerfilList().add(perfil);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            for (Funcionalidades funcionalidadesListFuncionalidades : perfil.getFuncionalidadesList()) {
                funcionalidadesListFuncionalidades.getPerfilList().add(perfil);
                funcionalidadesListFuncionalidades = em.merge(funcionalidadesListFuncionalidades);
            }
            for (Acessar acessarListAcessar : perfil.getAcessarList()) {
                Perfil oldPerfilOfAcessarListAcessar = acessarListAcessar.getPerfil();
                acessarListAcessar.setPerfil(perfil);
                acessarListAcessar = em.merge(acessarListAcessar);
                if (oldPerfilOfAcessarListAcessar != null) {
                    oldPerfilOfAcessarListAcessar.getAcessarList().remove(acessarListAcessar);
                    oldPerfilOfAcessarListAcessar = em.merge(oldPerfilOfAcessarListAcessar);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfil perfil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil persistentPerfil = em.find(Perfil.class, perfil.getId());
            Organizacao idOrganizacaoOld = persistentPerfil.getIdOrganizacao();
            Organizacao idOrganizacaoNew = perfil.getIdOrganizacao();
            List<Usuario> usuarioListOld = persistentPerfil.getUsuarioList();
            List<Usuario> usuarioListNew = perfil.getUsuarioList();
            List<Funcionalidades> funcionalidadesListOld = persistentPerfil.getFuncionalidadesList();
            List<Funcionalidades> funcionalidadesListNew = perfil.getFuncionalidadesList();
            List<Acessar> acessarListOld = persistentPerfil.getAcessarList();
            List<Acessar> acessarListNew = perfil.getAcessarList();
            List<String> illegalOrphanMessages = null;
            for (Acessar acessarListOldAcessar : acessarListOld) {
                if (!acessarListNew.contains(acessarListOldAcessar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Acessar " + acessarListOldAcessar + " since its perfil field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idOrganizacaoNew != null) {
                idOrganizacaoNew = em.getReference(idOrganizacaoNew.getClass(), idOrganizacaoNew.getId());
                perfil.setIdOrganizacao(idOrganizacaoNew);
            }
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getId());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            perfil.setUsuarioList(usuarioListNew);
            List<Funcionalidades> attachedFuncionalidadesListNew = new ArrayList<Funcionalidades>();
            for (Funcionalidades funcionalidadesListNewFuncionalidadesToAttach : funcionalidadesListNew) {
                funcionalidadesListNewFuncionalidadesToAttach = em.getReference(funcionalidadesListNewFuncionalidadesToAttach.getClass(), funcionalidadesListNewFuncionalidadesToAttach.getId());
                attachedFuncionalidadesListNew.add(funcionalidadesListNewFuncionalidadesToAttach);
            }
            funcionalidadesListNew = attachedFuncionalidadesListNew;
            perfil.setFuncionalidadesList(funcionalidadesListNew);
            List<Acessar> attachedAcessarListNew = new ArrayList<Acessar>();
            for (Acessar acessarListNewAcessarToAttach : acessarListNew) {
                acessarListNewAcessarToAttach = em.getReference(acessarListNewAcessarToAttach.getClass(), acessarListNewAcessarToAttach.getAcessarPK());
                attachedAcessarListNew.add(acessarListNewAcessarToAttach);
            }
            acessarListNew = attachedAcessarListNew;
            perfil.setAcessarList(acessarListNew);
            perfil = em.merge(perfil);
            if (idOrganizacaoOld != null && !idOrganizacaoOld.equals(idOrganizacaoNew)) {
                idOrganizacaoOld.getPerfilList().remove(perfil);
                idOrganizacaoOld = em.merge(idOrganizacaoOld);
            }
            if (idOrganizacaoNew != null && !idOrganizacaoNew.equals(idOrganizacaoOld)) {
                idOrganizacaoNew.getPerfilList().add(perfil);
                idOrganizacaoNew = em.merge(idOrganizacaoNew);
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.getPerfilList().remove(perfil);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    usuarioListNewUsuario.getPerfilList().add(perfil);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                }
            }
            for (Funcionalidades funcionalidadesListOldFuncionalidades : funcionalidadesListOld) {
                if (!funcionalidadesListNew.contains(funcionalidadesListOldFuncionalidades)) {
                    funcionalidadesListOldFuncionalidades.getPerfilList().remove(perfil);
                    funcionalidadesListOldFuncionalidades = em.merge(funcionalidadesListOldFuncionalidades);
                }
            }
            for (Funcionalidades funcionalidadesListNewFuncionalidades : funcionalidadesListNew) {
                if (!funcionalidadesListOld.contains(funcionalidadesListNewFuncionalidades)) {
                    funcionalidadesListNewFuncionalidades.getPerfilList().add(perfil);
                    funcionalidadesListNewFuncionalidades = em.merge(funcionalidadesListNewFuncionalidades);
                }
            }
            for (Acessar acessarListNewAcessar : acessarListNew) {
                if (!acessarListOld.contains(acessarListNewAcessar)) {
                    Perfil oldPerfilOfAcessarListNewAcessar = acessarListNewAcessar.getPerfil();
                    acessarListNewAcessar.setPerfil(perfil);
                    acessarListNewAcessar = em.merge(acessarListNewAcessar);
                    if (oldPerfilOfAcessarListNewAcessar != null && !oldPerfilOfAcessarListNewAcessar.equals(perfil)) {
                        oldPerfilOfAcessarListNewAcessar.getAcessarList().remove(acessarListNewAcessar);
                        oldPerfilOfAcessarListNewAcessar = em.merge(oldPerfilOfAcessarListNewAcessar);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfil.getId();
                if (findPerfil(id) == null) {
                    throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
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
            Perfil perfil;
            try {
                perfil = em.getReference(Perfil.class, id);
                perfil.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Acessar> acessarListOrphanCheck = perfil.getAcessarList();
            for (Acessar acessarListOrphanCheckAcessar : acessarListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perfil (" + perfil + ") cannot be destroyed since the Acessar " + acessarListOrphanCheckAcessar + " in its acessarList field has a non-nullable perfil field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Organizacao idOrganizacao = perfil.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao.getPerfilList().remove(perfil);
                idOrganizacao = em.merge(idOrganizacao);
            }
            List<Usuario> usuarioList = perfil.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.getPerfilList().remove(perfil);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            List<Funcionalidades> funcionalidadesList = perfil.getFuncionalidadesList();
            for (Funcionalidades funcionalidadesListFuncionalidades : funcionalidadesList) {
                funcionalidadesListFuncionalidades.getPerfilList().remove(perfil);
                funcionalidadesListFuncionalidades = em.merge(funcionalidadesListFuncionalidades);
            }
            em.remove(perfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfil> findPerfilEntities() {
        return findPerfilEntities(true, -1, -1);
    }

    public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
        return findPerfilEntities(false, maxResults, firstResult);
    }

    private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfil.class));
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

    public Perfil findPerfil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfil> rt = cq.from(Perfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
