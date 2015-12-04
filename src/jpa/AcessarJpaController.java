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
import model.Acessar;
import model.AcessarPK;
import model.Organizacao;
import model.Perfil;
import model.Problema;
import model.Usuario;

/**
 *
 * @author Spider
 */
public class AcessarJpaController implements Serializable {

    public AcessarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acessar acessar) throws PreexistingEntityException, Exception {
        if (acessar.getAcessarPK() == null) {
            acessar.setAcessarPK(new AcessarPK());
        }
        acessar.getAcessarPK().setIdUsuario(acessar.getUsuario().getId());
        acessar.getAcessarPK().setIdOrganizacao(acessar.getOrganizacao().getId());
        acessar.getAcessarPK().setIdProblema(acessar.getProblema().getId());
        acessar.getAcessarPK().setIdPerfil(acessar.getPerfil().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao organizacao = acessar.getOrganizacao();
            if (organizacao != null) {
                organizacao = em.getReference(organizacao.getClass(), organizacao.getId());
                acessar.setOrganizacao(organizacao);
            }
            Perfil perfil = acessar.getPerfil();
            if (perfil != null) {
                perfil = em.getReference(perfil.getClass(), perfil.getId());
                acessar.setPerfil(perfil);
            }
            Problema problema = acessar.getProblema();
            if (problema != null) {
                problema = em.getReference(problema.getClass(), problema.getId());
                acessar.setProblema(problema);
            }
            Usuario usuario = acessar.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                acessar.setUsuario(usuario);
            }
            em.persist(acessar);
            if (organizacao != null) {
                organizacao.getAcessarList().add(acessar);
                organizacao = em.merge(organizacao);
            }
            if (perfil != null) {
                perfil.getAcessarList().add(acessar);
                perfil = em.merge(perfil);
            }
            if (problema != null) {
                problema.getAcessarList().add(acessar);
                problema = em.merge(problema);
            }
            if (usuario != null) {
                usuario.getAcessarList().add(acessar);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAcessar(acessar.getAcessarPK()) != null) {
                throw new PreexistingEntityException("Acessar " + acessar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acessar acessar) throws NonexistentEntityException, Exception {
        acessar.getAcessarPK().setIdUsuario(acessar.getUsuario().getId());
        acessar.getAcessarPK().setIdOrganizacao(acessar.getOrganizacao().getId());
        acessar.getAcessarPK().setIdProblema(acessar.getProblema().getId());
        acessar.getAcessarPK().setIdPerfil(acessar.getPerfil().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acessar persistentAcessar = em.find(Acessar.class, acessar.getAcessarPK());
            Organizacao organizacaoOld = persistentAcessar.getOrganizacao();
            Organizacao organizacaoNew = acessar.getOrganizacao();
            Perfil perfilOld = persistentAcessar.getPerfil();
            Perfil perfilNew = acessar.getPerfil();
            Problema problemaOld = persistentAcessar.getProblema();
            Problema problemaNew = acessar.getProblema();
            Usuario usuarioOld = persistentAcessar.getUsuario();
            Usuario usuarioNew = acessar.getUsuario();
            if (organizacaoNew != null) {
                organizacaoNew = em.getReference(organizacaoNew.getClass(), organizacaoNew.getId());
                acessar.setOrganizacao(organizacaoNew);
            }
            if (perfilNew != null) {
                perfilNew = em.getReference(perfilNew.getClass(), perfilNew.getId());
                acessar.setPerfil(perfilNew);
            }
            if (problemaNew != null) {
                problemaNew = em.getReference(problemaNew.getClass(), problemaNew.getId());
                acessar.setProblema(problemaNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                acessar.setUsuario(usuarioNew);
            }
            acessar = em.merge(acessar);
            if (organizacaoOld != null && !organizacaoOld.equals(organizacaoNew)) {
                organizacaoOld.getAcessarList().remove(acessar);
                organizacaoOld = em.merge(organizacaoOld);
            }
            if (organizacaoNew != null && !organizacaoNew.equals(organizacaoOld)) {
                organizacaoNew.getAcessarList().add(acessar);
                organizacaoNew = em.merge(organizacaoNew);
            }
            if (perfilOld != null && !perfilOld.equals(perfilNew)) {
                perfilOld.getAcessarList().remove(acessar);
                perfilOld = em.merge(perfilOld);
            }
            if (perfilNew != null && !perfilNew.equals(perfilOld)) {
                perfilNew.getAcessarList().add(acessar);
                perfilNew = em.merge(perfilNew);
            }
            if (problemaOld != null && !problemaOld.equals(problemaNew)) {
                problemaOld.getAcessarList().remove(acessar);
                problemaOld = em.merge(problemaOld);
            }
            if (problemaNew != null && !problemaNew.equals(problemaOld)) {
                problemaNew.getAcessarList().add(acessar);
                problemaNew = em.merge(problemaNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getAcessarList().remove(acessar);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getAcessarList().add(acessar);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AcessarPK id = acessar.getAcessarPK();
                if (findAcessar(id) == null) {
                    throw new NonexistentEntityException("The acessar with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AcessarPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acessar acessar;
            try {
                acessar = em.getReference(Acessar.class, id);
                acessar.getAcessarPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acessar with id " + id + " no longer exists.", enfe);
            }
            Organizacao organizacao = acessar.getOrganizacao();
            if (organizacao != null) {
                organizacao.getAcessarList().remove(acessar);
                organizacao = em.merge(organizacao);
            }
            Perfil perfil = acessar.getPerfil();
            if (perfil != null) {
                perfil.getAcessarList().remove(acessar);
                perfil = em.merge(perfil);
            }
            Problema problema = acessar.getProblema();
            if (problema != null) {
                problema.getAcessarList().remove(acessar);
                problema = em.merge(problema);
            }
            Usuario usuario = acessar.getUsuario();
            if (usuario != null) {
                usuario.getAcessarList().remove(acessar);
                usuario = em.merge(usuario);
            }
            em.remove(acessar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acessar> findAcessarEntities() {
        return findAcessarEntities(true, -1, -1);
    }

    public List<Acessar> findAcessarEntities(int maxResults, int firstResult) {
        return findAcessarEntities(false, maxResults, firstResult);
    }

    private List<Acessar> findAcessarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acessar.class));
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

    public Acessar findAcessar(AcessarPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acessar.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcessarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acessar> rt = cq.from(Acessar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
