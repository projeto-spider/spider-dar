package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import jpa.ProblemaJpaController;
import model.Problema;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class JpaProblema extends ProblemaJpaController{

    public JpaProblema(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Problema createProblema(Problema problema)
    {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try
        {
            tx.begin();
            em.persist(problema);
            em.flush();
            tx.commit();
            return problema;
        }
        finally
        {
            em.close();
        }
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
    
    public List<Problema> listProblemasByNomeCodigoOuKeyword(Request request)
    {
        EntityManager em = getEntityManager();
        
        try
        {
            String busca = request.getData("Problema.busca");
            
//                            SELECT * 
//                            FROM Problema p 
//                            LEFT JOIN Keyword k ON(k.idForeignKey = p.id)
//                            WHERE p.idOrganizacao = 1 
//                                    AND (p.nome LIKE "%111%" OR k.nome LIKE "%111%")
            
            String queryString = "SELECT DISTINCT p FROM Problema p "
                                    + "LEFT JOIN Keyword k "
                                    + "WHERE p.idOrganizacao.id = :idOrganizacao "
                                    + "AND p.id = k.problema.id "
                                    + "AND (p.nome LIKE :nomeProblema OR k.nome LIKE :nomeKeyword)";
            
            TypedQuery<Problema> query = em.createQuery(queryString,Problema.class);
            
            TypedQuery<Problema> result = query.setParameter("idOrganizacao", Integer.parseInt(request.getData("Problema.idOrganizacao"))).
                                                setParameter("nomeKeyword", "%" + busca + "%").
                                                setParameter("nomeProblema", "%" + busca + "%");
            
            return result.getResultList();
        }
        finally
        {
            em.close();
        }
    }
}