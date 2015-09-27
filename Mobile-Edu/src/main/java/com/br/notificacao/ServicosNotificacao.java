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
    
    /*Okay*/
    public static Notificacao alunoPublicaGrupo(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setMensagem(loginAluno + " publicou no grupo " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

        return notificacao;
    }
    
    /*Okay*/
    public static Notificacao alunoComentaTopico(String loginAluno, String loginProfessor) {
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setMensagem(loginAluno + " comentou sua publicação");
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

        return notificacao;
    }
    
    /*Notificações professor*/
    
    /*Okay*/
    public static Notificacao participarGrupo(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setMensagem(loginProfessor + " adicionou voce no grupo de " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

        return notificacao;
    }
    
    public static Notificacao professorPublicaTopico(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setMensagem(loginProfessor + " publicou no grupo " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

        return notificacao;

    }
    
    public static Notificacao professorComentarTopico(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setMensagem(loginProfessor + " comentou sua publicação");
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

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
