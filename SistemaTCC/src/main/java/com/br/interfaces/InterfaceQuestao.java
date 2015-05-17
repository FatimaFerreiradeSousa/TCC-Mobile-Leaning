package com.br.interfaces;

import com.br.entidades.Questao;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceQuestao {

    public boolean salvar(Questao questao);
    
    public boolean atualizar(Questao questao);
    
    public Questao consultar(String codigo);
    
    public boolean remover(Questao questao);
    
    public List<Questao> listarQuestoes(String login);
}
