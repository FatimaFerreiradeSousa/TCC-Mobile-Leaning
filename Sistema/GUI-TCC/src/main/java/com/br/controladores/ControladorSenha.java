package com.br.controladores;

import com.br.senha.RecuperarEmail;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorSenha")
@SessionScoped
public class ControladorSenha implements Serializable {

    private String email;
    
    public ControladorSenha() {
    
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String enviarEmail() throws EmailException{
        RecuperarEmail recuperarEmail = new RecuperarEmail();
        recuperarEmail.enviarEmail();
        System.out.print("EMAIL: " +email);
        return "recuperar-senha?faces-redirect=true";
    }
}
