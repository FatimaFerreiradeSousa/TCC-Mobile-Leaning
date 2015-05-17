package com.br.gerenciadores;

import com.br.entidades.Questao;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */

public interface InterfaceGerenciadorQuestao {

    public boolean addQuestaoTeste(Questao questao);
    
    public boolean deletarQuestao(Questao questao);
    
    public void concluir();
    
    public List<Questao> listarQuestoes();
    
}
