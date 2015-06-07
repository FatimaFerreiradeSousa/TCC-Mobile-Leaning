package com.br.daos;

import com.br.entidades.Resposta;
import com.br.interfaces.InterfaceDaoResposta;
import java.util.ArrayList;
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
public class DaoResposta implements InterfaceDaoResposta {

    @PersistenceContext(unitName = "Mobile-Edu-UP")
    private EntityManager em;

    @Override
    public boolean salvarResposta(Resposta resposta) {

        try {
            em.persist(resposta);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizarResposta(Resposta resposta) {

        try {
            em.merge(resposta);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removerResposta(Resposta resposta) {

        try {
            em.remove(em.merge(resposta));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Resposta> listarResposta(String codigoQuestao) {
        List<Resposta> respostas = new ArrayList();
        Query query = em.createQuery("select r from Resposta r, Pergunta p where r.pergunta.codigo = :codigo and r.pergunta.codigo = p.codigo");
        query.setParameter("codigo", codigoQuestao);
        respostas = (List<Resposta>) query.getResultList();
        return respostas;
    }
    
    @Override
    public Resposta buscarResposta(String codigo){
        
        try{
            Query query = em.createQuery("select r from Resposta r where r.pergunta.codigo = :codigo");
            query.setParameter("codigo", codigo);
            
            List<Resposta> resposta = query.getResultList();
            
            if(resposta.size() > 0){
                return resposta.get(0);
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
