package com.br.daos;

import com.br.entidades.Resposta;
import com.br.interfaces.InterfaceDaoResposta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fatinha de Sousa
 */
@Stateless
public class DaoResposta implements InterfaceDaoResposta {

    @PersistenceContext(unitName = "Mobile-Edu-BD")
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
    public boolean removerResp(Resposta resposta) {

        try {
            em.remove(em.merge(resposta));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
