package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Guia;
import model.Itemguia;
import settings.Facade;
import settings.KeepData;
import util.Request;
import util.Text;

/**
 *
 * @author Bleno Vale
 */
public class ControllerGuia {

    private Guia guia;
    private Itemguia item;
    private final Facade facade = Facade.getInstance();

    public boolean createGuia(Request request) {
        try {
            guia = new Guia();

            int id = Integer.parseInt(KeepData.getData("Organizacao.id"));
            guia.setIdOrganizacao(facade.initializeJpaOrganizacao().findOrganizacao(id));
            guia.setCaminhoGuia(request.getData("Guia.caminhoguia"));
            guia.setDescricao(request.getData("Guia.descricao"));
            guia.setCreated(new Date());
            guia.setModified(new Date());

            facade.initializeJpaGuia().create(guia);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean createItemGuia(List<Request> listRequest) {
        try {
            guia = new Guia();
            int id = Integer.parseInt(KeepData.getData("Organizacao.id"));
            guia = facade.initializeJpaOrganizacao().findOrganizacao(id).getGuiaList().get(0);

            for (Request request : listRequest) {
                item = new Itemguia();
                item.setIdGuia(guia);
                item.setTipoDescricao(request.getData("Itemguia.tipo"));
                item.setAplicacao(request.getData("Itemguia.aplicacao"));
                item.setDefinicao(request.getData("Itemguia.definicao"));
                item.setTipo(0);
                item.setCreated(new Date());
                item.setModified(new Date());
                facade.initializeItemGuia().create(item);
            }

            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean updateGuia(Request request) {
        try {
            guia = new Guia();

            int idGuia = Integer.parseInt(request.getData("Guia.id"));
            guia = facade.initializeJpaGuia().findGuia(idGuia);
            guia.setCaminhoGuia(request.getData("Guia.caminhoguia"));
            guia.setDescricao(request.getData("Guia.descricao"));
            guia.setModified(new Date());

            facade.initializeJpaGuia().edit(guia);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean updateItemGuia(List<Request> list) {
        try {
            guia = new Guia();
            int id = Integer.parseInt(KeepData.getData("Organizacao.id"));
            guia = facade.initializeJpaOrganizacao().findOrganizacao(id).getGuiaList().get(0);

            for (int i = 0; i < list.size(); i++) {
                item = new Itemguia();
                item = guia.getItemguiaList().get(i);
                item.setAplicacao(list.get(i).getData("Itemguia.aplicacao"));
                item.setDefinicao(list.get(i).getData("Itemguia.definicao"));
                item.setTipo(i);
                item.setModified(new Date());
                facade.initializeItemGuia().edit(item);
            }

            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public Request findGuia(int idOrganizacao) {
        try {
            guia = new Guia();
            guia = facade.initializeJpaOrganizacao().findOrganizacao(idOrganizacao).getGuiaList().get(0);

            Map<String, String> data = new HashMap<>();
            data.put("Guia.id", String.valueOf(guia.getId()));
            data.put("Guia.caminhoguia", guia.getCaminhoGuia());
            data.put("Guia.descricao", guia.getDescricao());
            data.put("Guia.created", Text.formatDate(guia.getCreated()));
            data.put("Guia.modified", Text.formatDate(guia.getModified()));
            return new Request(data);
        } catch (Exception error) {
            return null;
        }
    }

    public List<Request> findListItemGuia(int idGuia) {
        try {
            List<Itemguia> list = facade.initializeJpaGuia().findListItemGuiaByIdGuia(idGuia);
            List<Request> listRequest = new ArrayList<>();

            for (Itemguia itemguia : list) {
                Map<String, String> data = new HashMap<>();

                data.put("Itemguia.tipo", "Diretriz");
                data.put("Itemguia.definicao", itemguia.getDefinicao());
                data.put("Itemguia.aplicacao", itemguia.getAplicacao());
                listRequest.add(new Request(data));
            }
            return listRequest;
        } catch (Exception error) {
            throw error;
        }
    }
}
