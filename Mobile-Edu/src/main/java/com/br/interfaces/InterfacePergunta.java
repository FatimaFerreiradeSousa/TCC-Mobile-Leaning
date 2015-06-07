package com.br.interfaces;

import com.br.entidades.Pergunta;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfacePergunta {

    public boolean salvar(Pergunta questao);
    
    public boolean atualizar(Pergunta questao);
    
    public Pergunta consultar(String codigo);
    
    public boolean remover(Pergunta questao);
    
    public List<Pergunta> listarQuestoes(String login);
}
