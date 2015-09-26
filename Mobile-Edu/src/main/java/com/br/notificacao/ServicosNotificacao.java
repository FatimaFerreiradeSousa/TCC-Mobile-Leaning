package com.br.notificacao;

import com.br.entidades.Notificacao;
import java.util.Date;

/**
 *
 * @author Fatinha de Sousa
 */
public class ServicosNotificacao {

    private static Notificacao notificacao;

    
    public static Notificacao participarGrupo(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setMensagem(loginProfessor + " adicionou voce no grupo de " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

        return notificacao;
    }

    public static Notificacao solicitarParticiparGrupo(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao.setLido(false);
        notificacao.setMensagem(loginAluno + " solicitou participar do grupo " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

        return notificacao;

    }
    
    public static Notificacao alunoPublicaGrupo(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao.setLido(false);
        notificacao.setMensagem(loginAluno + " publicou no grupo " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

        return notificacao;
    }
    
    public static Notificacao professorPublicaGrupo(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao.setLido(false);
        notificacao.setMensagem(loginProfessor + " publicou no grupo " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

        return notificacao;

    }
    
    public static Notificacao aceitarSolicitacao(String loginAluno, String loginProfessor, String nomeGrupo) {
        notificacao.setLido(false);
        notificacao.setMensagem(loginProfessor + " aceitou sua solicitação para participar do grupo " + nomeGrupo);
        notificacao.setLoginAluno(loginAluno);
        notificacao.setLoginProfessor(loginProfessor);
        notificacao.setDataNot(new Date());

        return notificacao;

    }
}
