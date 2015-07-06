package com.br.interfaces;

import com.br.entidades.Aluno;
import com.br.entidades.ParticipaGrupo;
import java.util.List;

/**
 *
 * @author Fatinha
 */

public interface InterfaceDaoPGrupo {

    public boolean addMembro(ParticipaGrupo participaGrupo);
    
    public boolean atualizarSolicitacao(ParticipaGrupo ParticipaGrupo);
    
    public boolean removerMembro(ParticipaGrupo participaGrupo);
    
    public List<Aluno> listarMembros(int codigoGrupo);
}
