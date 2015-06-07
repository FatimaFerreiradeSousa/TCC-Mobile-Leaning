package com.br.daos;

import com.br.entidades.Questao;
import com.br.interfaces.InterfacePergunta;
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
public class DaoPergunta implements InterfacePergunta {

    @PersistenceContext(unitName = "Sistema-TCC-UP")
    private EntityManager em;

    @Override
    public boolean salvar(Questao questao) {

        try {
            em.persist(questao);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizar(Questao questao) {

        try {
            em.merge(questao);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Questao consultar(String codigo) {

        try {
            return em.find(Questao.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remover(Questao questao) {

        try {
            em.remove(em.merge(questao));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Questao> listarQuestoes(String login) {
        Query query = em.createQuery("select q from Questao q where q.professor.login = :login");
        query.setParameter("login", login);

        return (List<Questao>) query.getResultList();
    }
}
