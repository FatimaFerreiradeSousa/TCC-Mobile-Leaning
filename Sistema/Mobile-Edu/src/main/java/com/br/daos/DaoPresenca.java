package com.br.daos;

import com.br.entidades.Presenca;
import com.br.interfaces.InterfaceDaoPresenca;
import java.util.Date;
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
public class DaoPresenca implements InterfaceDaoPresenca {

    @PersistenceContext(unitName = "Mobile-Edu-BD")
    private EntityManager em;

    @Override
    public boolean salvarPresenca(Presenca presenca) {

        try {
            em.persist(presenca);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizarPresenca(Presenca presenca) {
        try {
            em.merge(presenca);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removerPresenca(Presenca presenca) {
        try {
            em.remove(em.merge(presenca));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Presenca> listarPresencasPorData(Date data) {
        Query query = em.createQuery("select p from Presenca p where p.dataPreseca = :dataPresenca");
        query.setParameter("dataPresenca", data);

        return (List<Presenca>) query.getResultList();
    }

    @Override
    public List<Presenca> listarPresencasAluno(String login) {
        Query query = em.createQuery("SELECT p FROM Presenca p where p.aluno.login = :login");
        query.setParameter("login", login);

        return (List<Presenca>) query.getResultList();

    }

    @Override
    public List<Presenca> listarPresencasTurma(String turma) {
        Query query = em.createQuery("select p from Presenca p where p.turma.codigo = :turma");
        query.setParameter("turma", turma);

        return (List<Presenca>) query.getResultList();

    }

    @Override
    public int qtdFaltas(String login, String turma) {
        Query query = em.createQuery("SELECT p FROM Presenca p where p.aluno.login = :login and p.status = false and p.turma.codigo = :turma");
        query.setParameter("login", login);
        query.setParameter("turma", turma);

        List<Presenca> list = query.getResultList();

        return list.size();
    }

    @Override
    public List<Presenca> listarPresencaPorHorario(Date data, String horaInicio) {
        Query query = em.createQuery("select p from Presenca p where p.dataPreseca = :dataPresenca and p.horaAula = :horaInicio");
        query.setParameter("dataPresenca", data);
        query.setParameter("horaInicio", horaInicio);

        return (List<Presenca>) query.getResultList();
    }
}
