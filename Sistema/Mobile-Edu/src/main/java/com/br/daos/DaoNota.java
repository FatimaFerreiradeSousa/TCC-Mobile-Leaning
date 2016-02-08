package com.br.daos;

import com.br.entidades.Nota;
import com.br.interfaces.InterfaceDaoNota;
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
public class DaoNota implements InterfaceDaoNota {

    @PersistenceContext(unitName = "Mobile-Edu-BD")
    private EntityManager em;

    @Override
    public boolean salvarNota(Nota nota) {
        try {
            em.persist(nota);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Nota buscarNota(int codigo) {
        try {
            return em.find(Nota.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean removerNota(Nota nota) {
        try {
            em.remove(em.merge(nota));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterarNota(Nota nota) {
        try {
            em.merge(nota);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Nota> listarNotas(String turma) {
        Query query = em.createQuery("SELECT n FROM Nota n where n.turma.codigo = :turma");
        query.setParameter("turma", turma);
        
        return (List<Nota>) query.getResultList();
    }

}
