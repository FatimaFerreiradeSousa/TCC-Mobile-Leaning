package com.br.interfaces;

import com.br.entidades.Nota;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoNota {

    public boolean salvarNota(Nota nota);
    
    public Nota buscarNota(int codigo);
    
    public boolean removerNota(Nota nota);
    
    public boolean alterarNota(Nota nota);
    
    public List<Nota> listarNotas(String turma);
}
