package com.br.senha;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Fatinha de Sousa
 */
public class RecuperarEmail {

    public boolean enviarEmail() throws EmailException {

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.live.com");
        email.setSmtpPort(587);
        email.setSSLOnConnect(true);
        email.addTo("fferreira913@gmail.com", "Fatinha de Sousa");
        email.setAuthenticator(new DefaultAuthenticator("fatinha.sg@hotmail.com", "fatinha1994"));
        email.setFrom("fatinha.sg@hotmail.com", "Fatinha de Sousa");
        email.setSubject("Recuperação de Senha");
        email.setMsg("Sua senha e: 12345");
        email.send();
        return true;
    }
}
