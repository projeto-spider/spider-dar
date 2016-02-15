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
import model.Keyword;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Problema;

/**
 *
 * @author Iuri Raiol
 */
public class ProblemaJpaController implements Serializable
{

    public ProblemaJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Problema problema)
    {
        if (problema.getKeywordList() == null)
        {
            problema.setKeywordList(new ArrayList<Keyword>());
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao idOrganizacao = problema.getIdOrganizacao();
            if (idOrganizacao != null)
            {
                idOrganizacao = em.getReference(idOrganizacao.getClass(), idOrganizacao.getId());
                problema.setIdOrganizacao(idOrganizacao);
            }
            List<Keyword> attachedKeywordList = new ArrayList<Keyword>();
            for (Keyword keywordListKeywordToAttach : problema.getKeywordList())
            {
                keywordListKeywordToAttach = em.getReference(keywordListKeywordToAttach.getClass(), keywordListKeywordToAttach.getKeywordPK());
                attachedKeywordList.add(keywordListKeywordToAttach);
            }
            problema.setKeywordList(attachedKeywordList);
            em.persist(problema);
            if (idOrganizacao != null)
            {
                idOrganizacao.getProblemaList().add(problema);
                idOrganizacao = em.merge(idOrganizacao);
            }
            for (Keyword keywordListKeyword : problema.getKeywordList())
            {
                Problema oldProblemaOfKeywordListKeyword = keywordListKeyword.getProblema();
                keywordListKeyword.setProblema(problema);
                keywordListKeyword = em.merge(keywordListKeyword);
                if (oldProblemaOfKeywordListKeyword != null)
                {
                    oldProblemaOfKeywordListKeyword.getKeywordList().remove(keywordListKeyword);
                    oldProblemaOfKeywordListKeyword = em.merge(oldProblemaOfKeywordListKeyword);
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

    public void edit(Problema problema) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Problema persistentProblema = em.find(Problema.class, problema.getId());
            Organizacao idOrganizacaoOld = persistentProblema.getIdOrganizacao();
            Organizacao idOrganizacaoNew = problema.getIdOrganizacao();
            List<Keyword> keywordListOld = persistentProblema.getKeywordList();
            List<Keyword> keywordListNew = problema.getKeywordList();
            List<String> illegalOrphanMessages = null;
            for (Keyword keywordListOldKeyword : keywordListOld)
            {
                if (!keywordListNew.contains(keywordListOldKeyword))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Keyword " + keywordListOldKeyword + " since its problema field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idOrganizacaoNew != null)
            {
                idOrganizacaoNew = em.getReference(idOrganizacaoNew.getClass(), idOrganizacaoNew.getId());
                problema.setIdOrganizacao(idOrganizacaoNew);
            }
            List<Keyword> attachedKeywordListNew = new ArrayList<Keyword>();
            for (Keyword keywordListNewKeywordToAttach : keywordListNew)
            {
                keywordListNewKeywordToAttach = em.getReference(keywordListNewKeywordToAttach.getClass(), keywordListNewKeywordToAttach.getKeywordPK());
                attachedKeywordListNew.add(keywordListNewKeywordToAttach);
            }
            keywordListNew = attachedKeywordListNew;
            problema.setKeywordList(keywordListNew);
            problema = em.merge(problema);
            if (idOrganizacaoOld != null && !idOrganizacaoOld.equals(idOrganizacaoNew))
            {
                idOrganizacaoOld.getProblemaList().remove(problema);
                idOrganizacaoOld = em.merge(idOrganizacaoOld);
            }
            if (idOrganizacaoNew != null && !idOrganizacaoNew.equals(idOrganizacaoOld))
            {
                idOrganizacaoNew.getProblemaList().add(problema);
                idOrganizacaoNew = em.merge(idOrganizacaoNew);
            }
            for (Keyword keywordListNewKeyword : keywordListNew)
            {
                if (!keywordListOld.contains(keywordListNewKeyword))
                {
                    Problema oldProblemaOfKeywordListNewKeyword = keywordListNewKeyword.getProblema();
                    keywordListNewKeyword.setProblema(problema);
                    keywordListNewKeyword = em.merge(keywordListNewKeyword);
                    if (oldProblemaOfKeywordListNewKeyword != null && !oldProblemaOfKeywordListNewKeyword.equals(problema))
                    {
                        oldProblemaOfKeywordListNewKeyword.getKeywordList().remove(keywordListNewKeyword);
                        oldProblemaOfKeywordListNewKeyword = em.merge(oldProblemaOfKeywordListNewKeyword);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                Integer id = problema.getId();
                if (findProblema(id) == null)
                {
                    throw new NonexistentEntityException("The problema with id " + id + " no longer exists.");
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
            Problema problema;
            try
            {
                problema = em.getReference(Problema.class, id);
                problema.getId();
            } catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The problema with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Keyword> keywordListOrphanCheck = problema.getKeywordList();
            for (Keyword keywordListOrphanCheckKeyword : keywordListOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Problema (" + problema + ") cannot be destroyed since the Keyword " + keywordListOrphanCheckKeyword + " in its keywordList field has a non-nullable problema field.");
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Organizacao idOrganizacao = problema.getIdOrganizacao();
            if (idOrganizacao != null)
            {
                idOrganizacao.getProblemaList().remove(problema);
                idOrganizacao = em.merge(idOrganizacao);
            }
            em.remove(problema);
            em.getTransaction().commit();
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public List<Problema> findProblemaEntities()
    {
        return findProblemaEntities(true, -1, -1);
    }

    public List<Problema> findProblemaEntities(int maxResults, int firstResult)
    {
        return findProblemaEntities(false, maxResults, firstResult);
    }

    private List<Problema> findProblemaEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Problema.class));
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

    public Problema findProblema(Integer id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Problema.class, id);
        } finally
        {
            em.close();
        }
    }

    public int getProblemaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Problema> rt = cq.from(Problema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally
        {
            em.close();
        }
    }
    
}
