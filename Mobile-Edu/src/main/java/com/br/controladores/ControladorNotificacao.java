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
        //fachada.marcarComLido(notificacao);

        return "page-inicial-aluno?faces-redirect=true";
    }

    /*Notificacoes Professor*/
    public List<Notificacao> listarNotificacoesProfessr() {
        return fachada.listarNotificacoes(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }
    
    public List<Notificacao> notificacoesNaoLidasProfessor(){
        return new ArrayList();
    }
}
