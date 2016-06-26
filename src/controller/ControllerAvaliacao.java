package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Alternativa;
import model.Avaliar;
import model.Criterio;
import model.Nota;
import settings.Auxiliar;
import settings.Facade;
import settings.KeepData;
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

            List<Avaliar> listAvaliar = facade.initializeJpaAvaliacao().findAvaliarByIdAlternativa(idAlternativa);

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

    public List<Request> getRequestListFromAlternativa() {
        try {
            List<Request> requestList = new ArrayList<>();
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));
            List<Alternativa> listAlternativas = facade.initializeAlternativa().findAlternativasByProblema(idProblema);
            List<Auxiliar> listAuxiliar = new ArrayList<>();
            Auxiliar aux;
            DecimalFormat decimal = new DecimalFormat("#0.0");

            for (Alternativa another : listAlternativas) {

                float satisfacao = calculateSatisfacaoAndRanking(another.getId());

                aux = new Auxiliar();
                aux.setId(another.getId());
                aux.setAlternativa(another.getNome());
                aux.setSatifacao(satisfacao);

                listAuxiliar.add(aux);
            }

            Collections.sort(listAuxiliar);

            int cont = 1;
            for (Auxiliar auxiliar : listAuxiliar) {
                Map<String, String> data = new HashMap<>();
                data.put("Avaliacao.alternativa.id", String.valueOf(auxiliar.getId()));
                data.put("Avaliacao.alternativa.nome", auxiliar.getAlternativa());
                data.put("Avaliacao.satisfacao", decimal.format(auxiliar.getSatifacao()) + "%");
                data.put("Avaliacao.posicao", String.valueOf(cont++));
                requestList.add(new Request(data));
            }

            return requestList;
        } catch (Exception error) {
            throw error;
        }
    }

    private float calculateSatisfacaoAndRanking(int idAlternativa) {
        try {
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));
            List<Avaliar> avaliarList = facade.initializeJpaAvaliacao().findAvaliarByIdAlternativa(idAlternativa);
            List<Criterio> criterioList = facade.initializeJpaCriterio().findCriterioByIdProblema(idProblema);
            List<Integer> higherNotas = higherNotas(criterioList);

            if (!avaliarList.isEmpty()) {
                float sumGivenNotas = 0;
                float sumhigherNotas = 0;
                //Soma todas as notas dadas na avaliação com os pesos de cada critério.
                //Soma a maior nota de cada criterio com o seu peso.
                for (int i = 0; i < avaliarList.size(); i++) {
                    sumGivenNotas = sumGivenNotas + (avaliarList.get(i).getValor() * criterioList.get(i).getPeso());
                    sumhigherNotas = sumhigherNotas + (higherNotas.get(i) * criterioList.get(i).getPeso());
                }

                float satisfacao = (sumGivenNotas / sumhigherNotas) * 100;

                return satisfacao;
            } else {
                return 0;
            }

        } catch (Exception error) {
            throw error;
        }
    }

    private List<Integer> higherNotas(List<Criterio> criterioList) {
        try {
            List<Integer> list = new ArrayList<>();
            for (Criterio criterio : criterioList) {
                int higher = 0;
                for (Nota nota : criterio.getNotaList()) {
                    if (higher < nota.getNota()) {
                        higher = nota.getNota();
                    }
                }
                list.add(higher);
            }
            return list;
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
