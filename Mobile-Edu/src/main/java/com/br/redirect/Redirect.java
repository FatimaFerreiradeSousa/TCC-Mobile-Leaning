package com.br.redirect;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Fatinha
 */
@Named(value = "redirect")
@SessionScoped
public class Redirect implements Serializable {

    public Redirect() {
    }
    
    public String paginaConfiguracao(){
        return "../paginaConfig?faces-redirect=true";
    }
}
