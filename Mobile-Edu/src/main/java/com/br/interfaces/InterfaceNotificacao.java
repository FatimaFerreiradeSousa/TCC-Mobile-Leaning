package com.br.interfaces;

import com.br.entidades.Notificacao;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceNotificacao {

    public boolean salvarNotificacao(Notificacao notificacao);

    public List<Notificacao> listarNotificacoes(String login);
}
