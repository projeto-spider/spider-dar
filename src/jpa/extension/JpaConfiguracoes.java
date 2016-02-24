package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.ConfiguracoesJpaController;
import model.Configuracoes;

/**
 *
 * @author Géssica
 */
public class JpaConfiguracoes extends ConfiguracoesJpaController {
    
    public JpaConfiguracoes(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<Configuracoes> findListConfiguracoesById(int id) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Configuracoes> list = entityManager.createQuery("SELECT c FROM Configuracoes c WHERE c.id =:id ORDER BY c.id ASC")
                    .setParameter("id", id)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }
    
}
