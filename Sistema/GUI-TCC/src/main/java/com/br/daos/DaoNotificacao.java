package com.br.daos;

import com.br.entidades.Notificacao;
import com.br.enumeracao.TipoNotificacao;
import com.br.interfaces.InterfaceNotificacao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Fatinha de Sousa
 */
@Stateless
public class DaoNotificacao implements InterfaceNotificacao {

    @PersistenceContext(unitName = "Mobile-Edu-BD")
    private EntityManager em;

    @Override
    public boolean salvarNotificacao(Notificacao notificacao) {
        try {
            em.persist(notificacao);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Notificacao> listarNotificacoesAluno(String login) {
        Query q = em.createQuery("select n from Notificacao n where n.loginAluno = :login and n.tipo <> :tipo ORDER BY n.id DESC");
        q.setParameter("login", login);
        q.setParameter("tipo", TipoNotificacao.ENVIADA_ALUNO);

        return q.getResultList();
    }

    @Override
    public List<Notificacao> notificacoesNaoLidasAluno(String login) {
        Query q = em.createQuery("select n from Notificacao n where n.loginAluno = :login and n.lido = FALSE and n.tipo <> :tipo ORDER BY n.id DESC");
        q.setParameter("login", login);
        q.setParameter("tipo", TipoNotificacao.ENVIADA_ALUNO);

        return q.getResultList();

    }

    @Override
    public List<Notificacao> registroAttAluno(String login) {
        Query q = em.createQuery("select n from Notificacao n where n.loginAluno = :login and n.tipo = :tipo ORDER BY n.id DESC");
        q.setParameter("login", login);
        q.setParameter("tipo", TipoNotificacao.ENVIADA_ALUNO);

        return q.getResultList();
    }

    @Override
    public boolean atualizarNotificacao(Notificacao notificacao) {

        try {
            notificacao.setLido(true);
            em.merge(notificacao);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Notificacao> listarNotificacoesProfessor(String login) {
        Query q = em.createQuery("select n from Notificacao n where n.loginProfessor = :login and n.tipo <> :tipo ORDER BY n.id DESC");
        q.setParameter("login", login);
        q.setParameter("tipo", TipoNotificacao.ENVIADA_PROFESSOR);

        return q.getResultList();
    }

    @Override
    public List<Notificacao> notificacoesNaoLidasProfessor(String login) {
        Query q = em.createQuery("select n from Notificacao n where n.loginProfessor = :login and n.lido = FALSE and n.tipo <> :tipo ORDER BY n.id DESC");
        q.setParameter("login", login);
        q.setParameter("tipo", TipoNotificacao.ENVIADA_PROFESSOR);

        return q.getResultList();
    }
    
    @Override
    public List<Notificacao> registroAttProfessor(String login) {
        Query q = em.createQuery("select n from Notificacao n where n.loginProfessor = :login and n.tipo = :tipo ORDER BY n.id DESC");
        q.setParameter("login", login);
        q.setParameter("tipo", TipoNotificacao.ENVIADA_PROFESSOR);

        return q.getResultList();
    }
}
