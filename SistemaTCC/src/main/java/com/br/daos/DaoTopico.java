package com.br.daos;

import com.br.entidades.Topico;
import com.br.interfaces.InterfaceDaoTopico;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fatinha de Sousa
 */
@Stateless
public class DaoTopico implements InterfaceDaoTopico {

    @PersistenceContext(unitName = "Sistema-TCC-UP")
    private EntityManager em;

    @Override
    public boolean salvar(Topico topico) {

        try {
            em.persist(topico);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizar(Topico topico) {

        try {
            em.merge(topico);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Topico consultar(String codigo) {

        try {
            return em.find(Topico.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remover(Topico topico) {

        try {
            em.remove(em.merge(topico));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
