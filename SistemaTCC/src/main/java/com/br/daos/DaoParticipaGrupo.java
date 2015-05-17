package com.br.daos;

import com.br.entidades.ParticipaGrupo;
import com.br.interfaces.InterfaceDaoPGrupo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fatinha
 */

@Stateless
public class DaoParticipaGrupo implements InterfaceDaoPGrupo{
    @PersistenceContext(unitName = "Sistema-TCC-UP")
    private EntityManager em;
    
    @Override
    public boolean participaGrupo(ParticipaGrupo participaGrupo) {
        
        try{
            em.persist(participaGrupo);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean atualizarSolicitacao(ParticipaGrupo participaGrupo) {
        
        try{
            em.merge(participaGrupo);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
