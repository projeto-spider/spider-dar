package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.AcessarJpaController;
import model.Acessar;

/**
 *
 * @author Bleno Vale
 */
public class JpaAcessar extends AcessarJpaController {

    public JpaAcessar(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Acessar> findAcessarUsuarioByOrganizacao(String userName, int idOrg) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Acessar> list = entityManager
                    .createQuery("SELECT a FROM Acessar a WHERE a.usuario.nome =:name AND a.organizacao.id =:idOrg")
                    .setParameter("name", userName).setParameter("idOrg", idOrg)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }

}
