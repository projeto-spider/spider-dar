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
import model.Acessar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Problema;
import model.Perfil;
import model.Guia;
import model.Organizacao;

/**
 *
 * @author Bleno Vale
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
        if (organizacao.getAcessarList() == null) {
            organizacao.setAcessarList(new ArrayList<Acessar>());
        }
        if (organizacao.getProblemaList() == null) {
            organizacao.setProblemaList(new ArrayList<Problema>());
        }
        if (organizacao.getPerfilList() == null) {
            organizacao.setPerfilList(new ArrayList<Perfil>());
        }
        if (organizacao.getGuiaList() == null) {
            organizacao.setGuiaList(new ArrayList<Guia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Acessar> attachedAcessarList = new ArrayList<Acessar>();
            for (Acessar acessarListAcessarToAttach : organizacao.getAcessarList()) {
                acessarListAcessarToAttach = em.getReference(acessarListAcessarToAttach.getClass(), acessarListAcessarToAttach.getAcessarPK());
                attachedAcessarList.add(acessarListAcessarToAttach);
            }
            organizacao.setAcessarList(attachedAcessarList);
            List<Problema> attachedProblemaList = new ArrayList<Problema>();
            for (Problema problemaListProblemaToAttach : organizacao.getProblemaList()) {
                problemaListProblemaToAttach = em.getReference(problemaListProblemaToAttach.getClass(), problemaListProblemaToAttach.getId());
                attachedProblemaList.add(problemaListProblemaToAttach);
            }
            organizacao.setProblemaList(attachedProblemaList);
            List<Perfil> attachedPerfilList = new ArrayList<Perfil>();
            for (Perfil perfilListPerfilToAttach : organizacao.getPerfilList()) {
                perfilListPerfilToAttach = em.getReference(perfilListPerfilToAttach.getClass(), perfilListPerfilToAttach.getId());
                attachedPerfilList.add(perfilListPerfilToAttach);
            }
            organizacao.setPerfilList(attachedPerfilList);
            List<Guia> attachedGuiaList = new ArrayList<Guia>();
            for (Guia guiaListGuiaToAttach : organizacao.getGuiaList()) {
                guiaListGuiaToAttach = em.getReference(guiaListGuiaToAttach.getClass(), guiaListGuiaToAttach.getId());
                attachedGuiaList.add(guiaListGuiaToAttach);
            }
            organizacao.setGuiaList(attachedGuiaList);
            em.persist(organizacao);
            for (Acessar acessarListAcessar : organizacao.getAcessarList()) {
                Organizacao oldOrganizacaoOfAcessarListAcessar = acessarListAcessar.getOrganizacao();
                acessarListAcessar.setOrganizacao(organizacao);
                acessarListAcessar = em.merge(acessarListAcessar);
                if (oldOrganizacaoOfAcessarListAcessar != null) {
                    oldOrganizacaoOfAcessarListAcessar.getAcessarList().remove(acessarListAcessar);
                    oldOrganizacaoOfAcessarListAcessar = em.merge(oldOrganizacaoOfAcessarListAcessar);
                }
            }
            for (Problema problemaListProblema : organizacao.getProblemaList()) {
                Organizacao oldIdOrganizacaoOfProblemaListProblema = problemaListProblema.getIdOrganizacao();
                problemaListProblema.setIdOrganizacao(organizacao);
                problemaListProblema = em.merge(problemaListProblema);
                if (oldIdOrganizacaoOfProblemaListProblema != null) {
                    oldIdOrganizacaoOfProblemaListProblema.getProblemaList().remove(problemaListProblema);
                    oldIdOrganizacaoOfProblemaListProblema = em.merge(oldIdOrganizacaoOfProblemaListProblema);
                }
            }
            for (Perfil perfilListPerfil : organizacao.getPerfilList()) {
                Organizacao oldIdOrganizacaoOfPerfilListPerfil = perfilListPerfil.getIdOrganizacao();
                perfilListPerfil.setIdOrganizacao(organizacao);
                perfilListPerfil = em.merge(perfilListPerfil);
                if (oldIdOrganizacaoOfPerfilListPerfil != null) {
                    oldIdOrganizacaoOfPerfilListPerfil.getPerfilList().remove(perfilListPerfil);
                    oldIdOrganizacaoOfPerfilListPerfil = em.merge(oldIdOrganizacaoOfPerfilListPerfil);
                }
            }
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
            List<Acessar> acessarListOld = persistentOrganizacao.getAcessarList();
            List<Acessar> acessarListNew = organizacao.getAcessarList();
            List<Problema> problemaListOld = persistentOrganizacao.getProblemaList();
            List<Problema> problemaListNew = organizacao.getProblemaList();
            List<Perfil> perfilListOld = persistentOrganizacao.getPerfilList();
            List<Perfil> perfilListNew = organizacao.getPerfilList();
            List<Guia> guiaListOld = persistentOrganizacao.getGuiaList();
            List<Guia> guiaListNew = organizacao.getGuiaList();
            List<String> illegalOrphanMessages = null;
            for (Acessar acessarListOldAcessar : acessarListOld) {
                if (!acessarListNew.contains(acessarListOldAcessar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Acessar " + acessarListOldAcessar + " since its organizacao field is not nullable.");
                }
            }
            for (Problema problemaListOldProblema : problemaListOld) {
                if (!problemaListNew.contains(problemaListOldProblema)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Problema " + problemaListOldProblema + " since its idOrganizacao field is not nullable.");
                }
            }
            for (Perfil perfilListOldPerfil : perfilListOld) {
                if (!perfilListNew.contains(perfilListOldPerfil)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Perfil " + perfilListOldPerfil + " since its idOrganizacao field is not nullable.");
                }
            }
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
            List<Acessar> attachedAcessarListNew = new ArrayList<Acessar>();
            for (Acessar acessarListNewAcessarToAttach : acessarListNew) {
                acessarListNewAcessarToAttach = em.getReference(acessarListNewAcessarToAttach.getClass(), acessarListNewAcessarToAttach.getAcessarPK());
                attachedAcessarListNew.add(acessarListNewAcessarToAttach);
            }
            acessarListNew = attachedAcessarListNew;
            organizacao.setAcessarList(acessarListNew);
            List<Problema> attachedProblemaListNew = new ArrayList<Problema>();
            for (Problema problemaListNewProblemaToAttach : problemaListNew) {
                problemaListNewProblemaToAttach = em.getReference(problemaListNewProblemaToAttach.getClass(), problemaListNewProblemaToAttach.getId());
                attachedProblemaListNew.add(problemaListNewProblemaToAttach);
            }
            problemaListNew = attachedProblemaListNew;
            organizacao.setProblemaList(problemaListNew);
            List<Perfil> attachedPerfilListNew = new ArrayList<Perfil>();
            for (Perfil perfilListNewPerfilToAttach : perfilListNew) {
                perfilListNewPerfilToAttach = em.getReference(perfilListNewPerfilToAttach.getClass(), perfilListNewPerfilToAttach.getId());
                attachedPerfilListNew.add(perfilListNewPerfilToAttach);
            }
            perfilListNew = attachedPerfilListNew;
            organizacao.setPerfilList(perfilListNew);
            List<Guia> attachedGuiaListNew = new ArrayList<Guia>();
            for (Guia guiaListNewGuiaToAttach : guiaListNew) {
                guiaListNewGuiaToAttach = em.getReference(guiaListNewGuiaToAttach.getClass(), guiaListNewGuiaToAttach.getId());
                attachedGuiaListNew.add(guiaListNewGuiaToAttach);
            }
            guiaListNew = attachedGuiaListNew;
            organizacao.setGuiaList(guiaListNew);
            organizacao = em.merge(organizacao);
            for (Acessar acessarListNewAcessar : acessarListNew) {
                if (!acessarListOld.contains(acessarListNewAcessar)) {
                    Organizacao oldOrganizacaoOfAcessarListNewAcessar = acessarListNewAcessar.getOrganizacao();
                    acessarListNewAcessar.setOrganizacao(organizacao);
                    acessarListNewAcessar = em.merge(acessarListNewAcessar);
                    if (oldOrganizacaoOfAcessarListNewAcessar != null && !oldOrganizacaoOfAcessarListNewAcessar.equals(organizacao)) {
                        oldOrganizacaoOfAcessarListNewAcessar.getAcessarList().remove(acessarListNewAcessar);
                        oldOrganizacaoOfAcessarListNewAcessar = em.merge(oldOrganizacaoOfAcessarListNewAcessar);
                    }
                }
            }
            for (Problema problemaListNewProblema : problemaListNew) {
                if (!problemaListOld.contains(problemaListNewProblema)) {
                    Organizacao oldIdOrganizacaoOfProblemaListNewProblema = problemaListNewProblema.getIdOrganizacao();
                    problemaListNewProblema.setIdOrganizacao(organizacao);
                    problemaListNewProblema = em.merge(problemaListNewProblema);
                    if (oldIdOrganizacaoOfProblemaListNewProblema != null && !oldIdOrganizacaoOfProblemaListNewProblema.equals(organizacao)) {
                        oldIdOrganizacaoOfProblemaListNewProblema.getProblemaList().remove(problemaListNewProblema);
                        oldIdOrganizacaoOfProblemaListNewProblema = em.merge(oldIdOrganizacaoOfProblemaListNewProblema);
                    }
                }
            }
            for (Perfil perfilListNewPerfil : perfilListNew) {
                if (!perfilListOld.contains(perfilListNewPerfil)) {
                    Organizacao oldIdOrganizacaoOfPerfilListNewPerfil = perfilListNewPerfil.getIdOrganizacao();
                    perfilListNewPerfil.setIdOrganizacao(organizacao);
                    perfilListNewPerfil = em.merge(perfilListNewPerfil);
                    if (oldIdOrganizacaoOfPerfilListNewPerfil != null && !oldIdOrganizacaoOfPerfilListNewPerfil.equals(organizacao)) {
                        oldIdOrganizacaoOfPerfilListNewPerfil.getPerfilList().remove(perfilListNewPerfil);
                        oldIdOrganizacaoOfPerfilListNewPerfil = em.merge(oldIdOrganizacaoOfPerfilListNewPerfil);
                    }
                }
            }
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
            List<Acessar> acessarListOrphanCheck = organizacao.getAcessarList();
            for (Acessar acessarListOrphanCheckAcessar : acessarListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Organizacao (" + organizacao + ") cannot be destroyed since the Acessar " + acessarListOrphanCheckAcessar + " in its acessarList field has a non-nullable organizacao field.");
            }
            List<Problema> problemaListOrphanCheck = organizacao.getProblemaList();
            for (Problema problemaListOrphanCheckProblema : problemaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Organizacao (" + organizacao + ") cannot be destroyed since the Problema " + problemaListOrphanCheckProblema + " in its problemaList field has a non-nullable idOrganizacao field.");
            }
            List<Perfil> perfilListOrphanCheck = organizacao.getPerfilList();
            for (Perfil perfilListOrphanCheckPerfil : perfilListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Organizacao (" + organizacao + ") cannot be destroyed since the Perfil " + perfilListOrphanCheckPerfil + " in its perfilList field has a non-nullable idOrganizacao field.");
            }
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
