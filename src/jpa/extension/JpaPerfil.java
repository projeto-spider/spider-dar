package jpa.extension;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.PerfilJpaController;
import model.Perfil;

/**
 *
 * @author Bleno Vale
 */
public class JpaPerfil extends PerfilJpaController {

    public JpaPerfil(EntityManagerFactory emf) {
        super(emf);
    }

    /**
     * Busca Perfil pelo nome.
     *
     * @param name
     * @return
     */
    public Perfil findPerfilByName(String name) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Perfil perfil = (Perfil) entityManager
                    .createQuery("SELECT p FROM Perfil p WHERE p.nome =:name")
                    .setParameter("name", name)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return perfil;
        } catch (Exception error) {
            return null;
        }
    }
    
//    public List<Perfil> findPerfilByProblema(int idProblema){
//        try {
//            
//        } catch (Exception error) {
//        }
//    }

}
