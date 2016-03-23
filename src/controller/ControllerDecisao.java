package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Decisao;
import model.Historico;
import settings.Constant;
import settings.Facade;
import settings.KeepData;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ControllerDecisao {

    private final Facade facade = Facade.getInstance();

    public void saveDecisao(Request request) throws Exception {
        try {
            int idAlternativa = Integer.parseInt(request.getData("Alternativa.id"));
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));

            Decisao decisao = new Decisao();
            decisao.setIdProblema(idProblema);
            decisao.setIdAlternativa(facade.initializeAlternativa().findAlternativa(idAlternativa));
            decisao.setJustificativa(request.getData("Decisao.justificativa"));
            decisao.setDefinitivo(false);
            decisao.setCreated(new Date());
            decisao.setModified(new Date());

            facade.initializeJpaDecisao().create(decisao);

            Historico historico = new Historico();
            historico.setDescricao("Alternativa \"" + decisao.getIdAlternativa().getNome() + "\" foi Escolhida como decisão.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setTipo(Constant.FUC_AVALIACAO);
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(facade.initializeJpaProblema().findProblema(idProblema));

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Cadastrar"), error);
        }
    }

    public void updateDecisao(Request request) throws Exception {
        try {
            int idAlternativa = Integer.parseInt(request.getData("Alternativa.id"));
            int idDecisao = Integer.parseInt(request.getData("Decisao.id"));

            Decisao decisao = new Decisao();
            decisao = facade.initializeJpaDecisao().findDecisao(idDecisao);
            decisao.setIdAlternativa(facade.initializeAlternativa().findAlternativa(idAlternativa));
            decisao.setJustificativa(request.getData("Decisao.justificativa"));
            decisao.setModified(new Date());

            facade.initializeJpaDecisao().edit(decisao);

            Historico historico = new Historico();
            historico.setDescricao("Decisão \"" + decisao.getIdAlternativa().getNome() + "\" foi editada.");
            historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
            historico.setTipo(Constant.FUC_AVALIACAO);
            historico.setCreated(new Date());
            historico.setModified(new Date());
            historico.setIdProblema(facade.initializeJpaProblema().findProblema(decisao.getIdProblema()));

            facade.initializeHistorico().create(historico);
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Cadastrar"), error);
        }
    }

    public Request findDecisao() {
        try {
            Decisao decisao = new Decisao();
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));
            decisao = facade.initializeJpaDecisao().findDecisaoByProblema(idProblema);
            Map<String, String> data = new HashMap<>();
            if (decisao != null) {
                data.put("Decisao.id", String.valueOf(decisao.getId()));
                data.put("Decisao.alternativa", decisao.getIdAlternativa().getNome());
                data.put("Decisao.alternativa.id", String.valueOf(decisao.getIdAlternativa().getId()));
                data.put("Decisao.justificativa", decisao.getJustificativa());
            }

            return new Request(data);
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
