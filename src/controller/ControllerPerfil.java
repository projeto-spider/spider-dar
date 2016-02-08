package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
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
            perfil = facade.initializeJpaPefil().findPerfilByNameAndIdOrg(request.getData("Perfil.nome"), idOrg);
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

    public boolean updatePerfil(Request request) {
        try {
            int id = Integer.parseInt(request.getData("Perfil.id"));

            perfil = facade.initializeJpaPefil()
                    .findAnotherPerfilWithSameName(request.getData("Perfil.nome"), id);
            if (perfil != null) {
                return false;
            }

            perfil = new Perfil();
            perfil = facade.initializeJpaPefil().findPerfilByID(id);
            perfil.setNome(request.getData("Perfil.nome"));
            perfil.setHabilidades(request.getData("Perfil.habilidades"));
            perfil.setCompetencias(request.getData("Perfil.competencias"));
            perfil.setModified(new Date());

            facade.initializeJpaPefil().edit(perfil);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean deletePerfil(String name) {
        try {
            perfil = new Perfil();
            perfil = facade.initializeJpaPefil().findPerfilByName(name);
            facade.initializeJpaPefil().destroy(perfil.getId());
            return true;
        } catch (NonexistentEntityException | IllegalOrphanException error) {
            return false;
        } catch(Exception error){
            return false;
        }
    }

    public List<Request> getPerfisByIdOrganizacao(int idOrg) {
        try {
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

    public Request findPerfilSelected(String name) {
        try {
            perfil = new Perfil();
            perfil = facade.initializeJpaPefil().findPerfilByName(name);

            Map<String, String> data = new HashMap<>();
            data.put("Perfil.id", String.valueOf(perfil.getId()));
            data.put("Perfil.nome", perfil.getNome());
            data.put("Perfil.habilidades", perfil.getHabilidades());
            data.put("Perfil.competencias", String.valueOf(perfil.getCompetencias()));
            data.put("Perfil.created", String.valueOf(perfil.getCreated()));
            data.put("Perfil.modified", String.valueOf(perfil.getModified()));
            return new Request(data);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> findFuncionalidadesByPerfil(String name) {
        try {
            int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
            perfil = facade.initializeJpaPefil().findPerfilByNameAndIdOrg(name, idOrg);

            List<Request> requestList = new ArrayList<>();
            List<Funcionalidades> listInPerfil = perfil.getFuncionalidadesList();
            List<Funcionalidades> listOutPerfil = facade.initializeJpaFuncionalidades().findFuncionalidadesEntities();

            for (int i = 0; i < listInPerfil.size(); i++) {

                for (int j = 0; j < listOutPerfil.size(); j++) {
                    if (Objects.equals(listInPerfil.get(i), listOutPerfil.get(j))) {
                        listOutPerfil.remove(listOutPerfil.get(j));
                    }
                }

                Map<String, String> data = new HashMap<>();
                data.put("Funcionalidade.dentro.nome", listInPerfil.get(i).getNome());
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

    public boolean saveFuncionalidades(String name, Request request) {
        try {
            int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
            perfil = facade.initializeJpaPefil().findPerfilByNameAndIdOrg(name, idOrg);

            List<Funcionalidades> listOutPerfil = facade.initializeJpaFuncionalidades().findFuncionalidadesEntities();
            List<Funcionalidades> listInPerfil = new ArrayList<>();
            int size = Integer.parseInt(request.getData("Funcionalidade.quantidade"));

            for (int i = 0; i < listOutPerfil.size(); i++) {
                for (int j = 0; j < size; j++) {
                    if (listOutPerfil.get(i).getNome().equals(request.getData("Funcionalidade.nome" + j))) {
                        listInPerfil.add(listOutPerfil.get(i));
                    }
                }
            }

            perfil.setFuncionalidadesList(listInPerfil);
            facade.initializeJpaPefil().edit(perfil);

            return true;
        } catch (Exception error) {
            System.out.println("Error: " + error);
            return false;
        }
    }
}
