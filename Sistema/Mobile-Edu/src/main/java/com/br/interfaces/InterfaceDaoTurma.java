package com.br.interfaces;

import com.br.entidades.Turma;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoTurma {
    
    public boolean salvarTurma(Turma turma);
    
    public boolean atualizarTurma(Turma turma);
    
    public boolean removerTurma(Turma turma);
    
    public Turma buscarTurma(String codigo);
    
    public List<Turma> listarTurmas(String login);
    
}
