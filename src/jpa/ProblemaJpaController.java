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
import model.Criterio;
import model.Tarefa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Historico;
import model.Alternativa;
import model.Problema;

/**
 *
 * @author Sandro Bezerra
 */
public class ProblemaJpaController implements Serializable {

    public ProblemaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Problema problema) {
        if (problema.getTarefaList() == null) {
            problema.setTarefaList(new ArrayList<Tarefa>());
        }
        if (problema.getHistoricoList() == null) {
            problema.setHistoricoList(new ArrayList<Historico>());
        }
        if (problema.getAlternativaList() == null) {
            problema.setAlternativaList(new ArrayList<Alternativa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Criterio idCriterio = problema.getIdCriterio();
            if (idCriterio != null) {
                idCriterio = em.getReference(idCriterio.getClass(), idCriterio.getId());
                problema.setIdCriterio(idCriterio);
            }
            List<Tarefa> attachedTarefaList = new ArrayList<Tarefa>();
            for (Tarefa tarefaListTarefaToAttach : problema.getTarefaList()) {
                tarefaListTarefaToAttach = em.getReference(tarefaListTarefaToAttach.getClass(), tarefaListTarefaToAttach.getId());
                attachedTarefaList.add(tarefaListTarefaToAttach);
            }
            problema.setTarefaList(attachedTarefaList);
            List<Historico> attachedHistoricoList = new ArrayList<Historico>();
            for (Historico historicoListHistoricoToAttach : problema.getHistoricoList()) {
                historicoListHistoricoToAttach = em.getReference(historicoListHistoricoToAttach.getClass(), historicoListHistoricoToAttach.getId());
                attachedHistoricoList.add(historicoListHistoricoToAttach);
            }
            problema.setHistoricoList(attachedHistoricoList);
            List<Alternativa> attachedAlternativaList = new ArrayList<Alternativa>();
            for (Alternativa alternativaListAlternativaToAttach : problema.getAlternativaList()) {
                alternativaListAlternativaToAttach = em.getReference(alternativaListAlternativaToAttach.getClass(), alternativaListAlternativaToAttach.getId());
                attachedAlternativaList.add(alternativaListAlternativaToAttach);
            }
            problema.setAlternativaList(attachedAlternativaList);
            em.persist(problema);
            if (idCriterio != null) {
                idCriterio.getProblemaList().add(problema);
                idCriterio = em.merge(idCriterio);
            }
            for (Tarefa tarefaListTarefa : problema.getTarefaList()) {
                Problema oldIdProblemaOfTarefaListTarefa = tarefaListTarefa.getIdProblema();
                tarefaListTarefa.setIdProblema(problema);
                tarefaListTarefa = em.merge(tarefaListTarefa);
                if (oldIdProblemaOfTarefaListTarefa != null) {
                    oldIdProblemaOfTarefaListTarefa.getTarefaList().remove(tarefaListTarefa);
                    oldIdProblemaOfTarefaListTarefa = em.merge(oldIdProblemaOfTarefaListTarefa);
                }
            }
            for (Historico historicoListHistorico : problema.getHistoricoList()) {
                Problema oldIdProblemaOfHistoricoListHistorico = historicoListHistorico.getIdProblema();
                historicoListHistorico.setIdProblema(problema);
                historicoListHistorico = em.merge(historicoListHistorico);
                if (oldIdProblemaOfHistoricoListHistorico != null) {
                    oldIdProblemaOfHistoricoListHistorico.getHistoricoList().remove(historicoListHistorico);
                    oldIdProblemaOfHistoricoListHistorico = em.merge(oldIdProblemaOfHistoricoListHistorico);
                }
            }
            for (Alternativa alternativaListAlternativa : problema.getAlternativaList()) {
                Problema oldIdProblemaOfAlternativaListAlternativa = alternativaListAlternativa.getIdProblema();
                alternativaListAlternativa.setIdProblema(problema);
                alternativaListAlternativa = em.merge(alternativaListAlternativa);
                if (oldIdProblemaOfAlternativaListAlternativa != null) {
                    oldIdProblemaOfAlternativaListAlternativa.getAlternativaList().remove(alternativaListAlternativa);
                    oldIdProblemaOfAlternativaListAlternativa = em.merge(oldIdProblemaOfAlternativaListAlternativa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Problema problema) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Problema persistentProblema = em.find(Problema.class, problema.getId());
            Criterio idCriterioOld = persistentProblema.getIdCriterio();
            Criterio idCriterioNew = problema.getIdCriterio();
            List<Tarefa> tarefaListOld = persistentProblema.getTarefaList();
            List<Tarefa> tarefaListNew = problema.getTarefaList();
            List<Historico> historicoListOld = persistentProblema.getHistoricoList();
            List<Historico> historicoListNew = problema.getHistoricoList();
            List<Alternativa> alternativaListOld = persistentProblema.getAlternativaList();
            List<Alternativa> alternativaListNew = problema.getAlternativaList();
            List<String> illegalOrphanMessages = null;
            for (Tarefa tarefaListOldTarefa : tarefaListOld) {
                if (!tarefaListNew.contains(tarefaListOldTarefa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tarefa " + tarefaListOldTarefa + " since its idProblema field is not nullable.");
                }
            }
            for (Historico historicoListOldHistorico : historicoListOld) {
                if (!historicoListNew.contains(historicoListOldHistorico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historico " + historicoListOldHistorico + " since its idProblema field is not nullable.");
                }
            }
            for (Alternativa alternativaListOldAlternativa : alternativaListOld) {
                if (!alternativaListNew.contains(alternativaListOldAlternativa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Alternativa " + alternativaListOldAlternativa + " since its idProblema field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCriterioNew != null) {
                idCriterioNew = em.getReference(idCriterioNew.getClass(), idCriterioNew.getId());
                problema.setIdCriterio(idCriterioNew);
            }
            List<Tarefa> attachedTarefaListNew = new ArrayList<Tarefa>();
            for (Tarefa tarefaListNewTarefaToAttach : tarefaListNew) {
                tarefaListNewTarefaToAttach = em.getReference(tarefaListNewTarefaToAttach.getClass(), tarefaListNewTarefaToAttach.getId());
                attachedTarefaListNew.add(tarefaListNewTarefaToAttach);
            }
            tarefaListNew = attachedTarefaListNew;
            problema.setTarefaList(tarefaListNew);
            List<Historico> attachedHistoricoListNew = new ArrayList<Historico>();
            for (Historico historicoListNewHistoricoToAttach : historicoListNew) {
                historicoListNewHistoricoToAttach = em.getReference(historicoListNewHistoricoToAttach.getClass(), historicoListNewHistoricoToAttach.getId());
                attachedHistoricoListNew.add(historicoListNewHistoricoToAttach);
            }
            historicoListNew = attachedHistoricoListNew;
            problema.setHistoricoList(historicoListNew);
            List<Alternativa> attachedAlternativaListNew = new ArrayList<Alternativa>();
            for (Alternativa alternativaListNewAlternativaToAttach : alternativaListNew) {
                alternativaListNewAlternativaToAttach = em.getReference(alternativaListNewAlternativaToAttach.getClass(), alternativaListNewAlternativaToAttach.getId());
                attachedAlternativaListNew.add(alternativaListNewAlternativaToAttach);
            }
            alternativaListNew = attachedAlternativaListNew;
            problema.setAlternativaList(alternativaListNew);
            problema = em.merge(problema);
            if (idCriterioOld != null && !idCriterioOld.equals(idCriterioNew)) {
                idCriterioOld.getProblemaList().remove(problema);
                idCriterioOld = em.merge(idCriterioOld);
            }
            if (idCriterioNew != null && !idCriterioNew.equals(idCriterioOld)) {
                idCriterioNew.getProblemaList().add(problema);
                idCriterioNew = em.merge(idCriterioNew);
            }
            for (Tarefa tarefaListNewTarefa : tarefaListNew) {
                if (!tarefaListOld.contains(tarefaListNewTarefa)) {
                    Problema oldIdProblemaOfTarefaListNewTarefa = tarefaListNewTarefa.getIdProblema();
                    tarefaListNewTarefa.setIdProblema(problema);
                    tarefaListNewTarefa = em.merge(tarefaListNewTarefa);
                    if (oldIdProblemaOfTarefaListNewTarefa != null && !oldIdProblemaOfTarefaListNewTarefa.equals(problema)) {
                        oldIdProblemaOfTarefaListNewTarefa.getTarefaList().remove(tarefaListNewTarefa);
                        oldIdProblemaOfTarefaListNewTarefa = em.merge(oldIdProblemaOfTarefaListNewTarefa);
                    }
                }
            }
            for (Historico historicoListNewHistorico : historicoListNew) {
                if (!historicoListOld.contains(historicoListNewHistorico)) {
                    Problema oldIdProblemaOfHistoricoListNewHistorico = historicoListNewHistorico.getIdProblema();
                    historicoListNewHistorico.setIdProblema(problema);
                    historicoListNewHistorico = em.merge(historicoListNewHistorico);
                    if (oldIdProblemaOfHistoricoListNewHistorico != null && !oldIdProblemaOfHistoricoListNewHistorico.equals(problema)) {
                        oldIdProblemaOfHistoricoListNewHistorico.getHistoricoList().remove(historicoListNewHistorico);
                        oldIdProblemaOfHistoricoListNewHistorico = em.merge(oldIdProblemaOfHistoricoListNewHistorico);
                    }
                }
            }
            for (Alternativa alternativaListNewAlternativa : alternativaListNew) {
                if (!alternativaListOld.contains(alternativaListNewAlternativa)) {
                    Problema oldIdProblemaOfAlternativaListNewAlternativa = alternativaListNewAlternativa.getIdProblema();
                    alternativaListNewAlternativa.setIdProblema(problema);
                    alternativaListNewAlternativa = em.merge(alternativaListNewAlternativa);
                    if (oldIdProblemaOfAlternativaListNewAlternativa != null && !oldIdProblemaOfAlternativaListNewAlternativa.equals(problema)) {
                        oldIdProblemaOfAlternativaListNewAlternativa.getAlternativaList().remove(alternativaListNewAlternativa);
                        oldIdProblemaOfAlternativaListNewAlternativa = em.merge(oldIdProblemaOfAlternativaListNewAlternativa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = problema.getId();
                if (findProblema(id) == null) {
                    throw new NonexistentEntityException("The problema with id " + id + " no longer exists.");
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
            Problema problema;
            try {
                problema = em.getReference(Problema.class, id);
                problema.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The problema with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tarefa> tarefaListOrphanCheck = problema.getTarefaList();
            for (Tarefa tarefaListOrphanCheckTarefa : tarefaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Problema (" + problema + ") cannot be destroyed since the Tarefa " + tarefaListOrphanCheckTarefa + " in its tarefaList field has a non-nullable idProblema field.");
            }
            List<Historico> historicoListOrphanCheck = problema.getHistoricoList();
            for (Historico historicoListOrphanCheckHistorico : historicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Problema (" + problema + ") cannot be destroyed since the Historico " + historicoListOrphanCheckHistorico + " in its historicoList field has a non-nullable idProblema field.");
            }
            List<Alternativa> alternativaListOrphanCheck = problema.getAlternativaList();
            for (Alternativa alternativaListOrphanCheckAlternativa : alternativaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Problema (" + problema + ") cannot be destroyed since the Alternativa " + alternativaListOrphanCheckAlternativa + " in its alternativaList field has a non-nullable idProblema field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Criterio idCriterio = problema.getIdCriterio();
            if (idCriterio != null) {
                idCriterio.getProblemaList().remove(problema);
                idCriterio = em.merge(idCriterio);
            }
            em.remove(problema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Problema> findProblemaEntities() {
        return findProblemaEntities(true, -1, -1);
    }

    public List<Problema> findProblemaEntities(int maxResults, int firstResult) {
        return findProblemaEntities(false, maxResults, firstResult);
    }

    private List<Problema> findProblemaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Problema.class));
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

    public Problema findProblema(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Problema.class, id);
        } finally {
            em.close();
        }
    }

    public int getProblemaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Problema> rt = cq.from(Problema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
