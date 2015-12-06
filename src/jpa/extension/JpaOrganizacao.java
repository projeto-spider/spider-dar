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

    public Organizacao findOrganizacaoByName(String name) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Organizacao) entityManager.createQuery("SELECT o FROM Organizacao o WHERE o.nome =:name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception error) {
            return null;
        }
    }

    /**
     * Usado na edição de uma organização. Busca saber se o nome da organização
     * editado já existe.
     *
     * @param name
     * @param id
     * @return
     */
    public Organizacao findAnotherOrganizacaoWithSameName(String name, int id) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Organizacao) entityManager.createQuery("SELECT o FROM Organizacao o WHERE o.nome =:name AND o.id <>:id")
                    .setParameter("name", name).setParameter("id", id)
                    .getSingleResult();
        } catch (Exception error) {
            return null;
        }
    }

    public Organizacao findOrganizacaoByID(int id) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Organizacao) entityManager.createQuery("SELECT o FROM Organizacao o WHERE o.id =:id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception error) {
            throw error;
        }
    }

}
