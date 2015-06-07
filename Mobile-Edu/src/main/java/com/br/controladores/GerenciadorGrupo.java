package com.br.controladores;

import com.br.entidades.Grupo;
import com.br.entidades.Professor;
import com.br.fachada.Fachada;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Fatinha
 */
@ManagedBean(name = "gerenciadorGrupo")
@SessionScoped
public class GerenciadorGrupo implements Serializable {

    @EJB
    private Fachada fachada;
    private Grupo grupo;
    private HttpSession session;

    public GerenciadorGrupo() {
        grupo = new Grupo();
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    public String salvarGrupo(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");
        
        grupo.setDataCriacao(new Date());
        grupo.setProfessorGrupos(professorLogado);
        fachada.salvarGrupo(grupo);
        grupo = new Grupo();
        return null;
    }

    public List<Grupo> gruposCriados() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");

        return fachada.meusGrupos(professorLogado.getLogin());
    }

    public String pagInicialGrupo(Grupo grupo) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        this.session = (HttpSession) context.getSession(false);
        context.getSessionMap().put("grupo", grupo);
        return "pagInicialGrupo?faces-redirect=true";
    }
}
