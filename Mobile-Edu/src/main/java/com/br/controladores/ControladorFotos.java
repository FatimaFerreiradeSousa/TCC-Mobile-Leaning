package com.br.controladores;

import com.br.entidades.Aluno;
import com.br.fachada.Fachada;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Fatinha
 */
@Named(value = "controladorFotos")
@SessionScoped
public class ControladorFotos implements Serializable {

    @EJB
    Fachada fachada;
    private StreamedContent content;

    public ControladorFotos() {

    }

    public StreamedContent getContent() {
        return this.content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public StreamedContent mostrarFotoUsuario(String fotoPerfil) {
        File foto = new File(fotoPerfil);

        try {

            BufferedInputStream in = new BufferedInputStream(new FileInputStream(foto));
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            this.content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/jpeg");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }
}
