package com.br.daos;

import com.br.entidades.Teste;
import com.br.interfaces.InterfaceExercicio;
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
public class DaoExercicio implements InterfaceExercicio {

    @PersistenceContext(unitName = "Sistema-TCC-UP")
    private EntityManager em;

    @Override
    public boolean salvar(Teste exercicio) {

        try {
            em.persist(exercicio);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizar(Teste exercicio) {

        try {
            em.merge(exercicio);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Teste consultar(String codigo) {

        try {
            return em.find(Teste.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remover(Teste exercicio) {

        try {
            em.remove(em.merge(exercicio));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Teste> testesCadastradosProfessor(String login) {
        Query query = em.createQuery("select t from Teste t where t.professor.login = :login");
        query.setParameter("login", login);

        return (List<Teste>) query.getResultList();
    }
}
