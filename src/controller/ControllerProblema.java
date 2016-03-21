/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import jpa.extension.JpaAlternativa;
import jpa.extension.JpaAvaliacao;
import jpa.extension.JpaCriterio;
import jpa.extension.JpaHistorico;
import jpa.extension.JpaKeyword;
import jpa.extension.JpaProblema;
import jpa.extension.JpaTarefa;
import model.Alternativa;
import model.Avaliar;
import model.Criterio;
import model.Historico;
import model.Problema;
import model.Tarefa;
import settings.Constant;
import settings.Facade;
import settings.KeepData;
import util.Input;
import util.Request;
import util.Text;

/**
 *
 * @author iuriraiol
 */
public class ControllerProblema
{
    private Problema problema = new Problema();
    private final Facade facade = Facade.getInstance();
    private Historico historico;
    
    public void addProblema(Request request) throws Exception 
    {
        try
        {
            JpaProblema jpaProblema = facade.initializeJpaProblema();
            int idOrganizacao = Integer.parseInt(request.getDataInput("Problema.idOrganizacao").getValor());
            
            problema.setNome(request.getDataInput("Problema.nome").getValor());
            problema.setProposito(request.getDataInput("Problema.proposito").getValor());
            problema.setPlanejamento(request.getDataInput("Problema.planejamento").getValor());
            problema.setContexto(request.getDataInput("Problema.contexto").getValor());
            problema.setIdOrganizacao(facade.initializeJpaOrganizacao().findOrganizacao(idOrganizacao));
            problema.setCreated(new Date());
            problema.setModified(new Date());
            
            Problema lastInsertedProblema = jpaProblema.createProblema(problema);
            String idProblema = String.valueOf(lastInsertedProblema.getId());
            HashMap<String,Input> fields = request.getAllHashMapDataInputs();
            
            //TODO: verificar o tamanho da JList pela jlist
            int listSize = 3;
            
            ControllerKeywords controllerKeywords = new ControllerKeywords();
            
            for (int keywordIndex = 0; keywordIndex < listSize; keywordIndex++)
            {
                for(Entry<String,Input> entry : fields.entrySet())
                {
                    if (entry.getKey().equals("Keyword." + keywordIndex + ".nome"))
                    {
                        Request requestKeyword = new Request();
                        requestKeyword.setData("Keyword.nome", entry.getValue().getValor());
                        requestKeyword.setData("Problema.id", idProblema);
                        
                        controllerKeywords.addKeyword(requestKeyword);
                    }
                }
            }
            
            createHistoricoProblema(Integer.parseInt(idProblema),"cadastrado");
        }
        catch(Exception e)
        {
            throw new Exception(this.getExceptionMessage(e, "cadastrar"), e);
        }
    }
    
    public void editProblema(Request request) throws Exception
    {
        try
        {
            JpaProblema jpaProblema = facade.initializeJpaProblema();
            JpaKeyword jpaKeyword = facade.initializeJpaKeyword();
            
            int idProblema = Integer.parseInt(request.getDataInput("Problema.id").getValor());
            Problema problema = jpaProblema.findProblema(idProblema);
            
            problema.setNome(request.getDataInput("Problema.nome").getValor());
            problema.setProposito(request.getDataInput("Problema.proposito").getValor());
            problema.setPlanejamento(request.getDataInput("Problema.planejamento").getValor());
            problema.setContexto(request.getDataInput("Problema.contexto").getValor());
            problema.setModified(new Date());
            
            jpaKeyword.deleteAllKeywordsByIdProblema(idProblema);
            
            jpaProblema.edit(problema);
            
            HashMap<String,Input> fields = request.getAllHashMapDataInputs();
            
            //TODO: verificar o tamanho da JList pela jlist
            int listSize = 3;
            
            ControllerKeywords controllerKeywords = new ControllerKeywords();
            
            for (int keywordIndex = 0; keywordIndex < listSize; keywordIndex++)
            {
                for(Entry<String,Input> entry : fields.entrySet())
                {
                    if (entry.getKey().equals("Keyword." + keywordIndex + ".nome"))
                    {
                        Request requestKeyword = new Request();
                        
                        requestKeyword.setData("Keyword.nome", entry.getValue().getValor());
                        requestKeyword.setData("Problema.id", String.valueOf(idProblema));
                        
                        controllerKeywords.addKeyword(requestKeyword);
                    }
                }
            }
            createHistoricoProblema(idProblema, "editado");
         }
        catch (Exception e)
        {
            throw new Exception(this.getExceptionMessage(e, "editar"), e);
        }
    }
    
    private void createHistoricoProblema(int idProblema, String acao)
    {
        historico = new Historico();
        Problema problema = facade.initializeJpaProblema().findProblema(idProblema);
        
        historico.setDescricao("Problema \"" + problema.getNome() + "\" foi " + acao +".");
        historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
        historico.setTipo(Constant.FUC_PROBLEMA);
        historico.setCreated(new Date());
        historico.setModified(new Date());
        historico.setIdProblema(problema);

        facade.initializeHistorico().create(historico);
    }
    
    public void removeProblemaById(String idProblemaString) throws Exception
    {
        try
        {
            int idProblema = Integer.parseInt(idProblemaString);
            
            if (hasProblemaTarefa(idProblema))
                throw new Exception(exceptionItemMessage("Tarefas"));
            
            if (hasProblemaAlternativas(idProblema))
                throw new Exception(exceptionItemMessage("Alternativas de Solução"));
            
            if (hasProblemaCriterios(idProblema))
                throw new Exception(exceptionItemMessage("Critérios de Avaliação"));
            
            JpaKeyword jpaKeyword = facade.initializeJpaKeyword();
            JpaHistorico jpaHistorico = facade.initializeHistorico();
            JpaProblema jpaProblema = facade.initializeJpaProblema();
            
            jpaKeyword.deleteAllKeywordsByIdProblema(idProblema);
            jpaHistorico.deleteAllHistoricoByIdProblema(idProblema);
            jpaProblema.destroy(idProblema);
            
        }
        catch (Exception e)
        {
           throw new Exception(e.getMessage());
        }
    }
    
    private String exceptionItemMessage(String itemString)
    {
        return "<html>Não é possível excluir, o Problema possui <b>" + itemString +"</b> vinculados(as) a ele.</html>";
    }
    
    private boolean hasProblemaTarefa(int idProblema)
    {
        JpaTarefa jpaTarefa = facade.initializeTarefa();
        
        List<Tarefa> listTarefa = jpaTarefa.findTarefaByIdProblema(idProblema);
        
        return (listTarefa.size() > 0);
    }
    
    private boolean hasProblemaAlternativas(int idProblema)
    {
        JpaAlternativa jpaAlternativa = facade.initializeAlternativa();
        
        List<Alternativa> listAlternativa = jpaAlternativa.findAlternativasByProblema(idProblema);
        
        return (listAlternativa.size() > 0);
    }
    
    private boolean hasProblemaCriterios(int idProblema)
    {
        JpaCriterio jpaCriterio = facade.initializeJpaCriterio();
        
        List<Criterio> listCriterio = jpaCriterio.findCriterioByIdProblema(idProblema);
        
        return (listCriterio.size() > 0);
    }
    
    private boolean hasProblemaAvaliacao(int idProbblema)
    {
        JpaAvaliacao jpaAvaliacao = facade.initializeJpaAvaliacao();
        
        return false;
    }
    
    private String getExceptionMessage(Exception e, String operacao)
    {
        String message;
            
        if (e.getMessage() != null)
            message = e.getMessage();
        else
            message = "Ocorreu um problema ao " + operacao + ".";
        
        return message;
    }
    
    public Request getProblemaById(String id)
    {
        try
        {
            int idProblema = Integer.parseInt(id);
            Problema problema = this.facade.initializeJpaProblema().findProblema(idProblema);
            
            Request returnRequest = new Request();
            
            returnRequest.setData("Problema.id", String.valueOf(problema.getId()));
            returnRequest.setData("Problema.nome", problema.getNome());
            returnRequest.setData("Problema.created", Text.formatDateForTable(problema.getCreated()));
            returnRequest.setData("Problema.modified", Text.formatDateForTable(problema.getModified()));
            
            return returnRequest;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    
    public Request getProblemaById(Request request)
    {
        try
        {
            int idProblema = Integer.parseInt(request.getData("Problema.id"));
            int idOrganizacao = Integer.parseInt(request.getData("Problema.idOrganizacao"));
            
            Problema problema = this.facade.initializeJpaProblema().findProblemaById(idProblema,idOrganizacao);

            HashMap<String,String> data = new HashMap<>();

            data.put("Problema.id", problema.getId().toString());
            data.put("Problema.nome", problema.getNome());
            data.put("Problema.proposito", problema.getProposito());
            data.put("Problema.planejamento", problema.getPlanejamento());
            data.put("Problema.contexto", problema.getContexto());

            return new Request(data);
        }
        catch (Exception error)
        {
            throw error;
        }
    }
    
    public List<Request> listAllProblemas()
    {
        try
        {
            List<Problema> problemas = this.facade.initializeJpaProblema().findProblemaEntities();
            
            return this.getRequestListFromProblemaList(problemas);
        }
        catch(Exception error)
        {
            throw error;
        }
    }
    
    public List<Request> listProblemasByPesquisa(Request request)
    {
        try
        {
            List<Problema> problemas;
            
            if (request.getData("Problema.busca").equals("")) 
                problemas = this.facade.initializeJpaProblema().listAllProblemasByIdOrganizacao(request);
            else
                problemas = this.facade.initializeJpaProblema().listProblemasByNomeCodigoOuKeyword(request);
            
            return this.getRequestListFromProblemaList(problemas);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public List<Request> listProblemasByIdOrganizacao(String idOrganizacao)
    {
        try
        {
            List<Problema> problemas = this.facade.initializeJpaProblema().findProblemasByIdOrganizacao(Integer.parseInt(idOrganizacao));

            return this.getRequestListFromProblemaList(problemas);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    
    private List<Request> getRequestListFromProblemaList(List<Problema> problemas)
    {
        List<Request> requestList = new ArrayList<>();
        
        for (Problema problema : problemas)
        {
            Map<String, String> data = new HashMap<>();

            data.put("Problema.id", problema.getId().toString());
            data.put("Problema.nome", problema.getNome());
            data.put("Problema.created", Text.formatDateForTable(problema.getCreated()));
            data.put("Problema.modified", Text.formatDateForTable(problema.getModified()));

            if (problema.getCreated().equals(problema.getModified()))
                data.put("Problema.modified", "--");

            requestList.add(new Request(data));
        }
        return requestList;
    }
    
     public Request findProblemaById(int id) {
        try {
            problema = facade.initializeJpaProblema().findProblema(id);

            Map<String, String> data = new HashMap<>();
            data.put("Problema.id", String.valueOf(problema.getId()));
            data.put("Problema.nome", problema.getNome());
            data.put("Problema.contexto", problema.getContexto());
            data.put("Problema.planejamento", problema.getPlanejamento());
            data.put("Problema.proposito", problema.getProposito());
            data.put("Problema.created", Text.formatDateForTable(problema.getCreated()));
            data.put("Problema.modified", Text.formatDateForTable(problema.getModified()));

            return new Request(data);
        } catch (Exception error)
        {
            Map<String, String> data = new HashMap<>();
            data.put("Error.mensagem", "Erro inesperado.");
            return new Request(data);
        }
    }
    
}