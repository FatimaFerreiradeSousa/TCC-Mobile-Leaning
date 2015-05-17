package com.br.controlResp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Fatinha
 */
@Named(value = "controladorTeste")
@SessionScoped
public class ControladorTeste implements Serializable {

    private String nome;
    
    public ControladorTeste() {
        this.nome = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String salvar(){
        this.nome = "FatinhaS";
        System.out.println("Salvando!");
        return null;
    }
    
    public String alterar(){
        nome = new String();
        System.out.println("Alterando!");
        return null;
    }
}
