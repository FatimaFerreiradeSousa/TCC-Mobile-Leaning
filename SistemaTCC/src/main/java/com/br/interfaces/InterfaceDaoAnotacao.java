package com.br.interfaces;

import com.br.entidades.Anotacao;
import com.br.entidades.Professor;
import java.util.List;

/**
 *
 * @author Fatinha
 */

public interface InterfaceDaoAnotacao {

    public boolean salvarAnotacao(Anotacao anotacao);
    
    public boolean atualizarAnotacao(Anotacao anotacao);
    
    public boolean removerAnotacao(Anotacao anotacao);
    
    public List<Anotacao> listarAnotacao(Professor professor);
    
    public List<Anotacao> listarAnotacaoSemana(Professor professor);
}
