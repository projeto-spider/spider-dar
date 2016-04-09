/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jpa.extension.JpaAcessar;
import model.Acessar;
import settings.Facade;
import settings.KeepData;
import util.Request;

/**
 *
 * @author Iuri Raiol
 */
public class ControllerAcessar {

    private Acessar acessar = new Acessar();
    private final Facade facade = Facade.getInstance();

    public List<Acessar> findUsuariosPerfisByIdProblema(Request request) {
        JpaAcessar jpaAcessar = facade.initializeJpaAcessa();

        String idProblema = request.getData("Problema.id");

        List<Acessar> listAcessar = jpaAcessar.findUsuariosPerfilFromAcessarByIdProblema(Integer.parseInt(idProblema));

        return listAcessar;
    }

    public Request findPerfilByUser(int idUser) {
        Map<String, String> data = new HashMap<>();
        if (idUser == 1) {
            data.put("Perfil.nome", "Administrador-spiderDAR");
            data.put("Perfil.id", "1");
        } else {
            JpaAcessar jpaAcessar = facade.initializeJpaAcessa();
            int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));

            List<Acessar> listAcessar = jpaAcessar.findPerfilUser(idUser, idProblema);
            data.put("Perfil.nome", listAcessar.get(0).getPerfil().getNome());
            data.put("Perfil.id", String.valueOf(listAcessar.get(0).getPerfil().getId()));

        }
        return new Request(data);
    }
}
