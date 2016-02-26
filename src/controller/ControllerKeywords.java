/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import jpa.extension.JpaKeyword;
import jpa.extension.JpaProblema;
import model.Keyword;
import model.Problema;
import settings.Facade;
import util.Request;

/**
 *
 * @author Iuri Raiol
 */
public class ControllerKeywords
{
    private Keyword Keyword = new Keyword();
    private final Facade facade = Facade.getInstance();
    
    public void addKeyword(Request request) throws Exception
    {
        try
        {

            JpaKeyword jpaKeyword = facade.initializeJpaKeyword();
            JpaProblema jpaProblema = facade.initializeJpaProblema();

            int idProblema = Integer.parseInt(request.getData("Problema.id"));

            Problema problema = jpaProblema.findProblema(idProblema);

            Keyword.setNome(request.getData("Keyword.nome"));
            Keyword.setProblema(problema);
            Keyword.setCreated(new Date());
            Keyword.setModified(new Date());

            jpaKeyword.create(Keyword);
        }
        catch (Exception e)
        {
            throw new Exception(getExceptionMessage(e, "cadastrar"), e);
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
}