package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Avaliar;
import settings.Facade;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ControllerAvaliacao {

    private Facade facade = Facade.getInstance();

    public void saveAvaliar(List<Request> requestList) throws Exception {
        try {
            Avaliar avaliar;
            for (Request request : requestList) {
                avaliar = new Avaliar();

                int idAlternativa = Integer.parseInt(request.getData("Alternativa.id"));
                int idCriterio = Integer.parseInt(request.getData("Criterio.id"));
                avaliar = facade.initializeJpaAvaliacao().findAvaliarByIdCriterioAndIdAlternativa(idAlternativa, idCriterio);

                if (avaliar == null) {
                    addAvaliar(request);
                } else {
                    updateAvaliar(request, avaliar);
                }
            }
        } catch (Exception error) {
            throw new Exception(this.getExceptionMessage(error, "Salvar"), error);
        }
    }

    private void addAvaliar(Request request) {
        try {
            Avaliar avaliar = new Avaliar();

            int idAlternativa = Integer.parseInt(request.getData("Alternativa.id"));
            avaliar.setIdAlternativa(facade.initializeAlternativa().findAlternativa(idAlternativa));

            int idCriterio = Integer.parseInt(request.getData("Criterio.id"));
            avaliar.setIdCriterio(facade.initializeJpaCriterio().findCriterio(idCriterio));

            avaliar.setNota(request.getData("Avaliar.nome"));

            int value = facade.initializeJpaNota().findNotaByNome(request.getData("Avaliar.nome"), idCriterio).getNota();
            avaliar.setValor(value);

            avaliar.setCreated(new Date());
            avaliar.setModified(new Date());

            facade.initializeJpaAvaliacao().create(avaliar);
        } catch (Exception error) {
            throw error;
        }
    }

    private void updateAvaliar(Request request, Avaliar avaliar) throws Exception {
        try {
            int idCriterio = Integer.parseInt(request.getData("Criterio.id"));

            avaliar.setNota(request.getData("Avaliar.nome"));

            int value = facade.initializeJpaNota().findNotaByNome(request.getData("Avaliar.nome"), idCriterio).getNota();
            avaliar.setValor(value);

            avaliar.setModified(new Date());

            facade.initializeJpaAvaliacao().edit(avaliar);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Request> listAvaliacaoByAlternativa(int idAlternativa) {
        try {

            List<Avaliar> listAvaliar = facade.initializeJpaAvaliacao().findAvaliaryIdAlternativa(idAlternativa);

            return getRequesListtAvaliar(listAvaliar);
        } catch (Exception error) {
            throw error;
        }
    }

    private List<Request> getRequesListtAvaliar(List<Avaliar> list) {
        try {
            List<Request> requestList = new ArrayList<>();

            for (Avaliar another : list) {
                Map<String, String> data = new HashMap<>();

                data.put("Criterio.id", String.valueOf(another.getIdCriterio().getId()));
                data.put("Criterio.nome", another.getIdCriterio().getNome());
                data.put("Avaliacao.nota", another.getNota());

                requestList.add(new Request(data));
            }
            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

    public Request getAvaliar(int idAlternativa, int idCriterio) {
        try {

            Avaliar avaliar = facade.initializeJpaAvaliacao().findAvaliarByIdCriterioAndIdAlternativa(idAlternativa, idCriterio);
            Map<String, String> data = new HashMap<>();
            if (avaliar != null) {
                data.put("Criterio.id", String.valueOf(avaliar.getIdCriterio().getId()));
                data.put("Criterio.nome", avaliar.getIdCriterio().getNome());
                data.put("Avaliacao.nota", avaliar.getNota());
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
