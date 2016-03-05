package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.Historico;
import model.Tarefa;
import settings.Constant;
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
            historico.setTipo(Constant.FUC_TAREFAS); 
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(facade.initializeJpaProblema().findProblema(idProblema));

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Cadastrar"), error);
        }
    }

    public boolean isValidDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date now = calendar.getTime();

        if (date.compareTo(now) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public void updateTarefa(Request request, Date date) throws Exception {
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
            historico.setTipo(Constant.FUC_TAREFAS); 
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(tarefa.getIdProblema());

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Editar"), error);
        }
    }

    public boolean upDateStatusTarefa(int idTarefa, boolean status) {
        try {
            tarefa = new Tarefa();
            tarefa = facade.initializeTarefa().findTarefa(idTarefa);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date now = calendar.getTime();

            if (tarefa.getData().compareTo(now) < 0) {
                JOptionPane.showMessageDialog(null, "O Prazo expirou.");
                return tarefa.getFeito();
            }

            tarefa.setFeito(status);
            tarefa.setModified(new Date());

            facade.initializeTarefa().edit(tarefa);
            return status;
        } catch (Exception error) {
            return false;
        }
    }

    public void removeTarefa(int idTarefa) throws Exception {
        try {
            tarefa = new Tarefa();
            tarefa = facade.initializeTarefa().findTarefa(idTarefa);

            facade.initializeTarefa().destroy(tarefa.getId());

            historico = new Historico();
            historico.setDescricao("Tarefa \"" + tarefa.getNome() + "\" foi Excluída.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setTipo(Constant.FUC_TAREFAS); 
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

    public List<Request> listTarefasBySearch(String nome, boolean isFeito, boolean isEmAndamento, int idProjeto) {
        try {
            List<Tarefa> list = new ArrayList<>();

            if (isFeito && isEmAndamento == false) {
                list = facade.initializeTarefa().findTarefaByPartNomeAndFeitoAndIdProjeto(nome, true, idProjeto);
            } else if (isFeito  == false && isEmAndamento) {
                list = facade.initializeTarefa().findTarefaByPartNomeAndFeitoAndIdProjeto(nome, false, idProjeto);
            } else {
                list = facade.initializeTarefa().findTarefaByPartNomeAndIdProjeto(nome, idProjeto);
            }

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
