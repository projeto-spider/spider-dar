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
import jpa.extension.JpaProblema;
import model.Problema;
import settings.Facade;
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
            
            jpaProblema.create(problema);
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
            int idProblema = Integer.parseInt(request.getDataInput("Problema.id").getValor());
            
            Problema problema = facade.initializeJpaProblema().findProblema(idProblema);
            
            problema.setNome(request.getDataInput("Problema.nome").getValor());
            problema.setProposito(request.getDataInput("Problema.proposito").getValor());
            problema.setPlanejamento(request.getDataInput("Problema.planejamento").getValor());
            problema.setContexto(request.getDataInput("Problema.contexto").getValor());
            problema.setModified(new Date());
            
            facade.initializeJpaProblema().edit(problema);
         }
        catch (Exception e)
        {
            throw new Exception(this.getExceptionMessage(e, "editar"), e);
        }
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
                problemas = this.facade.initializeJpaProblema().listProblemasByNomeOuCodigo(request);
            
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
//            data.put("Problema.codigo", problema.getCodigo());
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
//            data.put("Problema.codigo", problema.getCodigo());
            data.put("Problema.contexto", problema.getContexto());
            data.put("Problema.planejamento", problema.getPlanejamento());
            data.put("Problema.proposito", problema.getProposito());
            data.put("Problema.created", Text.formatDateForTable(problema.getCreated()));
            data.put("Problema.modified", Text.formatDateForTable(problema.getModified()));

            return new Request(data);
        } catch (Exception error) {
            Map<String, String> data = new HashMap<>();
            data.put("Error.mensagem", "Erro inesperado.");
            return new Request(data);
        }
    }
    
}