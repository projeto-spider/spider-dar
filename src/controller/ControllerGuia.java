package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Guia;
import model.Itemguia;
import settings.Facade;
import settings.KeepData;
import util.Request;

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

    public void createItemGuia(List<Request> listRequest) {
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
        } catch (Exception error) {
        }
    }

    public Request findGuia(int idOrganizacao) {
        try {
            guia = new Guia();
            guia = facade.initializeJpaOrganizacao().findOrganizacao(idOrganizacao).getGuiaList().get(0);

            Map<String, String> data = new HashMap<>();
            data.put("Guia.id", String.valueOf(guia.getId()));
            return new Request(data);
        } catch (Exception error) {
            throw error;
        }
    }
}
