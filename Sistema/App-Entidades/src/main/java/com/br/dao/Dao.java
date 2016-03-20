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

    /*Salvar*/
    public Aluno buscarAluno(String login) {

        try {
            return em.find(Aluno.class, login);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Aluno buscarAlunoEmail(String email) {
        Query query = em.createQuery("select a from Aluno a where a.email = :email");
        query.setParameter("email", email);

        List<Aluno> alunos = (List<Aluno>) query.getResultList();

        if (!alunos.isEmpty()) {
            return alunos.get(0);
        } else {
            return null;
        }
    }

    public Professor buscarProfessor(String login) {

        try {
            return em.find(Professor.class, login);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Professor buscarProfessorEmail(String email) {
        Query query = em.createQuery("select p from Professor p where p.email = :email");
        query.setParameter("email", email);

        List<Professor> professores = (List<Professor>) query.getResultList();

        if (!professores.isEmpty()) {
            return professores.get(0);
        } else {
            return null;
        }
    }

    /**
     * ***********************************************************************
     */
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

    public List<Horario> consultarHorario(String dia, String turma) {
        Query query = em.createQuery("SELECT h FROM Horario h where h.dia = :dia and h.turma.codigo = :turma");
        query.setParameter("dia", dia);
        query.setParameter("turma", turma);

        return (List<Horario>) query.getResultList();
    }

    public int qtdFaltas(String login, String turma) {
        Query query = em.createQuery("SELECT p FROM Presenca p where p.aluno.login = :login and p.status = false and p.turma.codigo = :turma");
        query.setParameter("login", login);
        query.setParameter("turma", turma);

        List<Presenca> list = query.getResultList();

        return list.size();
    }

    public int qtdPresencas(String login, String turma) {
        Query query = em.createQuery("SELECT p FROM Presenca p where p.aluno.login = :login and p.status = true and p.turma.codigo = :turma");
        query.setParameter("login", login);
        query.setParameter("turma", turma);

        List<Presenca> list = query.getResultList();

        return list.size();
    }

    /*PARTICIPA GRUPO*/
    public List<Grupo> pesquisarGrupoPorNome(String nome) {
        Query query = em.createQuery("select g from Grupo g where g.nome = :nome");
        query.setParameter("nome", nome);

        return (List<Grupo>) query.getResultList();
    }

    public List<ParticipaGrupo> listarGruposAluno(String login) {
        Query query = em.createQuery("select p from ParticipaGrupo p where p.aluno.login = :login and p.aceito = true");
        query.setParameter("login", login);

        return (List<ParticipaGrupo>) query.getResultList();
    }

    public Grupo consultarGrupo(int codigo) {

        try {
            return em.find(Grupo.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Aluno> listarMembros(int codigoGrupo) {
        Query query = em.createQuery("select a from ParticipaGrupo p INNER JOIN p.aluno a where p.aceito = TRUE and p.grupo.codigo = :codGrupo");
        query.setParameter("codGrupo", codigoGrupo);

        return (List<Aluno>) query.getResultList();
    }

    public List<Topico> listarArquivos(int codigoGrupo) {
        Query query = em.createQuery("select t from Topico t where t.grupo.codigo = :codigoGrupo and t.tipo = 'Arquivo' ORDER BY t.codigo DESC");
        query.setParameter("codigoGrupo", codigoGrupo);

        return (List<Topico>) query.getResultList();
    }

    public List<Topico> listarTopicos(int codigoGrupo) {
        Query query = em.createQuery("select t from Topico t where t.grupo.codigo = :codigoGrupo and t.tipo = 'Publicacao' ORDER BY t.codigo DESC");
        query.setParameter("codigoGrupo", codigoGrupo);

        return (List<Topico>) query.getResultList();
    }

    public List<Comentario> comentariosTopico(int codigoTopico) {
        Query query = em.createQuery("select c from Comentario c where c.topico.codigo = :codigo ORDER BY c.codigo DESC");
        query.setParameter("codigo", codigoTopico);

        return (List<Comentario>) query.getResultList();
    }

    public List<RespondeExercicio> resultados(int codTeste) {
        Query query = em.createQuery("select r from RespondeExercicio r where r.codTeste = :codTeste");
        query.setParameter("codTeste", codTeste);

        return query.getResultList();
    }

    public List<Topico> listarTopicosUsuario(String login, int codigoGrupo) {

        Query query = em.createQuery("select t from Topico t where t.grupo.codigo = :codigoGrupo and t.loginUsuario = :login");
        query.setParameter("codigoGrupo", codigoGrupo);
        query.setParameter("login", login);

        return (List<Topico>) query.getResultList();
    }

    public List<RespondeExercicio> listarExcerciciosAluno(String login, int grupo) {
        Query query = em.createQuery("select r from RespondeExercicio r where r.aluno.login = :login AND r.grupo.codigo = :codigo");
        query.setParameter("login", login);
        query.setParameter("codigo", grupo);

        return query.getResultList();
    }

    public ParticipaGrupo buscarParticipaGrupo(String loginAluno, int codGrupo) {
        Query query = em.createQuery("select p from ParticipaGrupo p where p.aluno.login = :loginAluno and p.grupo.codigo = :codGrupo");
        query.setParameter("loginAluno", loginAluno);
        query.setParameter("codGrupo", codGrupo);

        List<ParticipaGrupo> participaGrupo = query.getResultList();

        if (!participaGrupo.isEmpty()) {
            return participaGrupo.get(0);
        } else {
            return null;
        }
    }

    /**
     * ************************TOPICO
     *
     * @param topicoS
     *
     * @return ***************************************
     */
    public boolean salvarTopico(Topico topico) {

        em.getTransaction().begin();
        try {
            em.persist(topico);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean removerTopico(Topico topico) {
        em.getTransaction().begin();
        try {
            em.remove(em.merge(topico));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean alterarTopico(Topico topico) {
        em.getTransaction().begin();
        try {
            em.merge(topico);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public Topico consultarTopico(int codigo) {

        try {
            return em.find(Topico.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean salvarComentario(Comentario comentario) {

        em.getTransaction().begin();
        try {
            em.persist(comentario);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public Comentario consultarComentario(int codigo) {

        try {
            return em.find(Comentario.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean atualizarComentario(Comentario comentario) {

        em.getTransaction().begin();
        try {
            em.merge(comentario);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean removerComentarios(Comentario comentario) {
        em.getTransaction().begin();
        try {
            em.remove(em.merge(comentario));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }
    
    //RESPONDE TESTE
    public Teste consultarTeste(int codigo) {

        try {
            return em.find(Teste.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public RespondeExercicio consultarRespondeExercicio(String codigo) {

        try {
            return em.find(RespondeExercicio.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean respondeTeste(int codTeste, String loginAluno) {
        Query query = em.createQuery("select r from RespondeExercicio r where r.codTeste = :codTeste and r.aluno.login = :loginAluno");
        query.setParameter("codTeste", codTeste);
        query.setParameter("loginAluno", loginAluno);

        List<RespondeExercicio> list = query.getResultList();

        return list.isEmpty();
    }
    
    public boolean salvarRespondeTeste(RespondeExercicio responde) {

        try {
            em.getTransaction().begin();
            em.persist(responde);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean atualizarPontuacao(ParticipaGrupo participaGrupo) {

        try {
            em.getTransaction().begin();
            em.merge(participaGrupo);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }
    
    public List<ParticipaGrupo> listarGruposPendentes(String login) {
        Query query = em.createQuery("select p from ParticipaGrupo p where p.aluno.login = :login and p.aceito = false");
        query.setParameter("login", login);

        return (List<ParticipaGrupo>) query.getResultList();
    }
    
    public boolean participaGrupo(ParticipaGrupo participaGrupo) {

        try {
            em.getTransaction().begin();
            em.persist(participaGrupo);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }
    
    public boolean removerSolicitacao(String login, int codigoGrupo) {

        try {
            
            em.getTransaction().begin();
            Query query = em.createQuery("delete from ParticipaGrupo p where p.aluno.login = :login and p.grupo.codigo = :codigo");
            query.setParameter("login", login);
            query.setParameter("codigo", codigoGrupo);
            query.executeUpdate();
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }
    
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
}
