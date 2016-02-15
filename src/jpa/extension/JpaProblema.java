package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import jpa.ProblemaJpaController;
import model.Organizacao;
import model.Problema;
import org.eclipse.persistence.internal.jpa.JPAQuery;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class JpaProblema extends ProblemaJpaController {

    public JpaProblema(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Problema> findProblemasByIdOrganizacao(int idOrg) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Problema> list = entityManager
                    .createQuery("SELECT p FROM Problema p WHERE p.idOrganizacao.id =:idOrg")
                    .setParameter("idOrg", idOrg)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }

    public Problema findProblemasByNameANDIdOrg(String name, int idOrg) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Problema problema = (Problema) entityManager
                    .createQuery("SELECT p FROM Problema p WHERE p.nome =:nome AND p.idOrganizacao.id =:idOrg")
                    .setParameter("nome", name).setParameter("idOrg", idOrg)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return problema;
        } catch (Exception error) {
            throw null;
        }
    }
    
    public Problema findProblemaById(int idProblema, int idOrganizacao)
    {
        EntityManager em = getEntityManager();
        
        try
        {
            TypedQuery<Problema> query = em.createQuery("FROM Problema p WHERE p.id = :idProblema AND p.idOrganizacao.id = :idOrganizacao",Problema.class);
            
            TypedQuery<Problema> result = query.setParameter("idProblema", idProblema).setParameter("idOrganizacao", idOrganizacao);
            
            return result.getSingleResult();
        }
        finally
        {
            em.close();
        }
    }
    
    public List<Problema> listAllProblemasByIdOrganizacao(Request request)
    {
        EntityManager em = getEntityManager();
        
        try
        {
            String queryString = "FROM Problema p WHERE p.idOrganizacao.id = :idOrganizacao";
            
            TypedQuery<Problema> query = em.createQuery(queryString,Problema.class);
            
            TypedQuery<Problema> result = query.setParameter("idOrganizacao", Integer.parseInt(request.getData("Problema.idOrganizacao")));
            
            return result.getResultList();
        }
        finally
        {
            em.close();
        }
    }
    
    public List<Problema> listProblemasByNomeOuCodigo(Request request)
    {
        EntityManager em = getEntityManager();
        
        try
        {
            String busca = request.getData("Problema.busca");
            
            String queryString = "FROM Problema p WHERE p.idOrganizacao.id = :idOrganizacao AND (p.codigo LIKE :buscaCodigo OR p.nome LIKE :buscaNome)";
            
            TypedQuery<Problema> query = em.createQuery(queryString,Problema.class);
            
            TypedQuery<Problema> result = query.setParameter("idOrganizacao", Integer.parseInt(request.getData("Problema.idOrganizacao"))).
                                                setParameter("buscaCodigo", "%" + busca + "%").
                                                setParameter("buscaNome", "%" + busca + "%");
            
            return result.getResultList();
        }
        finally
        {
            em.close();
        }
    }
}