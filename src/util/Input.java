/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author iuriraiol
 */
public class Input
{
    private int ordem;
    private String tipo;
    private String nome;
    private String valor;
    
    public Input(int ordem,String tipo, String nome, String valor)
    {
        this.ordem = ordem;
        this.tipo = tipo;
        this.nome= nome;
        this.valor= valor;
    }

    /**
     * @return the ordem
     */
    public int getOrdem() {
        return ordem;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }
}
