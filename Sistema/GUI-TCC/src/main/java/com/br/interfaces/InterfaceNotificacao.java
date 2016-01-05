package com.br.interfaces;

import com.br.entidades.Notificacao;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceNotificacao {

    public boolean salvarNotificacao(Notificacao notificacao);

    public List<Notificacao> listarNotificacoesAluno(String login);

    public List<Notificacao> notificacoesNaoLidasAluno(String login);

    public List<Notificacao> registroAttAluno(String login);

    public boolean atualizarNotificacao(Notificacao notificacao);

    public List<Notificacao> listarNotificacoesProfessor(String login);

    public List<Notificacao> notificacoesNaoLidasProfessor(String login);

    public List<Notificacao> registroAttProfessor(String login);
}
