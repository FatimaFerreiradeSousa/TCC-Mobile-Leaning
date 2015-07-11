package com.br.controladores;

import com.br.entidades.Anotacao;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

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
        anotacao.setProfessor(PegarUsuarioSessao.pegarProfessorSessao());
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
        return fachada.listarAnotacao(PegarUsuarioSessao.pegarProfessorSessao());
    }
}
