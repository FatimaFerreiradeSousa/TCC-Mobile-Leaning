package com.br.interfaces;

import com.br.entidades.ParticipaGrupo;

/**
 *
 * @author Fatinha
 */

public interface InterfaceDaoPGrupo {

    public boolean participaGrupo(ParticipaGrupo participaGrupo);
    
    public boolean atualizarSolicitacao(ParticipaGrupo participaGrupo);
}
