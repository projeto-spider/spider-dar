package settings;

import jpa.OrganizacaoJpaController;

/**
 * Implementação do padrão de projeto Facade.
 * padrão de projeto Facade fornecer uma interface unificada para um 
 * conjunto de interfaces em um subsistema. Facade define uma interface 
 * de nível mais alto que torna o subsistema mais fácil de ser usado.
 * 
 * @author Bleno Vale
 */
public class Facade {

    private static Facade instance = null;

    private OrganizacaoJpaController organizacaoJpaController;

    private Facade() {

    }

    /**
     * Implementação do padrão de projeto Singleton.
     * O padrão de projeto Singleton garanti que a classe possua uma única 
     * instancia de sí mesma. Ou seja, a classe gerencia a criação de sua 
     * instancia e fornece um ponto global de acesso para a mesma.
     *
     * @return
     */
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public OrganizacaoJpaController initializeOrganizacaoJpa() {
        organizacaoJpaController = new OrganizacaoJpaController(Connection.connect());
        return organizacaoJpaController;
    }

}
