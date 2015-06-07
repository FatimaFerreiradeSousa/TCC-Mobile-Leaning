package com.br.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Resposta implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String conteudo;
    private boolean respostaCerta;
    
    public Resposta(){
    
    }
    
    public Resposta(String conteudo, boolean respostaCerta){
        this.conteudo = conteudo;
        this.respostaCerta = respostaCerta;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
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
