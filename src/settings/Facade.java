package settings;

import jpa.ItemguiaJpaController;
import jpa.extension.JpaFuncionalidades;
import jpa.extension.JpaGuia;
import jpa.extension.JpaOrganizacao;
import jpa.extension.JpaPerfil;
import jpa.extension.JpaUsuario;

/**
 * Implementação do padrão de projeto Facade. padrão de projeto Facade fornecer
 * uma interface unificada para um conjunto de interfaces em um subsistema.
 * Facade define uma interface de nível mais alto que torna o subsistema mais
 * fácil de ser usado.
 *
 * @author Bleno Vale
 */
public class Facade {

    private static Facade instance = null;

    private JpaOrganizacao jpaOrganizacao;

    private Facade() {

    }

    /**
     * Implementação do padrão de projeto Singleton. O padrão de projeto
     * Singleton garanti que a classe possua uma única instancia de sí mesma. Ou
     * seja, a classe gerencia a criação de sua instancia e fornece um ponto
     * global de acesso para a mesma.
     *
     * @return
     */
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public JpaOrganizacao initializeJpaOrganizacao() {
        jpaOrganizacao = new JpaOrganizacao(Connection.connect());
        return jpaOrganizacao;
    }

    public JpaPerfil initializeJpaPefil() {
        return new JpaPerfil(Connection.connect());
    }

    public JpaGuia initializeJpaGuia() {
        return new JpaGuia(Connection.connect());
    }
    
    public ItemguiaJpaController initializeJpaItemGuia(){
        return new ItemguiaJpaController(Connection.connect());
    }
    
    public JpaFuncionalidades initializeJpaFuncionalidades(){
        return new JpaFuncionalidades(Connection.connect()); 
    }
    
    public JpaUsuario initializeJpaUsuario(){
        return new JpaUsuario(Connection.connect()); 
    }

}
