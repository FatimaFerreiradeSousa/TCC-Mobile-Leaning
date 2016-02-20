package com.br.daos;

import com.br.entidades.Pergunta;
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

    @PersistenceContext(unitName = "Mobile-Edu-BD")
    private EntityManager em;

    @Override
    public boolean salvar(Pergunta questao) {

        try {
            em.persist(questao);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizar(Pergunta questao) {

        try {
            em.merge(questao);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Pergunta consultar(int codigo) {

        try {
            return em.find(Pergunta.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remover(Pergunta questao) {

        try {
            em.remove(em.merge(questao));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Pergunta> listarQuestoes(String login) {
        Query query = em.createQuery("select p from Pergunta p where p.professor.login = :login");
        query.setParameter("login", login);

        return (List<Pergunta>) query.getResultList();
    }
    
    @Override
    public List<Pergunta> listarPerguntasPorCategoria(String categoria, int qtd){
        Query query = em.createQuery("select p from Pergunta p where p.categoria = :categoria");
        query.setParameter("categoria", categoria);
        
        return (List<Pergunta>) query.setMaxResults(qtd).setFirstResult(0).getResultList();
    }
    
    @Override
    public List<String> listarCategoriasPerguntas(String loginProfessor) {
        Query q = em.createQuery("SELECT DISTINCT p.categoria FROM Pergunta p WHERE p.professor.login = :login");
        q.setParameter("login", loginProfessor);
        
        return (List<String>) q.getResultList();
    }
}
