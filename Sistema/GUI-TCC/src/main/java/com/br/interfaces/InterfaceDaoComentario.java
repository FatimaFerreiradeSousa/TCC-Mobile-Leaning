package com.br.interfaces;

import com.br.entidades.Comentario;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoComentario {

    public boolean salvar(Comentario comentario);
    
    public boolean atualizar(Comentario comentario);
    
    public Comentario consultar(int codigo);
    
    public boolean remover(Comentario comentario);
    
}
