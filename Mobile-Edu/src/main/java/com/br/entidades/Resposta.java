package com.br.entidades;

import java.io.Serializable;
import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.numero;
        hash = 67 * hash + Objects.hashCode(this.conteudo);
        hash = 67 * hash + (this.respostaCerta ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Resposta other = (Resposta) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.conteudo, other.conteudo)) {
            return false;
        }
        if (this.respostaCerta != other.respostaCerta) {
            return false;
        }
        return true;
    }
}
