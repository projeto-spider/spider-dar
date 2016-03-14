package controller;

import java.util.ArrayList;
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
    
    private Facade facade =  Facade.getInstance();
    
    public List<Request> listAvaliacaoByAlternativa(String idAlternativa){
        try {
            int id = Integer.parseInt(idAlternativa);
            
            List<Avaliar> listAvaliar = facade.initializeJpaAvaliacao().findAlternativasByProblema(id);
            
            return getRequestListFromAvaliar(listAvaliar);
        } catch (Exception error) {
            throw error;
        }
    }
    
        private List<Request> getRequestListFromAvaliar(List<Avaliar> listAvaliar) {
        try {
            List<Request> requestList = new ArrayList<>();

            for (Avaliar another : listAvaliar) {
                Map<String, String> data = new HashMap<>();

                data.put("Avaliar .id", another.getId().toString());

                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }
}
