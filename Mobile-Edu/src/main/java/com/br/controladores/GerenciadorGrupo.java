package com.br.controladores;

import com.br.entidades.Grupo;
import com.br.entidades.Professor;
import com.br.fachada.Fachada;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha
 */
@Named(value = "gerenciadorGrupo")
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
