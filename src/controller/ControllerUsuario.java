package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.JOptionPane;
import model.Acessar;
import model.Organizacao;
import model.Perfil;
import model.Problema;
import model.Usuario;
import settings.Facade;
import settings.KeepData;
import util.Criptografia;
import util.MyEmail;
import util.Request;
import util.Text;

/**
 *
 * @author Géssica
 */
public class ControllerUsuario {

    private Usuario usuario;
    private final Facade facade = Facade.getInstance();
    private final Criptografia criptografia = new Criptografia();

    /**
     *
     * @param request
     * @param listAcesso
     * @param usuarioNome
     * @return
     */
    public boolean createUsuario(Request request, List<Request> listAcesso, String usuarioNome) {
        try {

            usuario = facade.initializeJpaUsuario().findUsuarioByNome(request.getData("Usuario.nome"));
            if (usuario != null) {
                return false;
            }

            usuario = new Usuario();
            usuario.setNome(request.getData("Usuario.nome"));
            usuario.setLogin(request.getData("Usuario.login"));
            usuario.setEmail(request.getData("Usuario.email"));
            usuario.setSenha(request.getData("Usuario.senha"));
            usuario.setCreated(new Date());
            usuario.setModified(new Date());

            facade.initializeJpaUsuario().create(usuario);

            int idOrg = Integer.parseInt(request.getData("Organizacao.id"));
            createAcessarUsuario(listAcesso, usuarioNome, idOrg);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean createAcessarUsuario(List<Request> list, String usuarioNome, int idOrg) {
        try {
            for (Request request : list) {
                Organizacao organizacao = facade.initializeJpaOrganizacao().findOrganizacaoByID(idOrg);
                Perfil perfil = facade.initializeJpaPefil().findPerfilByNameAndIdOrg(request.getData("Perfil.nome"), idOrg);
                usuario = facade.initializeJpaUsuario().findUsuarioByNome(usuarioNome);

                //Alterar isto quando problema for implementado.
                //Problema problema = facade.initializeJpaProblema().findProblema(1);
                Problema problema = facade.initializeJpaProblema().findProblemasByNameANDIdOrg(request.getData("Problema.nome"), idOrg);

                Acessar acessar = new Acessar();
                acessar.setOrganizacao(organizacao);
                acessar.setPerfil(perfil);
                acessar.setUsuario(usuario);
                acessar.setIdProblema(problema);

                facade.initializeJpaAcessa().create(acessar);
            }
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean updateUsuario(Request request) {
        try {
            int id = Integer.parseInt(request.getData("Usuario.id"));
            usuario = new Usuario();
            usuario = facade.initializeJpaUsuario().findUsuario(id);
            usuario.setNome(request.getData("Usuario.nome"));
            usuario.setLogin(request.getData("Usuario.login"));
            usuario.setEmail(request.getData("Usuario.email"));
            if (!request.getData("Usuario.senha").isEmpty()) {
                usuario.setSenha(request.getData("Usuario.senha"));
            }
            usuario.setModified(new Date());
            facade.initializeJpaUsuario().edit(usuario);

            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    public boolean updateFirstAccess(Request request) {
        try {
            int id = Integer.parseInt(request.getData("Usuario.id"));
            usuario = new Usuario();
            usuario = facade.initializeJpaUsuario().findUsuario(id);
            usuario.setLogin(request.getData("Usuario.login"));
            usuario.setEmail(request.getData("Usuario.email"));
            usuario.setSenha(request.getData("Usuario.senha"));

            usuario.setModified(new Date());
            facade.initializeJpaUsuario().edit(usuario);

            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    public boolean updateUsuarioByADM(Request request, List<Request> list) {
        try {
            int idUser = Integer.parseInt(request.getData("Usuario.id"));
            usuario = facade.initializeJpaUsuario().findAnotherPerfilWithSameName(request.getData("Usuario.nome"), idUser);
            if (usuario != null) {
                return false;
            }

            usuario = new Usuario();
            usuario = facade.initializeJpaUsuario().findUsuario(idUser);
            usuario.setNome(request.getData("Usuario.nome"));
            usuario.setLogin(request.getData("Usuario.login"));
            usuario.setModified(new Date());
            facade.initializeJpaUsuario().edit(usuario);

            int idOrg = Integer.parseInt(request.getData("Organizacao.id"));
            removeAcessoUsuario(facade.initializeJpaAcessa().findAcessarUsuarioByOrganizacao(usuario.getNome(), idOrg));
            createAcessarUsuario(list, usuario.getNome(), idOrg);
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    private void removeAcessoUsuario(List<Acessar> listAcesso) {
        try {
            for (Acessar acessar : listAcesso) {
                facade.initializeJpaAcessa().destroy(acessar.getAcessarPK());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public boolean removeUsuario(String name) {
        try {

            usuario = facade.initializeJpaUsuario().findUsuarioByNome(name);
            removeAcessoUsuario(usuario.getAcessarList());
            facade.initializeJpaUsuario().destroy(usuario.getId());
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean hasAcessoDuplicate(List<Request> list, Request request2) {
        for (Request request1 : list) {
            if (request1.getData("Problema.nome").equals(request2.getData("Problema.nome"))) {
                return true;
            }
        }
        return false;
    }

    public List<Request> findUsuarios() {
        try {
            List<Usuario> usuarioList = facade.initializeJpaUsuario().findUsuarioEntities();
            List<Request> list = new ArrayList<>();

            for (Usuario user : usuarioList) {
                Map<String, String> data = new HashMap<>();
                data.put("Usuario.nome", user.getNome());
                data.put("Usuario.login", user.getLogin());
                data.put("Usuario.quantidadeProblemas", String.valueOf(user.getAcessarList().size()));
                data.put("Usuario.created", Text.formatDateForTable(user.getCreated()));

                list.add(new Request(data));
            }
            return list;
        } catch (Exception error) {
            throw error;
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

    public List<Request> findUsuarioBySearch(String name) {
        try {
            List<Usuario> list = facade.initializeJpaUsuario().findUsuarioByPartName(name);
            List<Request> requestList = new ArrayList<>();

            for (Usuario user : list) {
                Map<String, String> data = new HashMap<>();
                data.put("Usuario.nome", user.getNome());
                data.put("Usuario.login", user.getLogin());
                data.put("Usuario.quantidadeProblemas", String.valueOf(user.getAcessarList().size()));
                data.put("Usuario.created", Text.formatDateForTable(user.getCreated()));

                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

    public Request findUsuarioByNome(String name) {
        try {
            usuario = facade.initializeJpaUsuario().findUsuarioByNome(name);

            Map<String, String> data = new HashMap<>();
            data.put("Usuario.id", String.valueOf(usuario.getId()));
            data.put("Usuario.nome", usuario.getNome());
            data.put("Usuario.login", usuario.getLogin());
            data.put("Usuario.email", usuario.getEmail());
            data.put("Usuario.senha", usuario.getSenha());
            data.put("Usuario.created", Text.formatDateForTable(usuario.getCreated()));
            data.put("Usuario.modified", Text.formatDateForTable(usuario.getModified()));

            return new Request(data);
        } catch (Exception error) {
            Map<String, String> data = new HashMap<>();
            data.put("Error.mensagem", "Erro inesperado.");
            return new Request(data);
        }
    }

    public Request findUsuarioById(int id) {
        try {
            usuario = facade.initializeJpaUsuario().findUsuario(id);

            Map<String, String> data = new HashMap<>();
            data.put("Usuario.id", String.valueOf(usuario.getId()));
            data.put("Usuario.nome", usuario.getNome());
            data.put("Usuario.login", usuario.getLogin());
            data.put("Usuario.email", usuario.getEmail());
            data.put("Usuario.senha", usuario.getSenha());
            data.put("Usuario.created", Text.formatDateForTable(usuario.getCreated()));
            data.put("Usuario.modified", Text.formatDateForTable(usuario.getModified()));

            return new Request(data);
        } catch (Exception error) {
            Map<String, String> data = new HashMap<>();
            data.put("Error.mensagem", "Erro inesperado.");
            return new Request(data);
        }
    }

    public List<Request> findAcessoByUsuario(String name, int idOrg) {
        try {
            List<Acessar> list = facade.initializeJpaAcessa().findAcessarUsuarioByOrganizacao(name, idOrg);
            List<Request> requestList = new ArrayList<>();

            for (Acessar acessar : list) {
                Map<String, String> data = new HashMap<>();
                data.put("Organizacao.nome", acessar.getPerfil().getNome());
                data.put("Problema.nome", acessar.getIdProblema().getNome());
                data.put("Perfil.nome", acessar.getPerfil().getNome());

                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            return null;
        }
    }

    public boolean isADM(String name) {
        return findUsuarioByNome(name).getData("Usuario.id").equals("1");
    }

    public Request findUsuarioByLogin(String loginUsuario) {
        try {
            usuario = new Usuario();
            usuario = facade.initializeJpaUsuario().findUsuarioByLogin(loginUsuario);

            Map<String, String> data = new HashMap<>();
            data.put("Usuario.id", String.valueOf(usuario.getId()));
            data.put("Usuario.nome", usuario.getNome());
            data.put("Usuario.login", usuario.getLogin());
            data.put("Usuario.email", usuario.getEmail());
            data.put("Usuario.senha", usuario.getSenha());
            data.put("Usuario.created", Text.formatDateForTable(usuario.getCreated()));
            data.put("Usuario.modified", Text.formatDateForTable(usuario.getModified()));

            return new Request(data);
        } catch (Exception error) {
            throw error;
        }
    }

    public boolean CompareSenhaTypedWithBD(String senha_usuario, String senha_digitada) {
        try {
            if (senha_usuario.equals(criptografia.encryptMessage(senha_digitada))) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta.");
                return false;
            }
        } catch (Exception error) {
            throw error;
        }
    }

    public boolean validateEmail(String email) {
        try {
            String regular_expression = "[A-Za-z0-9\\._-]+@[A-Za-z0-9]+(\\.[A-Za-z]+)+(\\.[A-Za-z]+)*";
            return email.matches(regular_expression);
        } catch (Exception error) {
            throw error;
        }
    }

    public void editUsuario(Usuario usuario) {
        try {
            facade.initializeJpaUsuario().edit(usuario);
            JOptionPane.showMessageDialog(null, "Dados Salvos com sucesso.");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro ao editar Usuário.");
            error.printStackTrace();
        }
    }

    public boolean existRegisteredEmail(String email) {
        try {
            List<Usuario> listaUsuario = facade.initializeJpaUsuario().findUsuarioByEmail(email);
            if (!listaUsuario.isEmpty()) {
                createNewPassword(listaUsuario.get(0));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "O e-mail não é Cadastrado.");
                return false;
            }
        } catch (Exception error) {
            throw error;
        }

    }
    
    public void createNewPassword(Usuario usuario) {
        try {
            //cria string aleatória de 6 digitos.
            UUID uuid = UUID.randomUUID();
            String newPassword = uuid.toString().substring(0, 6);
            
            MyEmail myEmail =  new MyEmail();
            myEmail.SendNewPassword(usuario.getEmail(), usuario.getNome(), newPassword);

            String newPasswordCrip = criptografia.encryptMessage(newPassword);
            usuario.setSenha(newPasswordCrip);

            facade.initializeJpaUsuario().edit(usuario);
        } catch (Exception error) {
            error.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro inesperado.");
        }
    }

    public List<Request> getPerfisByIdOrganizacao(int idOrg) {
        try {
            List<Perfil> list = facade.initializeJpaPefil().findPerfisByIdOrganizacao(idOrg);
            List<Request> requestList = new ArrayList<>();

            for (Perfil anotherPerfil : list) {
                Map<String, String> data = new HashMap<>();
                data.put("Usuario.nome", anotherPerfil.getNome());
                data.put("Usuario.created", Text.formatDateForTable(anotherPerfil.getCreated()));

                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> getProblemasByIdOrganizacao(int idOrg) {
        try {
            List<Problema> list = facade.initializeJpaProblema().findProblemasByIdOrganizacao(idOrg);
            List<Request> requestList = new ArrayList<>();

            for (Problema problema : list) {
                Map<String, String> data = new HashMap<>();
                data.put("Problema.nome", problema.getNome());
                data.put("Problema.created", Text.formatDateForTable(problema.getCreated()));

                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }
}
