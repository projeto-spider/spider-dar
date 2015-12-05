package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Organizacao;
import settings.Facade;
import util.Request;
import util.Text;

/**
 *
 * @author Bleno Vale
 */
public class ControllerOrganizacao {

    private Organizacao organizacao;
    private final Facade facade = Facade.getInstance();

    /**
     * Método responsavel pelo cadastro de uma nova Organização.
     *
     * @param request
     * @return se cadastro foi sucedido ou não.
     */
    public boolean createdNewOrganização(Request request) {
        try {
            organizacao = new Organizacao();
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

    public List<Request> findOrganizacoes() {
        try {
            List<Organizacao> list = facade.initializeOrganizacaoJpa().findOrganizacaoEntities();
            List<Request> requestList = new ArrayList<>();

            for (Organizacao org : list) {
                Map<String, String> data = new HashMap<>();
                data.put("Organizacao.nome", org.getNome());
                data.put("Organizacao.created", Text.formatDateForTable(org.getCreated()));
                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }
}
