package controller;

import java.util.Date;
import model.Usuario;
import settings.Facade;
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
}
