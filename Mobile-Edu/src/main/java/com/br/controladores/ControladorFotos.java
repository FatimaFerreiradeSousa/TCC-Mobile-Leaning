package com.br.controladores;

import com.br.entidades.Aluno;
import com.br.entidades.Pessoa;
import com.br.fachada.Fachada;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
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
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public StreamedContent mostrarFoto() {

        String loginUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("loginUsuario");
        
        if (loginUsuario != null) {
            Pessoa pessoa = new Pessoa();

            if (fachada.buscarProfessor(loginUsuario) != null) {
                pessoa = fachada.buscarProfessor(loginUsuario);
            } else {
                pessoa = fachada.buscarAluno(loginUsuario);
            }

            File fotoUsuario = new File(pessoa.getFoto());

            try {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(fotoUsuario));
                byte[] bytes = new byte[in.available()];
                in.read(bytes);
                in.close();
                this.content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/jpeg");

                return content;
            } catch (Exception e) {
                e.printStackTrace();
            };
        }
        return new DefaultStreamedContent();
    }
    
    public StreamedContent mostrarFotoComentario() {

        String loginComentario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("loginComentario");
        
        if (loginComentario != null) {
            Pessoa pessoa = new Pessoa();

            if (fachada.buscarProfessor(loginComentario) != null) {
                pessoa = fachada.buscarProfessor(loginComentario);
            } else {
                pessoa = fachada.buscarAluno(loginComentario);
            }

            File fotoUsuario = new File(pessoa.getFoto());

            try {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(fotoUsuario));
                byte[] bytes = new byte[in.available()];
                in.read(bytes);
                in.close();
                this.content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/jpeg");

                return content;
            } catch (Exception e) {
                e.printStackTrace();
            };
        }
        return new DefaultStreamedContent();
    }
}
