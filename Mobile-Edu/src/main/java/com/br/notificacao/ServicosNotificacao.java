package com.br.notificacao;

import com.br.entidades.Notificacao;
import com.br.enumeracao.TipoNotificacao;
import java.util.Date;

/**
 *
 * @author Fatinha de Sousa
 */
public class ServicosNotificacao {

    private static Notificacao notificacao;

    /*Notificações aluno*/
    /*Okay :)*/
    public static Notificacao solicitarParticiparGrupo(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setMensagem(loginAluno + " solicitou participar do grupo " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());
        notificacao.setTipo(TipoNotificacao.ENVIADA_ALUNO);

        return notificacao;

    }
    
    /*Notificações professor*/
    
    /*Notificacao Okay*/
    public static Notificacao participarGrupo(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setMensagem(loginProfessor + " adicionou voce no grupo de " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());
        notificacao.setTipo(TipoNotificacao.ENVIADA_PROFESSOR);

        return notificacao;
    }
    
    /*Aceitar Solicitacao - okay*/
    public static Notificacao aceitarSolicitacao(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setMensagem(loginProfessor + " aceitou sua solicitação para participar do grupo " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());
        notificacao.setTipo(TipoNotificacao.ENVIADA_PROFESSOR);

        return notificacao;

    }
}
