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
    public boolean removerMembro(ParticipaGrupo participaGrupo){
        
        try{
            em.remove(em.merge(participaGrupo));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Aluno> listarMembros(int codigoGrupo){
        String sql = "select q from Hotel h JOIN h.quartos q WHERE h.codigo = :codHotel";
        Query query = em.createQuery("select a from ParticipaGrupo p INNER JOIN p.aluno a where p.aceito = TRUE and p.grupo.codigo = :codGrupo");
        query.setParameter("codGrupo", codigoGrupo);
        
        return (List<Aluno>) query.getResultList();
    }
}
