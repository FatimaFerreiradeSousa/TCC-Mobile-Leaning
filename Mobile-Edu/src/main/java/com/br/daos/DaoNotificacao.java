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
    public List<Notificacao> listarNotificacoes(String login) {
        Query query = em.createQuery("select n from Notificacao n where n.destinatario = :login");
        query.setParameter("login", login);
        
        return (List<Notificacao>) query.getResultList();
    }
    
    @Override
    public int listarQTDNotificacoes(String login) {
        Query query = em.createQuery("select n from Notificacao n where n.destinatario = :login and n.lido = false ORDER BY n.id DESC");
        query.setParameter("login", login);
        
        return query.getResultList().size();
    }
}
