package com.br.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Resposta implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;
    private String conteudo;
    private boolean respostaCerta;
    
    public Resposta(){
    
    }
    
    public Resposta(int numero, String conteudo, boolean respostaCerta){
        this.numero = numero;
        this.conteudo = conteudo;
        this.respostaCerta = respostaCerta;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public String getConteudo(){
        return conteudo;
    }
    
    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }
    
    public boolean getRespostaCerta(){
        return respostaCerta;
    }
    
    public void setRespostaCerta(boolean respostaCerta){
        this.respostaCerta = respostaCerta;
    }
}
