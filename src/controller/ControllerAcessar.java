/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import jpa.extension.JpaAcessar;
import model.Acessar;
import settings.Facade;
import util.Request;

/**
 *
 * @author Iuri Raiol
 */
public class ControllerAcessar
{
    private Acessar acessar = new Acessar();
    private final Facade facade = Facade.getInstance();
    
    public List<Acessar> findUsuariosPerfisByIdProblema(Request request)
    {
        JpaAcessar jpaAcessar = facade.initializeJpaAcessa();
        
        String idProblema = request.getData("Problema.id");
        
        List<Acessar> listAcessar = jpaAcessar.findUsuariosPerfilFromAcessarByIdProblema(Integer.parseInt(idProblema));
        
        List<Request> requestList = new ArrayList<>();
        
        return  listAcessar;
    }
}
