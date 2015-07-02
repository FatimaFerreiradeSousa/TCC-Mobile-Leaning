package com.br.interfaces;

import com.br.entidades.Topico;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoTopico {

    public boolean salvar(Topico topico);
    
    public boolean atualizar(Topico topico);
    
    public Topico consultar(String codigo);
    
    public boolean remover(Topico topico);
    
}
