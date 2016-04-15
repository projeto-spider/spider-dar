package settings;

/**
 *
 * @author Bleno Vale
 */
public class Constant {

    public static int CREATE = 0;
    public static int UPDATE = 1;

    // Guia de gestão de decisão
    public static int DIRETRIZES = 0;
    public static int PROBLEMA = 1;
    public static int CRITERIOS = 2;
    public static int ALTERNATIVAS = 3;
    public static int METODOS = 4;
    public static int AVALIAR = 5;
    public static int SOLUCOES = 6;

    public static int ID_ADMIN = 1;

    //Tarefas Marcadores
    public static int TRIVIAL = 1;
    public static int PEQUENO = 2;
    public static int MEDIO = 3;
    public static int GRANDE = 4;

    //Funcionalidades MARCADORES
    public static int FUC_PROBLEMA = 0;
    public static int FUC_MOTIVACOES = 1;
    public static int FUC_TAREFAS = 2;
    public static int FUC_ALTERNATIVAS = 3;
    public static int FUC_CRITERIOS = 4;
    public static int FUC_AVALIACAO = 5;
    public static int FUC_HISTORICO = 6;
    public static int FUC_RELATORIO = 7;
    
    //Status do Problema
    public static final int PROBLEMA_ATIVO = 1;
    public static final int PROBLEMA_INATIVO = 9;
    public static final int PROBLEMA_FINALIZADO = 2;
    
    //Status de Instalação da Ferramenta
    public static final int INSTALL_CLIENTE = 1;
    public static final int INSTALL_SERVIDOR = 2;
    
    //Status botões da instalação
    public static final int INSTALL_SELECT_INSTALLATION = 1;
    public static final int INSTALL_CONFIGDB = 2;
    public static final int INSTALL_CREATE_ADMIN = 3;
    public static final int INSTALL_CONFIG_EMAIL = 4;
    public static final int INSTALL_CANCEL = 8;
    public static final int INSTALL_FINISH = 9;
}