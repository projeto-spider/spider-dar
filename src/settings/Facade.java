package settings;

import jpa.ItemguiaJpaController;
import jpa.extension.JpaAcessar;
import jpa.extension.JpaAlternativa;
import jpa.extension.JpaAvaliacao;
import jpa.extension.JpaConfiguracoes;
import jpa.extension.JpaCriterio;
import jpa.extension.JpaDecisao;
import jpa.extension.JpaFuncionalidades;
import jpa.extension.JpaGuia;
import jpa.extension.JpaHistorico;
import jpa.extension.JpaKeyword;
import jpa.extension.JpaNotas;
import jpa.extension.JpaOrganizacao;
import jpa.extension.JpaPerfil;
import jpa.extension.JpaProblema;
import jpa.extension.JpaTarefa;
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

    public Facade() {

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

    public ItemguiaJpaController initializeJpaItemGuia() {
        return new ItemguiaJpaController(Connection.connect());
    }

    public JpaFuncionalidades initializeJpaFuncionalidades() {
        return new JpaFuncionalidades(Connection.connect());
    }

    public JpaUsuario initializeJpaUsuario() {
        return new JpaUsuario(Connection.connect());
    }

    public JpaAcessar initializeJpaAcessa() {
        return new JpaAcessar(Connection.connect());
    }

    public JpaProblema initializeJpaProblema() {
        return new JpaProblema(Connection.connect());
    }

    public JpaAlternativa initializeAlternativa() {
        return new JpaAlternativa(Connection.connect());
    }

    public JpaHistorico initializeHistorico() {
        return new JpaHistorico(Connection.connect());
    }

    public JpaTarefa initializeTarefa() {
        return new JpaTarefa(Connection.connect());
    }

    public JpaConfiguracoes initializeJpaConfiguracoes() {
        return new JpaConfiguracoes(Connection.connect());
    }

    public JpaKeyword initializeJpaKeyword() {
        return new JpaKeyword(Connection.connect());
    }

    public JpaCriterio initializeJpaCriterio() {
        return new JpaCriterio(Connection.connect());
    }

    public JpaNotas initializeJpaNota() {
        return new JpaNotas(Connection.connect());
    }
    
    public JpaAvaliacao initializeJpaAvaliacao() {
        return new JpaAvaliacao(Connection.connect());
    }
    
    public JpaDecisao initializeJpaDecisao(){
        return new JpaDecisao(Connection.connect()); 
    }
}
