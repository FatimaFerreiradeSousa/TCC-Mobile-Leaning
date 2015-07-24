package com.br.daos;

import com.br.entidades.Comentario;
import com.br.interfaces.InterfaceDaoComentario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Fatinha de Sousa
 */
@Stateless
public class DaoComentario implements InterfaceDaoComentario {

    @PersistenceContext(unitName = "Mobile-Edu-UP")
    private EntityManager em;

    @Override
    public boolean salvar(Comentario comentario) {

        try {
            em.persist(comentario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizar(Comentario comentario) {

        try {
            em.merge(comentario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Comentario consultar(String codigo) {

        try {
            return em.find(Comentario.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remover(Comentario comentario) {

        try {
            em.remove(em.merge(comentario));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}