package com.br.interfaces;

import com.br.entidades.Grupo;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoGrupo {

    public boolean salvar(Grupo grupo);
    
    public boolean atualizar(Grupo grupo);
    
    public Grupo consultar(String codigo);
    
    public boolean remover(Grupo grupo);
    
    public List<Grupo> gruposProfessor(String login);
}
