package com.br.daos;

import com.br.entidades.Horario;
import com.br.interfaces.InterfaceDaoHorario;
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
public class DaoHorario implements InterfaceDaoHorario{

    @PersistenceContext(unitName = "Mobile-Edu-BD")
    private EntityManager em;
    
    @Override
    public boolean salvarHorario(Horario horario) {
        
        try{
            em.persist(horario);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterarHorario(Horario horario) {
        try{
            em.merge(horario);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removerHorario(Horario horario) {
        try{
            em.remove(em.merge(horario));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Horario> consultarHorario(String dia) {
        Query query = em.createQuery("SELECT h FROM Horario h where h.dia = :dia");
        query.setParameter("dia", dia);
        
        return (List<Horario>) query.getResultList();
    }
}
