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

    @PersistenceContext(unitName = "Sistema-TCC-UP")
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
        Query query = em.createQuery("select r from Resposta r, Questao q where r.questao.codigo = :codigo and r.questao.codigo = q.codigo");
        query.setParameter("codigo", codigoQuestao);
        respostas = (List<Resposta>) query.getResultList();
        return respostas;
    }

    @Override
    public Resposta buscarRespostaCodigo(int codigo) {

        try {
            return em.find(Resposta.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
