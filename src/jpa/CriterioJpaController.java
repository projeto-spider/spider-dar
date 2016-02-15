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
import model.Avaliar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import model.Criterio;
import model.Nota;

/**
 *
 * @author Iuri Raiol
 */
public class CriterioJpaController implements Serializable
{

    public CriterioJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Criterio criterio) throws PreexistingEntityException, Exception
    {
        if (criterio.getAvaliarList() == null)
        {
            criterio.setAvaliarList(new ArrayList<Avaliar>());
        }
        if (criterio.getNotaList() == null)
        {
            criterio.setNotaList(new ArrayList<Nota>());
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Avaliar> attachedAvaliarList = new ArrayList<Avaliar>();
            for (Avaliar avaliarListAvaliarToAttach : criterio.getAvaliarList())
            {
                avaliarListAvaliarToAttach = em.getReference(avaliarListAvaliarToAttach.getClass(), avaliarListAvaliarToAttach.getAvaliarPK());
                attachedAvaliarList.add(avaliarListAvaliarToAttach);
            }
            criterio.setAvaliarList(attachedAvaliarList);
            List<Nota> attachedNotaList = new ArrayList<Nota>();
            for (Nota notaListNotaToAttach : criterio.getNotaList())
            {
                notaListNotaToAttach = em.getReference(notaListNotaToAttach.getClass(), notaListNotaToAttach.getId());
                attachedNotaList.add(notaListNotaToAttach);
            }
            criterio.setNotaList(attachedNotaList);
            em.persist(criterio);
            for (Avaliar avaliarListAvaliar : criterio.getAvaliarList())
            {
                Criterio oldCriterioOfAvaliarListAvaliar = avaliarListAvaliar.getCriterio();
                avaliarListAvaliar.setCriterio(criterio);
                avaliarListAvaliar = em.merge(avaliarListAvaliar);
                if (oldCriterioOfAvaliarListAvaliar != null)
                {
                    oldCriterioOfAvaliarListAvaliar.getAvaliarList().remove(avaliarListAvaliar);
                    oldCriterioOfAvaliarListAvaliar = em.merge(oldCriterioOfAvaliarListAvaliar);
                }
            }
            for (Nota notaListNota : criterio.getNotaList())
            {
                Criterio oldIdCriterioOfNotaListNota = notaListNota.getIdCriterio();
                notaListNota.setIdCriterio(criterio);
                notaListNota = em.merge(notaListNota);
                if (oldIdCriterioOfNotaListNota != null)
                {
                    oldIdCriterioOfNotaListNota.getNotaList().remove(notaListNota);
                    oldIdCriterioOfNotaListNota = em.merge(oldIdCriterioOfNotaListNota);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            if (findCriterio(criterio.getId()) != null)
            {
                throw new PreexistingEntityException("Criterio " + criterio + " already exists.", ex);
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

    public void edit(Criterio criterio) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Criterio persistentCriterio = em.find(Criterio.class, criterio.getId());
            List<Avaliar> avaliarListOld = persistentCriterio.getAvaliarList();
            List<Avaliar> avaliarListNew = criterio.getAvaliarList();
            List<Nota> notaListOld = persistentCriterio.getNotaList();
            List<Nota> notaListNew = criterio.getNotaList();
            List<String> illegalOrphanMessages = null;
            for (Avaliar avaliarListOldAvaliar : avaliarListOld)
            {
                if (!avaliarListNew.contains(avaliarListOldAvaliar))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Avaliar " + avaliarListOldAvaliar + " since its criterio field is not nullable.");
                }
            }
            for (Nota notaListOldNota : notaListOld)
            {
                if (!notaListNew.contains(notaListOldNota))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Nota " + notaListOldNota + " since its idCriterio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Avaliar> attachedAvaliarListNew = new ArrayList<Avaliar>();
            for (Avaliar avaliarListNewAvaliarToAttach : avaliarListNew)
            {
                avaliarListNewAvaliarToAttach = em.getReference(avaliarListNewAvaliarToAttach.getClass(), avaliarListNewAvaliarToAttach.getAvaliarPK());
                attachedAvaliarListNew.add(avaliarListNewAvaliarToAttach);
            }
            avaliarListNew = attachedAvaliarListNew;
            criterio.setAvaliarList(avaliarListNew);
            List<Nota> attachedNotaListNew = new ArrayList<Nota>();
            for (Nota notaListNewNotaToAttach : notaListNew)
            {
                notaListNewNotaToAttach = em.getReference(notaListNewNotaToAttach.getClass(), notaListNewNotaToAttach.getId());
                attachedNotaListNew.add(notaListNewNotaToAttach);
            }
            notaListNew = attachedNotaListNew;
            criterio.setNotaList(notaListNew);
            criterio = em.merge(criterio);
            for (Avaliar avaliarListNewAvaliar : avaliarListNew)
            {
                if (!avaliarListOld.contains(avaliarListNewAvaliar))
                {
                    Criterio oldCriterioOfAvaliarListNewAvaliar = avaliarListNewAvaliar.getCriterio();
                    avaliarListNewAvaliar.setCriterio(criterio);
                    avaliarListNewAvaliar = em.merge(avaliarListNewAvaliar);
                    if (oldCriterioOfAvaliarListNewAvaliar != null && !oldCriterioOfAvaliarListNewAvaliar.equals(criterio))
                    {
                        oldCriterioOfAvaliarListNewAvaliar.getAvaliarList().remove(avaliarListNewAvaliar);
                        oldCriterioOfAvaliarListNewAvaliar = em.merge(oldCriterioOfAvaliarListNewAvaliar);
                    }
                }
            }
            for (Nota notaListNewNota : notaListNew)
            {
                if (!notaListOld.contains(notaListNewNota))
                {
                    Criterio oldIdCriterioOfNotaListNewNota = notaListNewNota.getIdCriterio();
                    notaListNewNota.setIdCriterio(criterio);
                    notaListNewNota = em.merge(notaListNewNota);
                    if (oldIdCriterioOfNotaListNewNota != null && !oldIdCriterioOfNotaListNewNota.equals(criterio))
                    {
                        oldIdCriterioOfNotaListNewNota.getNotaList().remove(notaListNewNota);
                        oldIdCriterioOfNotaListNewNota = em.merge(oldIdCriterioOfNotaListNewNota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                Integer id = criterio.getId();
                if (findCriterio(id) == null)
                {
                    throw new NonexistentEntityException("The criterio with id " + id + " no longer exists.");
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
            Criterio criterio;
            try
            {
                criterio = em.getReference(Criterio.class, id);
                criterio.getId();
            } catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The criterio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Avaliar> avaliarListOrphanCheck = criterio.getAvaliarList();
            for (Avaliar avaliarListOrphanCheckAvaliar : avaliarListOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Criterio (" + criterio + ") cannot be destroyed since the Avaliar " + avaliarListOrphanCheckAvaliar + " in its avaliarList field has a non-nullable criterio field.");
            }
            List<Nota> notaListOrphanCheck = criterio.getNotaList();
            for (Nota notaListOrphanCheckNota : notaListOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Criterio (" + criterio + ") cannot be destroyed since the Nota " + notaListOrphanCheckNota + " in its notaList field has a non-nullable idCriterio field.");
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(criterio);
            em.getTransaction().commit();
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public List<Criterio> findCriterioEntities()
    {
        return findCriterioEntities(true, -1, -1);
    }

    public List<Criterio> findCriterioEntities(int maxResults, int firstResult)
    {
        return findCriterioEntities(false, maxResults, firstResult);
    }

    private List<Criterio> findCriterioEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Criterio.class));
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

    public Criterio findCriterio(Integer id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Criterio.class, id);
        } finally
        {
            em.close();
        }
    }

    public int getCriterioCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Criterio> rt = cq.from(Criterio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally
        {
            em.close();
        }
    }
    
}
