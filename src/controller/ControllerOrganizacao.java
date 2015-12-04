package controller;

import java.util.Date;
import model.Organizacao;
import settings.Facade;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ControllerOrganizacao {

    private Organizacao organizacao;
    private final Facade facade = Facade.getInstance();

    /**
     * Método responsavel pelo cadastro de uma nova Organização.
     * @param request
     * @return se cadastro foi sucedido ou não.
     */
    public boolean createdNewOrganização(Request request) {
        try {
            organizacao =  new Organizacao();
            organizacao.setNome(request.getData("Organizacao.nome"));
            organizacao.setDescricao(request.getData("Organizacao.descricao"));
            organizacao.setCreated(new Date());
            organizacao.setModified(new Date());
            
            facade.initializeOrganizacaoJpa().create(organizacao);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}
