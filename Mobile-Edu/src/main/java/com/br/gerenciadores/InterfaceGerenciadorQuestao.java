package com.br.gerenciadores;

import com.br.entidades.Pergunta;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */

public interface InterfaceGerenciadorQuestao {

    public boolean addQuestaoTeste(Pergunta questao);
    
    public boolean deletarQuestao(Pergunta questao);
    
    public void concluir();
    
    public List<Pergunta> listarQuestoes();
    
}
