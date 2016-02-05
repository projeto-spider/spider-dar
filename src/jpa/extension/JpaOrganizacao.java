package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.OrganizacaoJpaController;
import model.Organizacao;
import settings.KeepData;

/**
 *
 * @author Bleno Vale
 */
public class JpaOrganizacao extends OrganizacaoJpaController {

    public JpaOrganizacao(EntityManagerFactory emf) {
        super(emf);
    }

    /**
     * Busca organização pelo nome.
     *
     * @param name
     * @return
     */
    public Organizacao findOrganizacaoByName(String name) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Organizacao organizacao = (Organizacao) entityManager
                    .createQuery("SELECT o FROM Organizacao o WHERE o.nome =:name")
                    .setParameter("name", name)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return organizacao;
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
            entityManager.getTransaction().begin();

            Organizacao organizacao = (Organizacao) entityManager
                    .createQuery("SELECT o FROM Organizacao o WHERE o.nome =:name AND o.id <>:id")
                    .setParameter("name", name).setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return organizacao;
        } catch (Exception error) {
            return null;
        }
    }

    /**
     * Busca organização pelo id.
     *
     * @param id
     * @return
     */
    public Organizacao findOrganizacaoByID(int id) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Organizacao organizacao = (Organizacao) entityManager
                    .createQuery("SELECT o FROM Organizacao o WHERE o.id =:id")
                    .setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return organizacao;
        } catch (Exception error) {
            throw error;
        }
    }

    /**
     * Busca uma lista de Organizações por parte do nome.
     *
     * @param name
     * @return lista de organizações.
     */
    public List<Organizacao> findOrganizacoesByPartName(String name) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Organizacao> list = entityManager
                    .createQuery("SELECT o FROM Organizacao o WHERE o.nome LIKE :name ORDER BY o.nome ASC")
                    .setParameter("name", name + "%")
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Organizacao> findOrganizacoesByUsuarioId(int idUser)
    {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            //int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));

            List<Organizacao> list = entityManager
                    .createQuery("SELECT a.organizacao FROM Acessar a WHERE a.usuario.id =:idUser")
                    .setParameter("idUser", idUser)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }

}
