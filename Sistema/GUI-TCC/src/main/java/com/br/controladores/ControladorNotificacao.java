package com.br.controladores;

import com.br.entidades.Notificacao;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorNotificacao")
@SessionScoped
public class ControladorNotificacao implements Serializable {

    @EJB
    private Fachada fachada;

    public ControladorNotificacao() {
    }

    public String marcarComVisto(Notificacao notificacao) {
        
        fachada.marcarComoLido(notificacao);
        return "page-registro-aluno?faces-redirect=true";
    }
    
    public String marcarComVistoProfessor(Notificacao notificacao) {
        System.out.println("OKAY " +notificacao.getId());
        
        fachada.marcarComoLido(notificacao);
        return "page-notificacao-professor?faces-redirect=true";
    }
    
    public String pageNotificacaoProfessor(){
        return "page-notificacao-professor?faces-redirect=true";
    }
    
    public String pageNotificacaoAluno(){
        return "page-notificacao-aluno?faces-rediret=true";
    }
    
    public int listarNotificacoesNaoLidasAluno(){
        return fachada.listarQTDNotificacoes(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
    }
    
    public List<Notificacao> listarNotificacoesAluno(){
        return fachada.listarNotificacoes(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
    }
    
    public List<Notificacao> listarNotificacoesProfessor(){
        return fachada.listarNotificacoes(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }
    
    public int listarNotificacoesNaoLidasProfessor(){
        return fachada.listarQTDNotificacoes(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }
}
