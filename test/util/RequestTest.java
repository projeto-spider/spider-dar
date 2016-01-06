/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;


/**
 *
 * @author iuriraiol
 */
public class RequestTest
{
    private Request request;
    private HashMap<String,String> data;
    
    @Before
    public void initialize()
    {
        this.data = new HashMap<String,String>();
               
        this.request = new Request(data);
    }
    
    @Test
    public void retornarNullParaRequestDataComHashMapVazio()
    {
        String result = request.getData(null);
        
        assertEquals(null, result);
    }
    
    @Test
    public void retornarValorPorChaveParaRequestDataComHashMap1Item()
    {
        data.put("chave","valor");
        request = new Request(data);
        
        String result = request.getData("chave");
        
        assertEquals("valor",result);
    }
    
    @Test
    public void retornaValoresPorChavesParaRequestDataComHashMapNItens()
    {
        data.put("chave1","valor1");
        data.put("chave2","valor2");
        data.put("chave3",null);
        data.put("chave4","valor4");
        
        request = new Request(data);
        
        assertEquals("valor1",request.getData("chave1"));
        assertEquals("valor2",request.getData("chave2"));
        assertEquals(null,request.getData("chave3"));
        assertEquals("valor4",request.getData("chave4"));
    }
    
    @Test
    public void retorna1DadoSetado()
    {
        request.setData("chave","valorSetado");
        
        assertEquals("valorSetado", request.getData("chave"));
    }
    
    @Test
    public void retornaNDadosSetados()
    {
        request.setData("chave1","valor1");
        request.setData("chave2",null);
        request.setData("chave3","valor3");
        request.setData(null,"valor4");
        
        assertEquals("valor1",request.getData("chave1"));
        assertEquals(null,request.getData("chave2"));
        assertEquals("valor3",request.getData("chave3"));
        assertEquals("valor4",request.getData(null));//TODO:teste deveria dar inválido     
    }

    /**
     * Test of getHashMapData method, of class Request.
     */
    @Test
    public void testGetHashMapData()
    {
        request.setData("chave1", "valor1");
        request.setData("chave2", "valor2");
        request.setData("chave3", "valor3");
        request.setData("chave4", "valor4");
        
        HashMap<String,String> expResult = new HashMap<String,String>();
        
        expResult.put("chave1", "valor1");
        expResult.put("chave2", "valor2");
        expResult.put("chave3", "valor3");
        expResult.put("chave4", "valor4");
        
        assertEquals(expResult, request.getHashMapData());
    }
    
    @Test
    public void testSeRequestAceitaTipoInput()
    {
        request.setHashMapValueToInput();
        
        Input objectExpected = new Input(1, "tipo", "Nome", "Valor");
        
        request.setDataInput("Objeto.input", objectExpected);
        
        assertThat(request.getDataInput("Objeto.input"), instanceOf(Input.class));
    }        
}