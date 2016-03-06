package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.NotaJpaController;
import model.Nota;

/**
 *
 * @author Bleno Vale
 */
public class JpaNotas extends NotaJpaController {

    public JpaNotas(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Nota> findNotaByIdCriterio(int idCriterio) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Nota> list = entityManager
                    .createQuery("SELECT n FROM Nota n WHERE n.idCriterio.id =:idCriterio")
                    .setParameter("idCriterio", idCriterio)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return list;
        } catch (Exception error) {
            throw error;
        }
    }

    public Nota findNotaByNome(String nome, int idCriterio) {
        Nota nota =  null;
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            nota = (Nota) entityManager
                    .createQuery("SELECT n FROM Nota n WHERE n.descricao =:nome AND n.idCriterio.id =:idCriterio")
                    .setParameter("nome", nome)
                    .setParameter("idCriterio", idCriterio)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return nota;
        } catch (Exception error) {
            return nota;
        }
    }

}
