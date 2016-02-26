package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Historico;
import settings.Facade;
import settings.KeepData;
import util.Request;
import util.Text;

/**
 *
 * @author Bleno Vale
 */
public class ControllerHistorico {

    private Facade facade = Facade.getInstance();

    public List<Request> listHistoricoByProblema() {
        try {
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));

            List<Historico> listAlternativa = facade.initializeHistorico().findHistoricoByProblema(idProblema);

            return getRequestListFromAlternativa(listAlternativa);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> listHistoricoByPartDescricao(String descricao) {
        try {
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));

            List<Historico> listAlternativa = facade.initializeHistorico().findHistoricoByDescricao(descricao, idProblema);

            return getRequestListFromAlternativa(listAlternativa);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> listHistoricoBySearch(String descricao, Date dataInicio, Date dataFim, int tipo) {
        try {
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));
            List<Historico> listAlternativa = new ArrayList<>();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataInicio);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dataInicio = calendar.getTime();
            if (tipo == -1) {
                listAlternativa = facade.initializeHistorico().findHistoricoByPartDescricaoAndDates(descricao, idProblema, dataInicio, dataFim);
            } else {
                listAlternativa = facade.initializeHistorico().findHistoricoByPartDescricaoAndDatesAndType(descricao, idProblema, dataInicio, dataFim, tipo);
            }

            return getRequestListFromAlternativa(listAlternativa);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> listHistoricoBySearchDate(String descricao, Date dataInicio, Date dataFim) {
        try {
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));

            List<Historico> listAlternativa = facade.initializeHistorico().findHistoricoByPartDescricaoAndDates(descricao, idProblema, dataInicio, dataFim);

            return getRequestListFromAlternativa(listAlternativa);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> listHistoricoBySearchDateAndTipo(String descricao, Date dataInicio, Date dataFim, int tipo) {
        try {
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));

            List<Historico> listAlternativa = facade.initializeHistorico().findHistoricoByPartDescricaoAndDatesAndType(descricao, idProblema, dataInicio, dataFim, tipo);

            return getRequestListFromAlternativa(listAlternativa);
        } catch (Exception error) {
            throw error;
        }
    }

    private List<Request> getRequestListFromAlternativa(List<Historico> listHistorico) {
        try {
            List<Request> requestList = new ArrayList<>();

            for (Historico another : listHistorico) {
                Map<String, String> data = new HashMap<>();

                data.put("Historico.id", another.getId().toString());
                data.put("Historico.descricao", another.getDescricao());
                data.put("Historico.usuarioNome", another.getUsuarioNome());
                data.put("Historico.tipo", String.valueOf(another.getTipo()));
                data.put("Historico.created", Text.formatDateForTable(another.getCreated()));
                data.put("Historico.modified", Text.formatDateForTable(another.getModified()));
                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

}
