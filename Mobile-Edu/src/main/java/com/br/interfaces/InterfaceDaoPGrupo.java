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
    
    public boolean removerMembro(String login, int codigoGrupo);
    
    public List<Aluno> listarMembros(int codigoGrupo);
    
    public boolean verificaSeJaEhMembro(String login, int codigoGrupo);
    
    public List<ParticipaGrupo> listarGruposAluno(String login);
    
    public List<ParticipaGrupo> listarGruposPendentes(String login);
    
    public boolean verificaSolicitacao(String login, int codigoGrupo);
    
    public List<ParticipaGrupo> solicitacoesRecebidas(String loginProfessor);
    
    public boolean removerMembros(int codigoGrupo);
    
    public ParticipaGrupo buscarParticipaGrupo(String loginAluno, int codGrupo);
}
