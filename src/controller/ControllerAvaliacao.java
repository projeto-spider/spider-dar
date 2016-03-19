package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Avaliar;
import model.Criterio;
import settings.Facade;
import util.Request;
import util.Text;

/**
 *
 * @author Bleno Vale
 */
public class ControllerAvaliacao {

    private Facade facade = Facade.getInstance();

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
}
