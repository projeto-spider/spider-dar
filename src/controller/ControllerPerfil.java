package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.Funcionalidades;
import model.Perfil;
import settings.Facade;
import settings.KeepData;
import util.Request;
import util.Text;

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
            int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
            perfil = facade.initializeJpaPefil().findPerfilByNameAndNome(request.getData("Perfil.nome"), idOrg);
            if (perfil != null) {
                return false;
            }

            perfil = new Perfil();
            perfil.setIdOrganizacao(facade.initializeJpaOrganizacao().findOrganizacao(idOrg));
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

    public List<Request> findPerfis() {
        try {
            int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
            List<Perfil> list = facade.initializeJpaPefil().findPerfisByIdOrganizacao(idOrg);
            List<Request> requestList = new ArrayList<>();

            for (Perfil anotherPerfil : list) {
                Map<String, String> data = new HashMap<>();
                data.put("Perfil.nome", anotherPerfil.getNome());
                data.put("Perfil.created", Text.formatDateForTable(anotherPerfil.getCreated()));

                if (anotherPerfil.getCreated().equals(anotherPerfil.getModified())) {
                    data.put("Perfil.modified", "--");
                } else {
                    data.put("Perfil.modified", Text.formatDateForTable(anotherPerfil.getModified()));
                }

                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> findFuncionalidadesByPerfil(String name) {
        try {
            int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
            perfil = facade.initializeJpaPefil().findPerfilByNameAndNome(name, idOrg);

            List<Request> requestList = new ArrayList<>();
            List<Funcionalidades> listInPerfil = perfil.getFuncionalidadesList();
            List<Funcionalidades> listOutPerfil = facade.initializeJpaFuncionalidades().findFuncionalidadesEntities();

            for (Funcionalidades inPerfil : listInPerfil) {
                Map<String, String> data = new HashMap<>();

                for (Funcionalidades outPerfil : listOutPerfil) {
                    if (Objects.equals(inPerfil, outPerfil)) {
                        listOutPerfil.remove(outPerfil);
                    }
                }

                data.put("Funcionalidade.dentro.nome", inPerfil.getNome());
                requestList.add(new Request(data));
            }

            for (Funcionalidades outPerfil : listOutPerfil) {
                Map<String, String> data = new HashMap<>();
                data.put("Funcionalidade.fora.nome", outPerfil.getNome());
                requestList.add(new Request(data));
            }
            
            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }
}
