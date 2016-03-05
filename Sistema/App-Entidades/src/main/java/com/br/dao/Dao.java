package com.br.dao;

import com.br.app.conection.Connection;
import com.br.entidades.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Fatinha de Sousa
 */
public class Dao {

    private final EntityManager em;

    public Dao() {
        em = Connection.conn();
    }

    public boolean salvarAluno(Aluno aluno) {

        em.getTransaction().begin();
        try {
            em.persist(aluno);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean atualizarAluno(Aluno aluno) {

        em.getTransaction().begin();
        try {
            em.merge(aluno);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public Aluno buscarAluno(String login) {

        return em.find(Aluno.class, login);

    }

    public boolean removerAluno(Aluno aluno) {

        em.getTransaction().begin();
        try {
            em.remove(aluno);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public Aluno loginAluno(String login, String senha) {
        Query query = em.createQuery("select a from Aluno a where a.login = :login and a.senha = :senha");
        query.setParameter("login", login);
        query.setParameter("senha", senha);

        List<Aluno> alunos = (List<Aluno>) query.getResultList();

        if (!alunos.isEmpty()) {
            return alunos.get(0);
        } else {
            return null;
        }
    }

    public Turma buscarTurma(String codigo) {
        try {

            return em.find(Turma.class, codigo);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Nota buscarNota(int codigo) {
        try {
            return em.find(Nota.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /*PARTICIPA GRUPO*/

    public List<Grupo> pesquisarGrupoPorNome(String nome) {
        Query query = em.createQuery("select g from Grupo g where g.nome = :nome");
        query.setParameter("nome", nome);

        return (List<Grupo>) query.getResultList();
    }
    
    

    public List<Topico> topicosGrupo(int codigoGrupo) {
        Query query = em.createQuery("select t from Topico t where t.grupo.codigo = :codigoGrupo ORDER BY t.codigo DESC");
        query.setParameter("codigoGrupo", codigoGrupo);

        return (List<Topico>) query.getResultList();
    }
}
