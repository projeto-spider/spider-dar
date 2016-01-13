/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iuriraiol
 */
public class InputTest 
{
    @Test
    public void retornaInputCompleto()
    {
        Input input = new Input(1,"tipo","Nome do Campo", "valor");
        
        assertEquals(1,input.getOrdem());
        assertEquals("tipo",input.getTipo());
        assertEquals("Nome do Campo",input.getNome());
        assertEquals("valor",input.getValor());
    }
}