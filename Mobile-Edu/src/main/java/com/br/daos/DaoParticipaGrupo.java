package com.br.daos;

import com.br.entidades.Aluno;
import com.br.entidades.ParticipaGrupo;
import com.br.interfaces.InterfaceDaoPGrupo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Fatinha
 */
@Stateless
public class DaoParticipaGrupo implements InterfaceDaoPGrupo {

    @PersistenceContext(unitName = "Mobile-Edu-UP")
    private EntityManager em;

    @Override
    public boolean addMembro(ParticipaGrupo participaGrupo) {

        try {
            em.persist(participaGrupo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizarSolicitacao(ParticipaGrupo participaGrupo) {

        try {
            em.merge(participaGrupo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removerMembro(String login, int codigoGrupo) {

        try {
            Query query = em.createQuery("delete from ParticipaGrupo p where p.aluno.login = :login and p.grupo.codigo = :codigo");
            query.setParameter("login", login);
            query.setParameter("codigo", codigoGrupo);
            query.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Aluno> listarMembros(int codigoGrupo) {
        Query query = em.createQuery("select a from ParticipaGrupo p INNER JOIN p.aluno a where p.aceito = TRUE and p.grupo.codigo = :codGrupo");
        query.setParameter("codGrupo", codigoGrupo);

        return (List<Aluno>) query.getResultList();
    }

    @Override
    public boolean verificaSeJaEhMembro(String login, int codigoGrupo) {
        Query query = em.createQuery("select p from ParticipaGrupo p where p.aluno.login = :login and p.grupo.codigo = :codigoGrupo and p.aceito = true");
        query.setParameter("login", login);
        query.setParameter("codigoGrupo", codigoGrupo);

        List<ParticipaGrupo> participaGrupos = query.getResultList();

        if (participaGrupos.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<ParticipaGrupo> listarGruposAluno(String login) {
        Query query = em.createQuery("select p from ParticipaGrupo p where p.aluno.login = :login and p.aceito = true");
        query.setParameter("login", login);

        return (List<ParticipaGrupo>) query.getResultList();
    }

    @Override
    public List<ParticipaGrupo> listarGruposPendentes(String login) {
        Query query = em.createQuery("select p from ParticipaGrupo p where p.aluno.login = :login and p.aceito = false");
        query.setParameter("login", login);

        return (List<ParticipaGrupo>) query.getResultList();
    }

    @Override
    public boolean verificaSolicitacao(String login, int codigoGrupo) {
        Query query = em.createQuery("select p from ParticipaGrupo p where p.aluno.login = :login and p.grupo.codigo = :codigoGrupo and p.aceito = false");
        query.setParameter("login", login);
        query.setParameter("codigoGrupo", codigoGrupo);

        List<ParticipaGrupo> participaGrupos = query.getResultList();

        if (participaGrupos.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ParticipaGrupo> solicitacoesRecebidas(String loginProfessor) {
        Query query = em.createQuery("select p from ParticipaGrupo p INNER JOIN p.grupo g where g.professorGrupos.login = :loginProfessor and p.aceito = false");
        query.setParameter("loginProfessor", loginProfessor);

        return (List<ParticipaGrupo>) query.getResultList();
    }

    @Override
    public boolean removerMembros(int codigoGrupo) {

        try {
            Query query = em.createQuery("DELETE FROM ParticipaGrupo p where p.grupo.codigo = :codigoGrupo");
            query.setParameter("codigoGrupo", codigoGrupo);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
