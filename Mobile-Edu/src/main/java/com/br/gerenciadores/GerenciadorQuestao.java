package com.br.gerenciadores;

import com.br.entidades.Pergunta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Fatinha de Sousa
 */

@Stateless
public class GerenciadorQuestao implements InterfaceGerenciadorQuestao{

    private List<Pergunta> questoes = new ArrayList();
    
    public GerenciadorQuestao(){
        questoes = new ArrayList();
    }

    @Override
    public boolean addQuestaoTeste(Pergunta questao) {
        questoes.add(questao);
        return true;
    }

    @Override
    public boolean deletarQuestao(Pergunta questao) {
        Pergunta q = new Pergunta();
        
        for(Pergunta qt: questoes){
            if(qt.getCodigo().equalsIgnoreCase(questao.getCodigo())){
                q = qt;
            }
        }
        
        questoes.remove(q);
        return true;
    }

    @Override
    public void concluir() {
        questoes = new ArrayList();
    }

    @Override
    public List<Pergunta> listarQuestoes() {
        return questoes;
    }
}
