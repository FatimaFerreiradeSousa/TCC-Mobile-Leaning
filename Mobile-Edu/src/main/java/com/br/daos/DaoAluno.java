package com.br.daos;

import com.br.entidades.Aluno;
import com.br.interfaces.InterfaceDaoAluno;
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
public class DaoAluno implements InterfaceDaoAluno {

    @PersistenceContext(unitName = "Mobile-Edu-UP")
    private EntityManager em;

    @Override
    public boolean salvarAluno(Aluno aluno) {

        try {
            em.persist(aluno);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizarAluno(Aluno aluno) {

        try {
            em.merge(aluno);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Aluno buscarAluno(String login) {

        try {
            return em.find(Aluno.class, login);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean removerAluno(Aluno aluno) {

        try {
            em.remove(em.merge(aluno));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
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
    
    @Override
    public Aluno buscarAlunoEmail(String email){
        Query query = em.createQuery("select a from Aluno a where a.email = :email");
        query.setParameter("email", email);
        
        List<Aluno> alunos = (List<Aluno>) query.getResultList();

        if (!alunos.isEmpty()) {
            return alunos.get(0);
        } else {
            return null;
        }
    }
}
