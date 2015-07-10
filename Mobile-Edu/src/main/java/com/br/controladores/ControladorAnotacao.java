package com.br.controladores;

import com.br.entidades.Anotacao;
import com.br.entidades.Professor;
import com.br.fachada.Fachada;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha
 */
@Named(value = "controladorAnotacao")
@SessionScoped
public class ControladorAnotacao implements Serializable {

    @EJB
    private Fachada fachada;
    private Anotacao anotacao;
    
    public ControladorAnotacao() {
        anotacao = new Anotacao();
    }

    public Anotacao getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(Anotacao anotacao) {
        this.anotacao = anotacao;
    }
    
    public String salvarAnotacao(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");
        anotacao.setProfessor(professorLogado);
        fachada.salvarAnotacao(anotacao);
        anotacao = new Anotacao();
        return "paginaInicialProfessor?faces-redirect=true";
    }
    
    public String atualizarAnotacao(){
        fachada.atualizarAnotacao(anotacao);
        return "paginaInicialProfessor?faces-redirect=true";
    }
    
    public String removerAnotacao(Anotacao anotacao){
        fachada.removerAnotacao(anotacao);
        return "paginaInicialProfessor?faces-redirect=true";
    }
    
    public List<Anotacao> listarTodas(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");
        
        return fachada.listarAnotacao(professorLogado);
    }
}
