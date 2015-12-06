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
            organizacao = facade.initializeJpaOrganizacao().findOrganizacaoByName(request.getData("Organizacao.nome"));
            if (organizacao != null) {
                return false;
            }

            organizacao = new Organizacao();
            organizacao.setNome(request.getData("Organizacao.nome"));
            organizacao.setDescricao(request.getData("Organizacao.descricao"));
            organizacao.setCreated(new Date());
            organizacao.setModified(new Date());

            facade.initializeJpaOrganizacao().create(organizacao);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    /**
     * Método responsavel pelo cadastro de uma nova Organização.
     *
     * @param request
     * @return se cadastro foi sucedido ou não.
     */
    public boolean updatedNewOrganização(Request request) {
        try {
            int id = Integer.parseInt(request.getData("Organizacao.id"));

            organizacao = facade.initializeJpaOrganizacao()
                    .findAnotherOrganizacaoWithSameName(request.getData("Organizacao.nome"), id);
            if (organizacao != null) {
                return false;
            }

            organizacao = new Organizacao();
            organizacao = facade.initializeJpaOrganizacao().findOrganizacaoByID(id);
            organizacao.setNome(request.getData("Organizacao.nome"));
            organizacao.setDescricao(request.getData("Organizacao.descricao"));
            organizacao.setModified(new Date());

            facade.initializeJpaOrganizacao().edit(organizacao);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    /**
     * Busca todas as organizações cadastradas anteriormente.
     *
     * @return uma lista contendo os dados requisitados de cada Organização.
     */
    public List<Request> findOrganizacoes() {
        try {
            List<Organizacao> list = facade.initializeJpaOrganizacao().findOrganizacaoEntities();
            List<Request> requestList = new ArrayList<>();

            for (Organizacao org : list) {
                Map<String, String> data = new HashMap<>();
                data.put("Organizacao.nome", org.getNome());
                data.put("Organizacao.created", Text.formatDateForTable(org.getCreated()));

                if (org.getCreated().equals(org.getModified())) {
                    data.put("Organizacao.modified", "--");
                } else {
                    data.put("Organizacao.modified", Text.formatDateForTable(org.getModified()));
                }

                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

    /**
     * Busca a organização pelo seu nome.
     *
     * @param name
     * @return dados da organização.
     */
    public Request findOrganizacaoSelected(String name) {
        try {
            organizacao = new Organizacao();
            organizacao = facade.initializeJpaOrganizacao().findOrganizacaoByName(name);

            Map<String, String> data = new HashMap<>();
            data.put("Organizacao.id", String.valueOf(organizacao.getId()));
            data.put("Organizacao.nome", organizacao.getNome());
            data.put("Organizacao.descricao", organizacao.getDescricao());
            return new Request(data);
        } catch (Exception error) {
            throw error;
        }
    }
}
