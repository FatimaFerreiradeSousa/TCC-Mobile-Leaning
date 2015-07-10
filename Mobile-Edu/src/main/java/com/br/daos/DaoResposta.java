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
    public boolean removerResposta(List<Resposta> respostas) {

        try {
            for (Resposta r : respostas) {
                em.remove(em.merge(r));
            }
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
}
