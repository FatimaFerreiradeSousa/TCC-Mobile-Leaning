package com.br.daos;

import com.br.entidades.Turma;
import com.br.interfaces.InterfaceDaoTurma;
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
public class DaoTurma implements InterfaceDaoTurma {

    @PersistenceContext(unitName = "Mobile-Edu-BD")
    private EntityManager em;

    @Override
    public boolean salvarTurma(Turma turma) {
        try {
            em.persist(turma);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizarTurma(Turma turma) {
        try {
            em.merge(turma);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removerTurma(Turma turma) {
        try {
            em.remove(em.merge(turma));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Turma buscarTurma(String codigo) {
        try {

            return em.find(Turma.class, codigo);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Turma> listarTurmas(String login) {
        Query query = em.createQuery("select t from Turma t where t.professor.login = :login");
        query.setParameter("login", login);

        return (List<Turma>) query.getResultList();
    }
}
