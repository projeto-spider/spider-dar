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
import model.Acessar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Tarefa;
import model.Historico;
import model.Alternativa;
import model.Criterio;
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
        if (problema.getAcessarList() == null) {
            problema.setAcessarList(new ArrayList<Acessar>());
        }
        if (problema.getTarefaList() == null) {
            problema.setTarefaList(new ArrayList<Tarefa>());
        }
        if (problema.getHistoricoList() == null) {
            problema.setHistoricoList(new ArrayList<Historico>());
        }
        if (problema.getAlternativaList() == null) {
            problema.setAlternativaList(new ArrayList<Alternativa>());
        }
        if (problema.getCriterioList() == null) {
            problema.setCriterioList(new ArrayList<Criterio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao idOrganizacao = problema.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao = em.getReference(idOrganizacao.getClass(), idOrganizacao.getId());
                problema.setIdOrganizacao(idOrganizacao);
            }
            List<Acessar> attachedAcessarList = new ArrayList<Acessar>();
            for (Acessar acessarListAcessarToAttach : problema.getAcessarList()) {
                acessarListAcessarToAttach = em.getReference(acessarListAcessarToAttach.getClass(), acessarListAcessarToAttach.getAcessarPK());
                attachedAcessarList.add(acessarListAcessarToAttach);
            }
            problema.setAcessarList(attachedAcessarList);
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
            List<Criterio> attachedCriterioList = new ArrayList<Criterio>();
            for (Criterio criterioListCriterioToAttach : problema.getCriterioList()) {
                criterioListCriterioToAttach = em.getReference(criterioListCriterioToAttach.getClass(), criterioListCriterioToAttach.getId());
                attachedCriterioList.add(criterioListCriterioToAttach);
            }
            problema.setCriterioList(attachedCriterioList);
            em.persist(problema);
            if (idOrganizacao != null) {
                idOrganizacao.getProblemaList().add(problema);
                idOrganizacao = em.merge(idOrganizacao);
            }
            for (Acessar acessarListAcessar : problema.getAcessarList()) {
                Problema oldProblemaOfAcessarListAcessar = acessarListAcessar.getProblema();
                acessarListAcessar.setProblema(problema);
                acessarListAcessar = em.merge(acessarListAcessar);
                if (oldProblemaOfAcessarListAcessar != null) {
                    oldProblemaOfAcessarListAcessar.getAcessarList().remove(acessarListAcessar);
                    oldProblemaOfAcessarListAcessar = em.merge(oldProblemaOfAcessarListAcessar);
                }
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
            for (Criterio criterioListCriterio : problema.getCriterioList()) {
                Problema oldIdProblemaOfCriterioListCriterio = criterioListCriterio.getIdProblema();
                criterioListCriterio.setIdProblema(problema);
                criterioListCriterio = em.merge(criterioListCriterio);
                if (oldIdProblemaOfCriterioListCriterio != null) {
                    oldIdProblemaOfCriterioListCriterio.getCriterioList().remove(criterioListCriterio);
                    oldIdProblemaOfCriterioListCriterio = em.merge(oldIdProblemaOfCriterioListCriterio);
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
            Organizacao idOrganizacaoOld = persistentProblema.getIdOrganizacao();
            Organizacao idOrganizacaoNew = problema.getIdOrganizacao();
            List<Acessar> acessarListOld = persistentProblema.getAcessarList();
            List<Acessar> acessarListNew = problema.getAcessarList();
            List<Tarefa> tarefaListOld = persistentProblema.getTarefaList();
            List<Tarefa> tarefaListNew = problema.getTarefaList();
            List<Historico> historicoListOld = persistentProblema.getHistoricoList();
            List<Historico> historicoListNew = problema.getHistoricoList();
            List<Alternativa> alternativaListOld = persistentProblema.getAlternativaList();
            List<Alternativa> alternativaListNew = problema.getAlternativaList();
            List<Criterio> criterioListOld = persistentProblema.getCriterioList();
            List<Criterio> criterioListNew = problema.getCriterioList();
            List<String> illegalOrphanMessages = null;
            for (Acessar acessarListOldAcessar : acessarListOld) {
                if (!acessarListNew.contains(acessarListOldAcessar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Acessar " + acessarListOldAcessar + " since its problema field is not nullable.");
                }
            }
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
            for (Criterio criterioListOldCriterio : criterioListOld) {
                if (!criterioListNew.contains(criterioListOldCriterio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Criterio " + criterioListOldCriterio + " since its idProblema field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idOrganizacaoNew != null) {
                idOrganizacaoNew = em.getReference(idOrganizacaoNew.getClass(), idOrganizacaoNew.getId());
                problema.setIdOrganizacao(idOrganizacaoNew);
            }
            List<Acessar> attachedAcessarListNew = new ArrayList<Acessar>();
            for (Acessar acessarListNewAcessarToAttach : acessarListNew) {
                acessarListNewAcessarToAttach = em.getReference(acessarListNewAcessarToAttach.getClass(), acessarListNewAcessarToAttach.getAcessarPK());
                attachedAcessarListNew.add(acessarListNewAcessarToAttach);
            }
            acessarListNew = attachedAcessarListNew;
            problema.setAcessarList(acessarListNew);
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
            List<Criterio> attachedCriterioListNew = new ArrayList<Criterio>();
            for (Criterio criterioListNewCriterioToAttach : criterioListNew) {
                criterioListNewCriterioToAttach = em.getReference(criterioListNewCriterioToAttach.getClass(), criterioListNewCriterioToAttach.getId());
                attachedCriterioListNew.add(criterioListNewCriterioToAttach);
            }
            criterioListNew = attachedCriterioListNew;
            problema.setCriterioList(criterioListNew);
            problema = em.merge(problema);
            if (idOrganizacaoOld != null && !idOrganizacaoOld.equals(idOrganizacaoNew)) {
                idOrganizacaoOld.getProblemaList().remove(problema);
                idOrganizacaoOld = em.merge(idOrganizacaoOld);
            }
            if (idOrganizacaoNew != null && !idOrganizacaoNew.equals(idOrganizacaoOld)) {
                idOrganizacaoNew.getProblemaList().add(problema);
                idOrganizacaoNew = em.merge(idOrganizacaoNew);
            }
            for (Acessar acessarListNewAcessar : acessarListNew) {
                if (!acessarListOld.contains(acessarListNewAcessar)) {
                    Problema oldProblemaOfAcessarListNewAcessar = acessarListNewAcessar.getProblema();
                    acessarListNewAcessar.setProblema(problema);
                    acessarListNewAcessar = em.merge(acessarListNewAcessar);
                    if (oldProblemaOfAcessarListNewAcessar != null && !oldProblemaOfAcessarListNewAcessar.equals(problema)) {
                        oldProblemaOfAcessarListNewAcessar.getAcessarList().remove(acessarListNewAcessar);
                        oldProblemaOfAcessarListNewAcessar = em.merge(oldProblemaOfAcessarListNewAcessar);
                    }
                }
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
            for (Criterio criterioListNewCriterio : criterioListNew) {
                if (!criterioListOld.contains(criterioListNewCriterio)) {
                    Problema oldIdProblemaOfCriterioListNewCriterio = criterioListNewCriterio.getIdProblema();
                    criterioListNewCriterio.setIdProblema(problema);
                    criterioListNewCriterio = em.merge(criterioListNewCriterio);
                    if (oldIdProblemaOfCriterioListNewCriterio != null && !oldIdProblemaOfCriterioListNewCriterio.equals(problema)) {
                        oldIdProblemaOfCriterioListNewCriterio.getCriterioList().remove(criterioListNewCriterio);
                        oldIdProblemaOfCriterioListNewCriterio = em.merge(oldIdProblemaOfCriterioListNewCriterio);
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
            List<Acessar> acessarListOrphanCheck = problema.getAcessarList();
            for (Acessar acessarListOrphanCheckAcessar : acessarListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Problema (" + problema + ") cannot be destroyed since the Acessar " + acessarListOrphanCheckAcessar + " in its acessarList field has a non-nullable problema field.");
            }
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
            List<Criterio> criterioListOrphanCheck = problema.getCriterioList();
            for (Criterio criterioListOrphanCheckCriterio : criterioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Problema (" + problema + ") cannot be destroyed since the Criterio " + criterioListOrphanCheckCriterio + " in its criterioList field has a non-nullable idProblema field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Organizacao idOrganizacao = problema.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao.getProblemaList().remove(problema);
                idOrganizacao = em.merge(idOrganizacao);
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
