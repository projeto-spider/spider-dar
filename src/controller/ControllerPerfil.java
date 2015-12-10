package controller;

import java.util.Date;
import model.Perfil;
import settings.Facade;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ControllerPerfil {

    private Perfil perfil = new Perfil();
    private final Facade facade = Facade.getInstance();

    /**
     * Método responsavel pelo cadastro de um novo Perfil.
     *
     * @param request dados requisistados do perfil.
     * @return se cadastro foi sucedido ou não.
     */
    public boolean createPerfil(Request request) {
        try {
            perfil = facade.initializeJpaPefil().findPerfilByName(request.getData("Perfil.nome"));
            if (perfil != null) {
                return false;
            }

            perfil = new Perfil();
            perfil.setNome(request.getData("Perfil.nome"));
            perfil.setHabilidades(request.getData("Perfil.habilidades"));
            perfil.setCompetencias(request.getData("Perfil.competencias"));
            perfil.setCreated(new Date());
            perfil.setModified(new Date());

            facade.initializeJpaPefil().create(perfil);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}
