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
    
    public Problema findProblemaByCodigoAndIdOrganizacao(String codigo, int idOrganizacao)
    {
        EntityManager em = getEntityManager();
        
        try
        {
            TypedQuery<Problema> query = em.createQuery("FROM Problema p WHERE p.codigo = :codigo AND p.idOrganizacao.id = :idOrganizacao",Problema.class);
            
            TypedQuery<Problema> result = query.setParameter("codigo", codigo).setParameter("idOrganizacao", idOrganizacao);
            
            return result.getSingleResult();
        }
        finally
        {
            em.close();
        }
    }
    
    public List<Problema> listProblemasByNomeOuCodigo(String busca)
    {
        EntityManager em = getEntityManager();
        
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Problema> problema = cq.from(Problema.class);
            cq.select(problema);
            
            Predicate predicate = em.getCriteriaBuilder().equal(problema.get("codigo"), "%" + busca + "%");
            cq.where(predicate);
            
            Query query = em.createQuery(cq);
            
            return query.getResultList();
        }
        finally
        {
            em.close();
        }
    }
}
