package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Alternativa;
import model.Historico;
import settings.Facade;
import settings.KeepData;
import util.Request;
import util.Text;

/**
 *
 * @author Bleno Vale
 */
public class ControllerAlternativa {

    private final Facade facade = Facade.getInstance();
    private Alternativa alternativa;
    private Historico historico;

    public void addAlternativa(Request request) throws Exception {
        try {

            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));

            alternativa = new Alternativa();
            alternativa.setNome(request.getDataInput("Alternativa.nome").getValor());
            alternativa.setDescricao(request.getDataInput("Alternativa.descricao").getValor());
            alternativa.setCusto(request.getDataInput("Alternativa.estimativaCusto").getValor());
            alternativa.setTempo(request.getDataInput("Alternativa.estimativaTempo").getValor());
            alternativa.setCreated(new Date());
            alternativa.setModified(new Date());
            alternativa.setIdProblema(facade.initializeJpaProblema().findProblema(idProblema));

            facade.initializeAlternativa().create(alternativa);

            historico = new Historico();
            historico.setDescricao("Alternativa \"" + alternativa.getNome() + "\" Cadastrada.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(facade.initializeJpaProblema().findProblema(idProblema));

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "cadastrar"), error);
        }
    }

    public List<Request> listAlternativasByProblema() {
        try {
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));

            List<Alternativa> list = facade.initializeAlternativa().findAlternativasByProblema(idProblema);

            return getRequestListFromAlternativa(list);
        } catch (Exception error) {
            throw error;
        }
    }

    private List<Request> getRequestListFromAlternativa(List<Alternativa> listAlternativas) {
        try {
            List<Request> requestList = new ArrayList<>();

            for (Alternativa another : listAlternativas) {
                Map<String, String> data = new HashMap<>();

                data.put("Alternativa.id", another.getId().toString());
                data.put("Alternativa.nome", another.getNome());
                data.put("Alternativa.descricao", another.getDescricao());
                data.put("Alternativa.estimativaCusto", another.getCusto());
                data.put("Alternativa.estimativaTempo", another.getTempo());
                data.put("Alternativa.created", Text.formatDateForTable(another.getCreated()));
                data.put("Alternativa.created", Text.formatDateForTable(another.getModified()));

                if (another.getCreated().equals(another.getModified())) {
                    data.put("Alternativa.modified", "--");
                }

                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

    private String getExceptionMessage(Exception e, String operacao) {
        String message;

        if (e.getMessage() != null) {
            message = e.getMessage();
        } else {
            message = "Ocorreu um problema ao " + operacao + ".";
        }

        return message;
    }
}
