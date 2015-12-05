package jpa.extension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.OrganizacaoJpaController;
import model.Organizacao;

/**
 *
 * @author Bleno Vale
 */
public class JpaOrganizacao extends OrganizacaoJpaController {

    public JpaOrganizacao(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Organizacao findOrganizacaoByName(String name){
        try{
            EntityManager entityManager = super.getEntityManager();
            return (Organizacao) entityManager.createQuery("SELECT o FROM Organizacao o Where o.nome =:name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch(Exception error){
            throw error;
        } 
    }
    
}
