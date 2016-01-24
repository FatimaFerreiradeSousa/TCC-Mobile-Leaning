package com.br.interfaces;

import com.br.entidades.Resposta;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoResposta {

    public boolean salvarResposta(Resposta resposta);
    
    public boolean atualizarResposta(Resposta resposta);
    
    public boolean removerResposta(List<Resposta> respostas);
    
    public List<Resposta> listarResposta(String codigoQuestao);
   
}
