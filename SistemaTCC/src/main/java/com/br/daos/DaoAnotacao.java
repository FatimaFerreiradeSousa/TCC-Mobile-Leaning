package com.br.daos;

import com.br.entidades.Anotacao;
import com.br.entidades.Professor;
import com.br.interfaces.InterfaceDaoAnotacao;
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
public class DaoAnotacao implements InterfaceDaoAnotacao{
    
    @PersistenceContext(unitName = "Sistema-TCC-UP")
    private EntityManager em;

    @Override
    public boolean salvarAnotacao(Anotacao anotacao) {
        
        try{
           em.persist(anotacao);
           return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizarAnotacao(Anotacao anotacao) {
        
        try{
            em.merge(anotacao);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removerAnotacao(Anotacao anotacao) {
        
        try{
            em.remove(anotacao);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Anotacao> listarAnotacao(Professor professor) {
        Query query = em.createQuery("select a from Anotacao a where a.professor.login := login");
        query.setParameter("aluno", professor.getLogin());
        
        return (List<Anotacao>) query.getResultList();
    }
}
