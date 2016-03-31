package com.br.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Fatinha de Sousa
 */
public class RecuperarEmail {

    public boolean enviarEmail(String emailUsuario, String senha) {

        SimpleEmail email = new SimpleEmail();
        try {
            
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.addTo(emailUsuario);
            email.setFrom("sousaeliane657@gmail.com", "Eliane de Sousa");
            email.setSubject("Recuperação de Senha");
            email.setMsg("Sua senha é: " +senha);
            email.setTLS(true);
            email.setAuthenticator(new DefaultAuthenticator("sousaeliane657@gmail.com", "heloisa01082015"));
            email.send();
            
            return true;
        } catch (EmailException ex) {
            Logger.getLogger(RecuperarEmail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
