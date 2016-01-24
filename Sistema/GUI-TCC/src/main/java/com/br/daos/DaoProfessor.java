package com.br.daos;

import com.br.entidades.Professor;
import com.br.interfaces.InterfaceDaoProfessor;
import java.util.ArrayList;
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
public class DaoProfessor implements InterfaceDaoProfessor {

    @PersistenceContext(unitName = "Mobile-Edu-BD")
    private EntityManager em;

    @Override
    public boolean salvar(Professor professor) {

        try {
            em.persist(professor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizar(Professor professor) {

        try {
            em.merge(professor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remover(Professor professor) {

        try {
            em.remove(em.merge(professor));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Professor buscarProfessor(String login) {

        try {
            return em.find(Professor.class, login);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Professor loginProfessor(String login, String senha) {
        Query query = em.createQuery("select p from Professor p where p.login = :login and p.senha = :senha");
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        List<Professor> professor = (List<Professor>) query.getResultList();

        if (!professor.isEmpty()) {
            return professor.get(0);
        } else {
            return null;
        }
    }
    
    @Override
    public Professor buscarProfessorEmail(String email){
        Query query = em.createQuery("select p from Professor p where p.email = :email");
        query.setParameter("email", email);
        
        List<Professor> professor = (List<Professor>) query.getResultList();

        if (!professor.isEmpty()) {
            return professor.get(0);
        } else {
            return null;
        }
    }
}
