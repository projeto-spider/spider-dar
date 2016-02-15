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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Organizacao;

/**
 *
 * @author Iuri Raiol
 */
public class OrganizacaoJpaController implements Serializable
{

    public OrganizacaoJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Organizacao organizacao)
    {
        if (organizacao.getProblemaList() == null)
        {
            organizacao.setProblemaList(new ArrayList<Problema>());
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Problema> attachedProblemaList = new ArrayList<Problema>();
            for (Problema problemaListProblemaToAttach : organizacao.getProblemaList())
            {
                problemaListProblemaToAttach = em.getReference(problemaListProblemaToAttach.getClass(), problemaListProblemaToAttach.getId());
                attachedProblemaList.add(problemaListProblemaToAttach);
            }
            organizacao.setProblemaList(attachedProblemaList);
            em.persist(organizacao);
            for (Problema problemaListProblema : organizacao.getProblemaList())
            {
                Organizacao oldIdOrganizacaoOfProblemaListProblema = problemaListProblema.getIdOrganizacao();
                problemaListProblema.setIdOrganizacao(organizacao);
                problemaListProblema = em.merge(problemaListProblema);
                if (oldIdOrganizacaoOfProblemaListProblema != null)
                {
                    oldIdOrganizacaoOfProblemaListProblema.getProblemaList().remove(problemaListProblema);
                    oldIdOrganizacaoOfProblemaListProblema = em.merge(oldIdOrganizacaoOfProblemaListProblema);
                }
            }
            em.getTransaction().commit();
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void edit(Organizacao organizacao) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao persistentOrganizacao = em.find(Organizacao.class, organizacao.getId());
            List<Problema> problemaListOld = persistentOrganizacao.getProblemaList();
            List<Problema> problemaListNew = organizacao.getProblemaList();
            List<String> illegalOrphanMessages = null;
            for (Problema problemaListOldProblema : problemaListOld)
            {
                if (!problemaListNew.contains(problemaListOldProblema))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Problema " + problemaListOldProblema + " since its idOrganizacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Problema> attachedProblemaListNew = new ArrayList<Problema>();
            for (Problema problemaListNewProblemaToAttach : problemaListNew)
            {
                problemaListNewProblemaToAttach = em.getReference(problemaListNewProblemaToAttach.getClass(), problemaListNewProblemaToAttach.getId());
                attachedProblemaListNew.add(problemaListNewProblemaToAttach);
            }
            problemaListNew = attachedProblemaListNew;
            organizacao.setProblemaList(problemaListNew);
            organizacao = em.merge(organizacao);
            for (Problema problemaListNewProblema : problemaListNew)
            {
                if (!problemaListOld.contains(problemaListNewProblema))
                {
                    Organizacao oldIdOrganizacaoOfProblemaListNewProblema = problemaListNewProblema.getIdOrganizacao();
                    problemaListNewProblema.setIdOrganizacao(organizacao);
                    problemaListNewProblema = em.merge(problemaListNewProblema);
                    if (oldIdOrganizacaoOfProblemaListNewProblema != null && !oldIdOrganizacaoOfProblemaListNewProblema.equals(organizacao))
                    {
                        oldIdOrganizacaoOfProblemaListNewProblema.getProblemaList().remove(problemaListNewProblema);
                        oldIdOrganizacaoOfProblemaListNewProblema = em.merge(oldIdOrganizacaoOfProblemaListNewProblema);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                Integer id = organizacao.getId();
                if (findOrganizacao(id) == null)
                {
                    throw new NonexistentEntityException("The organizacao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao organizacao;
            try
            {
                organizacao = em.getReference(Organizacao.class, id);
                organizacao.getId();
            } catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The organizacao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Problema> problemaListOrphanCheck = organizacao.getProblemaList();
            for (Problema problemaListOrphanCheckProblema : problemaListOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Organizacao (" + organizacao + ") cannot be destroyed since the Problema " + problemaListOrphanCheckProblema + " in its problemaList field has a non-nullable idOrganizacao field.");
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(organizacao);
            em.getTransaction().commit();
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public List<Organizacao> findOrganizacaoEntities()
    {
        return findOrganizacaoEntities(true, -1, -1);
    }

    public List<Organizacao> findOrganizacaoEntities(int maxResults, int firstResult)
    {
        return findOrganizacaoEntities(false, maxResults, firstResult);
    }

    private List<Organizacao> findOrganizacaoEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Organizacao.class));
            Query q = em.createQuery(cq);
            if (!all)
            {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

    public Organizacao findOrganizacao(Integer id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Organizacao.class, id);
        } finally
        {
            em.close();
        }
    }

    public int getOrganizacaoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Organizacao> rt = cq.from(Organizacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally
        {
            em.close();
        }
    }
    
}
