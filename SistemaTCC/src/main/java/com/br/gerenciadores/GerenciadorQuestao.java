package com.br.gerenciadores;

import com.br.entidades.Questao;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Fatinha de Sousa
 */

@Stateless
public class GerenciadorQuestao implements InterfaceGerenciadorQuestao{

    private List<Questao> questoes = new ArrayList();
    
    public GerenciadorQuestao(){
        questoes = new ArrayList();
    }

    @Override
    public boolean addQuestaoTeste(Questao questao) {
        questoes.add(questao);
        return true;
    }

    @Override
    public boolean deletarQuestao(Questao questao) {
        Questao q = new Questao();
        
        for(Questao qt: questoes){
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
    public List<Questao> listarQuestoes() {
        return questoes;
    }
}
