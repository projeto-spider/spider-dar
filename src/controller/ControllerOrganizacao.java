package controller;

import java.util.Date;
import model.Organizacao;
import settings.Facade;

/**
 *
 * @author Bleno Vale
 */
public class ControllerOrganizacao {

    private final Facade facade = Facade.getInstance();

    /**
     * Método responsavel pelo cadastro de uma nova Organização.
     * @param organizacao que será cadastrada.
     * @return se cadastro foi sucedido ou não.
     */
    public boolean createdNewOrganização(Organizacao organizacao) {
        try {
            organizacao.setCreated(new Date());
            organizacao.setModified(new Date());
            
            facade.initializeOrganizacaoJpa().create(organizacao);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}
