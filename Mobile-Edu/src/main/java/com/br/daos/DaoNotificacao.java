package com.br.daos;

import com.br.entidades.Notificacao;
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

    @PersistenceContext(unitName = "Mobile-Edu-UP")
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
        Query q = em.createQuery("select n from Notificacao n where n.loginAluno = :login ORDER BY n.id DESC");
        q.setParameter("login", login);

        return q.getResultList();
    }

    @Override
    public List<Notificacao> notificacoesNaoLidasAluno(String login) {
        Query q = em.createQuery("select n from Notificacao n where n.loginAluno = :login and n.lido = FALSE ORDER BY n.id DESC");
        q.setParameter("login", login);

        return q.getResultList();

    }
    
    @Override
    public boolean atualizarNotificacao(Notificacao notificacao){
        
        try{
            notificacao.setLido(true);
            em.merge(notificacao);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
