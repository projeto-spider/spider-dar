package controller;

import java.util.Date;
import java.util.List;
import model.Acessar;
import model.Organizacao;
import model.Perfil;
import model.Problema;
import model.Usuario;
import settings.Facade;
import settings.KeepData;
import util.Request;

/**
 *
 * @author Géssica
 */
public class ControllerUsuario {

    private Usuario usuario;
    private final Facade facade = Facade.getInstance();

    public boolean createUsuario(Request request) {
        try {
            usuario = new Usuario();
            usuario.setNome(request.getData("Usuario.nome"));
            usuario.setLogin(request.getData("Usuario.login"));
            usuario.setEmail(request.getData("Usuario.email"));
            usuario.setSenha(request.getData("Usuario.senha"));
            usuario.setCreated(new Date());
            usuario.setModified(new Date());

            facade.initializeJpaUsuario().create(usuario);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean createAcessarOfUsuario(List<Request> list, String usuarioNome) {
        try {
            int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
            for (Request request : list) {
                Organizacao organizacao = facade.initializeJpaOrganizacao().findOrganizacaoByID(idOrg);
                Perfil perfil = facade.initializeJpaPefil().findPerfilByNameAndIdOrg(request.getData("Perfil.nome"), idOrg);
                usuario = facade.initializeJpaUsuario().findUsuarioByNome(usuarioNome);
                
                //Alterar isto quando problema for implementado.
                Problema problema = facade.initializeJpaProblema().findProblema(1);               
                
                Acessar acessar = new Acessar();
                acessar.setOrganizacao(organizacao);
                acessar.setPerfil(perfil);
                acessar.setUsuario(usuario);
                acessar.setProblema(problema);

                facade.initializeJpaAcessa().create(acessar);
            }
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public List<Request> findPerfisHasntUsuario(List<Request> requestList) {
        int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
        List<Perfil> perfisList = facade.initializeJpaPefil().findPerfisByIdOrganizacao(idOrg);

        for (Request request : requestList) {
            for (Perfil perfil : perfisList) {
                if (request.getData("Problema.nome").equals(perfil.getNome())) {
                    requestList.remove(request);
                }
            }
        }
        return requestList;
    }
}
