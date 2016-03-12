package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.Criterio;
import model.Historico;
import model.Nota;
import settings.Constant;
import settings.Facade;
import settings.KeepData;
import util.Request;
import util.Text;

/**
 *
 * @author Bleno Vale
 */
public class ControllerCriterios {

    Criterio criterio;
    Historico historico;
    Facade facade = Facade.getInstance();

    public void addCriterio(Request request, List<Request> notas) throws Exception {
        try {
            int idProblema = Integer.parseInt(request.getDataInput("Problema.id").getValor());

            criterio = new Criterio();
            criterio.setIdProblema(facade.initializeJpaProblema().findProblema(idProblema));
            criterio.setNome(request.getDataInput("Criterio.nome").getValor());
            int Peso = Integer.parseInt(request.getDataInput("Criterio.peso").getValor());
            criterio.setPeso(Peso);
            criterio.setJustificativa(request.getDataInput("Criterio.justificativa").getValor());
            criterio.setCreated(new Date());
            criterio.setModified(new Date());

            facade.initializeJpaCriterio().create(criterio);

            addNotas(notas, criterio);

            historico = new Historico();
            historico.setDescricao("Critério \"" + criterio.getNome() + "\" foi Cadastrada.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setTipo(Constant.FUC_CRITERIOS);
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(facade.initializeJpaProblema().findProblema(idProblema));

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Cadastrar"), error);
        }
    }

    public void updateCriterio(Request request, List<Request> notas) throws Exception {
        try {
            int idCriterio = Integer.parseInt(request.getDataInput("Criterio.id").getValor());

            criterio = new Criterio();
            criterio = facade.initializeJpaCriterio().findCriterio(idCriterio);

            criterio.setNome(request.getDataInput("Criterio.nome").getValor());
            int Peso = Integer.parseInt(request.getDataInput("Criterio.peso").getValor());
            criterio.setPeso(Peso);
            criterio.setJustificativa(request.getDataInput("Criterio.justificativa").getValor());
            criterio.setModified(new Date());

            facade.initializeJpaCriterio().edit(criterio);

            addNotas(notas, criterio);

            historico = new Historico();
            historico.setDescricao("Critério \"" + criterio.getNome() + "\" foi Editado.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setTipo(Constant.FUC_CRITERIOS);
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(criterio.getIdProblema());

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Editar"), error);
        }
    }

    private void addNotas(List<Request> notas, Criterio ct) {
        try {
            if (ct.getId() == null) {
                criterio = facade.initializeJpaCriterio().findlastestCriterioCreated(ct.getIdProblema().getId());
            } else {
                criterio = facade.initializeJpaCriterio().findCriterio(ct.getId());
            }
            
            for (Nota nota : criterio.getNotaList()) {
                facade.initializeJpaNota().destroy(nota.getId());
            }

            Nota nota;

            for (Request aux : notas) {
                nota = facade.initializeJpaNota().findNotaByNome(aux.getData("Nota.nome"), criterio.getId());
                if (nota == null) {
                    nota = new Nota();
                    nota.setDescricao(aux.getData("Nota.nome"));
                    nota.setNota(Integer.parseInt(aux.getData("Nota.valor")));
                    nota.setIdCriterio(criterio);
                    nota.setCreated(new Date());
                    nota.setModified(new Date());
                    facade.initializeJpaNota().create(nota);
                }
            }

        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void removerNota(int idNota) {
        try {
            facade.initializeJpaNota().destroy(idNota);
        } catch (Exception error) {
        }
    }

    public void removeCriterio(int idCriterio) throws Exception {
        try {

            criterio = new Criterio();
            criterio = facade.initializeJpaCriterio().findCriterio(idCriterio);

            for (Nota nota : criterio.getNotaList()) {
                facade.initializeJpaNota().destroy(nota.getId());
            }

            facade.initializeJpaCriterio().destroy(idCriterio);

            historico = new Historico();
            historico.setDescricao("Critério \"" + criterio.getNome() + "\" foi Deletado.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setTipo(Constant.FUC_CRITERIOS);
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(criterio.getIdProblema());

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Deletar"), error);
        }
    }

    public List<Request> listCriteirosByProjeto(int idProblema) {
        try {
            List<Criterio> list = facade.initializeJpaCriterio().findCriterioByIdProblema(idProblema);
            return getRequestListCriterios(list, idProblema);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> listCriteirosBySearch(String nome, int idProblema) {
        try {
            List<Criterio> list = facade.initializeJpaCriterio().findCriterioByPartnomeIdProblema(nome, idProblema);
            return getRequestListCriterios(list, idProblema);
        } catch (Exception error) {
            throw error;
        }
    }

    private List<Request> getRequestListCriterios(List<Criterio> list, int idProblema) {
        try {
            int total = sumTotal(idProblema);

            List<Request> requestList = new ArrayList<>();
            for (Criterio another : list) {
                Map<String, String> data = new HashMap<>();

                data.put("Criterio.id", another.getId().toString());
                data.put("Criterio.nome", another.getNome());
                data.put("Criterio.justificativa", another.getJustificativa());
                data.put("Criterio.peso", String.valueOf(another.getPeso()));
                data.put("Criterio.porcentagem", String.valueOf(getPorcentagem(another.getPeso(), total)));
                data.put("Criterio.created", Text.formatDateForTable(another.getCreated()));
                data.put("Criterio.modified", Text.formatDateForTable(another.getModified()));
                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

    private int sumTotal(int idProblema) {
        int total = 0;
        List<Criterio> list = facade.initializeJpaCriterio().findCriterioByIdProblema(idProblema);
        for (Criterio ctr : list) {
            total = total + ctr.getPeso();
        }
        return total;
    }

    private double getPorcentagem(int peso, int total) {
        double percent = (100 * peso / total);
        return percent;
    }

    public Request getCriterioSelected(int idCriterio) {
        try {
            criterio = new Criterio();
            criterio = facade.initializeJpaCriterio().findCriterio(idCriterio);

            Map<String, String> data = new HashMap<>();
            data.put("Criterio.id", criterio.getId().toString());
            data.put("Criterio.nome", criterio.getNome());
            data.put("Criterio.justificativa", criterio.getJustificativa());
            data.put("Criterio.peso", String.valueOf(criterio.getPeso()));
            data.put("Criterio.created", Text.formatDateForTable(criterio.getCreated()));
            data.put("Criterio.modified", Text.formatDateForTable(criterio.getModified()));
            return new Request(data);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> listNotasByCriterio(int idCriterio) {
        try {
            List<Request> requestlist = new ArrayList<>();
            List<Nota> notas = new ArrayList<>();
            notas = facade.initializeJpaNota().findNotaByIdCriterio(idCriterio);
            for (Nota another : notas) {
                Map<String, String> data = new HashMap<>();

                data.put("Nota.id", another.getId().toString());
                data.put("Nota.nome", another.getDescricao());
                data.put("Nota.valor", String.valueOf(another.getNota()));
                data.put("Nota.created", Text.formatDateForTable(another.getCreated()));
                data.put("Nota.modified", Text.formatDateForTable(another.getModified()));
                requestlist.add(new Request(data));
            }

            return requestlist;
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
