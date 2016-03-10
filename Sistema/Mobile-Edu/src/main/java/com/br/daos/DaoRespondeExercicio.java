package com.br.daos;

import com.br.entidades.RespondeExercicio;
import com.br.interfaces.InterfaceDaoRespondeExercicio;
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
public class DaoRespondeExercicio implements InterfaceDaoRespondeExercicio {

    @PersistenceContext(unitName = "Mobile-Edu-BD")
    private EntityManager em;

    @Override
    public boolean salvar(RespondeExercicio responde) {

        try {
            em.persist(responde);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualiza(RespondeExercicio responde) {

        try {
            em.merge(responde);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public RespondeExercicio consulta(String codigo) {

        try {
            return em.find(RespondeExercicio.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remover(RespondeExercicio responde) {

        try {
            em.remove(em.merge(responde));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean respondeTeste(int codTeste, String loginAluno) {
        Query query = em.createQuery("select r from RespondeExercicio r where r.codTeste = :codTeste and r.aluno.login = :loginAluno");
        query.setParameter("codTeste", codTeste);
        query.setParameter("loginAluno", loginAluno);

        List<RespondeExercicio> list = query.getResultList();

        return list.isEmpty();
    }

    @Override
    public List<RespondeExercicio> resultados(int codTeste) {
        Query query = em.createQuery("select r from RespondeExercicio r where r.codTeste = :codTeste");
        query.setParameter("codTeste", codTeste);

        return query.getResultList();
    }

    @Override
    public List<RespondeExercicio> listarExcerciciosAluno(String login, int grupo) {
        Query query = em.createQuery("select r from RespondeExercicio r where r.aluno.login = :login AND r.grupo.codigo = :codigo");
        query.setParameter("login", login);
        query.setParameter("codigo", grupo);

        return query.getResultList();
    }
}
