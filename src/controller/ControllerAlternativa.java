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
            throw new Exception(this.getExceptionMessage(error, "Cadastrar"), error);
        }
    }

    public void upDatelternativa(Request request, int idAlternativa) throws Exception {
        try {

            alternativa = new Alternativa();
            alternativa =  facade.initializeAlternativa().findAlternativa(idAlternativa);
            alternativa.setNome(request.getDataInput("Alternativa.nome").getValor());
            alternativa.setDescricao(request.getDataInput("Alternativa.descricao").getValor());
            alternativa.setCusto(request.getDataInput("Alternativa.estimativaCusto").getValor());
            alternativa.setTempo(request.getDataInput("Alternativa.estimativaTempo").getValor());
            alternativa.setModified(new Date());

            facade.initializeAlternativa().edit(alternativa);

            historico = new Historico();
            historico.setDescricao("Alternativa \"" + alternativa.getNome() + "\" Editada.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(facade.initializeJpaProblema().findProblema(alternativa.getIdProblema().getId()));

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Editar"), error);
        }
    }
    
    public List<Request> listAlternativasByProblema() {
        try {
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));

            List<Alternativa> listAlternativa = facade.initializeAlternativa().findAlternativasByProblema(idProblema);

            return getRequestListFromAlternativa(listAlternativa);
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
                data.put("Alternativa.modified", Text.formatDateForTable(another.getModified()));

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

    public Request getAlternativaSelected(int idAlternativa) {
        try {
            alternativa = new Alternativa();
            alternativa = facade.initializeAlternativa().findAlternativa(idAlternativa);

            Map<String, String> data = new HashMap<>();
            data.put("Alternativa.id", alternativa.getId().toString());
            data.put("Alternativa.nome", alternativa.getNome());
            data.put("Alternativa.descricao", alternativa.getDescricao());
            data.put("Alternativa.estimativaCusto", alternativa.getCusto());
            data.put("Alternativa.estimativaTempo", alternativa.getTempo());
            data.put("Alternativa.created", Text.formatDateForTable(alternativa.getCreated()));
            data.put("Alternativa.modified", Text.formatDateForTable(alternativa.getModified()));
            
            return new Request(data);
        } catch (Exception error) {
            throw error;
        }
    }

    public void deletAlternativa(int idAlternativa) throws Exception{
        try {
            facade.initializeAlternativa().destroy(idAlternativa); 
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Excluir"), error);
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
