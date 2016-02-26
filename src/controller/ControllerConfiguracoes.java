package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Configuracoes;
import settings.Facade;
import util.Request;
import util.Text;

/**
 *
 * @author Géssica
 */
public class ControllerConfiguracoes {
    
    private Configuracoes configuracoes;
    private final Facade facade = Facade.getInstance();
    
    public boolean createConfiguracao(Request request) {
        try {
            configuracoes = new Configuracoes();

            configuracoes.setEmail(request.getData("Configuracoes.email"));
            configuracoes.setSenha(request.getData("Configuracoes.senha"));
            configuracoes.setPorta(request.getData("Configuracoes.porta"));
            configuracoes.setServidor(request.getData("Configuracoes.servidor"));
            configuracoes.setTipoCript(request.getData("Configuracoes.crip"));
            configuracoes.setCreated(new Date());
            configuracoes.setModified(new Date()); 

            facade.initializeJpaConfiguracoes().create(configuracoes);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
    
    public boolean updateConfiguracao(Request request) {
        try {
            configuracoes = new Configuracoes();
            configuracoes =  facade.initializeJpaConfiguracoes().findConfiguracoes(1);

            configuracoes.setEmail(request.getData("Configuracoes.email"));
            configuracoes.setSenha(request.getData("Configuracoes.senha"));
            configuracoes.setPorta(request.getData("Configuracoes.porta"));
            configuracoes.setServidor(request.getData("Configuracoes.servidor"));
            configuracoes.setTipoCript(request.getData("Configuracoes.crip"));
            configuracoes.setModified(new Date()); 

            facade.initializeJpaConfiguracoes().edit(configuracoes);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
    
    public Request findConfiguracao() {
        try {
            configuracoes = new Configuracoes();
            configuracoes =  facade.initializeJpaConfiguracoes().findConfiguracoes(1);

            Map<String, String> data = new HashMap<>();
            data.put("Configuracoes.id", String.valueOf(configuracoes.getId()));
            data.put("Configuracoes.email", configuracoes.getEmail());
            data.put("Configuracoes.porta", configuracoes.getPorta());
            data.put("Configuracoes.servidor", configuracoes.getServidor());
            data.put("Configuracoes.senha", configuracoes.getSenha());
            data.put("Configuracoes.crip", configuracoes.getTipoCript());
            data.put("Configuracoes.created", Text.formatDate(configuracoes.getCreated()));
            data.put("Configuracoes.modified", Text.formatDate(configuracoes.getModified()));
            return new Request(data);
        } catch (Exception error) {
            return null;
        }
    }
    
    public List<Request> findListConfiguracoes(int id) {
        try {
            List<Configuracoes> list = facade.initializeJpaConfiguracoes().findListConfiguracoesById(id);
            List<Request> listRequest = new ArrayList<>();

            for (Configuracoes configuracoes : list) {
                Map<String, String> data = new HashMap<>();

                data.put("Configuracoes.email", configuracoes.getEmail());
                data.put("Configuracoes.porta", configuracoes.getPorta());
                data.put("Configuracoes.senha", configuracoes.getSenha());
                data.put("Configuracoes.servidor", configuracoes.getServidor());
                data.put("Configuracoes.tipoEncript", configuracoes.getTipoCript());
                listRequest.add(new Request(data));
            }
            return listRequest;
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Request findConfiguracoesById(int idConfiguracoes) {
        try {
            configuracoes = new Configuracoes();
            configuracoes = facade.initializeJpaConfiguracoes().findConfiguracoes(idConfiguracoes);

            Map<String, String> data = new HashMap<>();
            data.put("Configuracoes.id", configuracoes.getId().toString());
            data.put("Configuracoes.email", configuracoes.getEmail());
            data.put("Configuracoes.porta", configuracoes.getPorta());
            data.put("Configuracoes.senha", configuracoes.getSenha());
            data.put("Configuracoes.servidor", configuracoes.getServidor());
            data.put("Configuracoes.tipoEncript", configuracoes.getTipoCript());
            data.put("Configuracoes.created", Text.formatDateForTable(configuracoes.getCreated()));
            data.put("Configuracoes.modified", Text.formatDateForTable(configuracoes.getModified()));

            return new Request(data);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public boolean validateEmail(String email) {
        try {
            String regular_expression = "[A-Za-z0-9\\._-]+@[A-Za-z0-9]+(\\.[A-Za-z]+)+(\\.[A-Za-z]+)*";
            return email.matches(regular_expression);
        } catch (Exception error) {
            throw error;
        }
    }
}
