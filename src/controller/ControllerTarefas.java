package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Historico;
import model.Tarefa;
import settings.Facade;
import settings.KeepData;
import util.Request;
import util.Text;

/**
 *
 * @author Bleno Vale
 */
public class ControllerTarefas {

    Tarefa tarefa;
    Historico historico;
    Facade facade = Facade.getInstance();

    public void addTarefa(Request request, Date date) throws Exception {
        try {
            int idProblema = Integer.parseInt(request.getDataInput("Problema.id").getValor());

            tarefa = new Tarefa();
            tarefa.setIdProblema(facade.initializeJpaProblema().findProblema(idProblema));
            tarefa.setNome(request.getDataInput("Tarefa.nome").getValor());
            tarefa.setDescricao(request.getDataInput("Tarefa.descricao").getValor());
            int marcador = Integer.parseInt(request.getDataInput("Tarefa.marcador").getValor());
            tarefa.setMarcador(marcador);
            tarefa.setFeito(false);
            tarefa.setData(date);
            tarefa.setCreated(new Date());
            tarefa.setModified(new Date());

            facade.initializeTarefa().create(tarefa);

            historico = new Historico();
            historico.setDescricao("Tarefa \"" + tarefa.getNome() + "\" foi Cadastrada.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(facade.initializeJpaProblema().findProblema(idProblema));

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Cadastrar"), error);
        }
    }

    public void upDateTarefa(Request request, Date date) throws Exception {
        try {
            int idTarefa = Integer.parseInt(request.getDataInput("Tarefa.id").getValor());

            tarefa = new Tarefa();
            tarefa = facade.initializeTarefa().findTarefa(idTarefa);
            
            tarefa.setNome(request.getDataInput("Tarefa.nome").getValor());
            tarefa.setDescricao(request.getDataInput("Tarefa.descricao").getValor());
            int marcador = Integer.parseInt(request.getDataInput("Tarefa.marcador").getValor());
            tarefa.setMarcador(marcador);
            tarefa.setData(date);
            tarefa.setModified(new Date());

            facade.initializeTarefa().edit(tarefa);

            historico = new Historico();
            historico.setDescricao("Tarefa \"" + tarefa.getNome() + "\" foi Editada.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(tarefa.getIdProblema());

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Cadastrar"), error);
        }
    }

    public void removeTarefa(int idTarefa) throws Exception{
        try {
            tarefa = new Tarefa(); 
            tarefa =  facade.initializeTarefa().findTarefa(idTarefa);
            
            facade.initializeTarefa().destroy(tarefa.getId());
            
            historico = new Historico();
            historico.setDescricao("Tarefa \"" + tarefa.getNome() + "\" foi Excluída.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(facade.initializeJpaProblema().findProblema(tarefa.getIdProblema().getId()));  
            
            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Excluir"), error);
        }
    }
    
    public List<Request> listTarefasByProjeto(int idProjeto) {
        try {
            List<Tarefa> list = facade.initializeTarefa().findTarefaByIdProblema(idProjeto);
            return getRequesytListTarefas(list);
        } catch (Exception error) {
            throw error;
        }
    }

    private List<Request> getRequesytListTarefas(List<Tarefa> list) {
        try {
            List<Request> requestList = new ArrayList<>();
            for (Tarefa another : list) {
                Map<String, String> data = new HashMap<>();

                data.put("Tarefa.id", another.getId().toString());
                data.put("Tarefa.nome", another.getNome());
                data.put("Tarefa.descricao", another.getDescricao());
                data.put("Tarefa.marcador", String.valueOf(another.getMarcador()));
                if (another.getFeito() == true) {
                    data.put("Tarefa.feito", "true");
                } else {
                    data.put("Tarefa.feito", "false");
                }
                data.put("Tarefa.data", Text.formatDateForTable(another.getData()));
                data.put("Tarefa.created", Text.formatDateForTable(another.getCreated()));
                data.put("Tarefa.modified", Text.formatDateForTable(another.getModified()));
                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

    public Request getTarefasSelected(int idTarefa) {
        try {
            tarefa = new Tarefa();
            tarefa = facade.initializeTarefa().findTarefa(idTarefa);

            Map<String, String> data = new HashMap<>();
            data.put("Tarefa.id", tarefa.getId().toString());
            data.put("Tarefa.nome", tarefa.getNome());
            data.put("Tarefa.descricao", tarefa.getDescricao());
            data.put("Tarefa.marcador", String.valueOf(tarefa.getMarcador()));
            if (tarefa.getFeito() == true) {
                data.put("Tarefa.feito", "true");
            } else {
                data.put("Tarefa.feito", "false");
            }
            data.put("Tarefa.data", Text.formatDateForTable(tarefa.getData()));
            data.put("Tarefa.created", Text.formatDateForTable(tarefa.getCreated()));
            data.put("Tarefa.modified", Text.formatDateForTable(tarefa.getModified()));
            return new Request(data);
        } catch (Exception error) {
            throw error;
        }
    }

    public Date getPrazoTarefaSelected(int idTarefa) {
        try {
            tarefa = new Tarefa();
            tarefa = facade.initializeTarefa().findTarefa(idTarefa);
            return tarefa.getData();
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
