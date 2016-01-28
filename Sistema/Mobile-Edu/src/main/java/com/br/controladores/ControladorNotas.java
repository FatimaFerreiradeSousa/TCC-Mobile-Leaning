package com.br.controladores;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorNotas")
@SessionScoped
public class ControladorNotas implements Serializable {

    public ControladorNotas() {
    }
    
    public String paginaNotas(){
        return "page-add-notas?faces-redirect=true";
    }
    
}
