package com.br.daos;

import com.br.entidades.Arquivo;
import com.br.interfaces.InterfaceDaoArquivo;
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
public class DaoArquivo implements InterfaceDaoArquivo {

    @PersistenceContext(unitName = "Sistema-TCC-UP")
    private EntityManager em;

    @Override
    public boolean salvar(Arquivo arquivo) {

        try {
            em.persist(arquivo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Arquivo consultar(String codigo) {

        try {
            return em.find(Arquivo.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remover(Arquivo arquivo) {

        try {
            em.remove(em.merge(arquivo));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Arquivo> listarArquivos(String login) {
        Query query = em.createQuery("select a from Arquivo a where a.professor.login = :login");
        query.setParameter("login", login);

        return (List<Arquivo>) query.getResultList();
    }
}
