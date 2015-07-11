package com.br.interfaces;

import com.br.entidades.Aluno;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */

public interface InterfaceDaoAluno {

    public boolean salvarAluno(Aluno aluno);
    
    public boolean atualizarAluno(Aluno aluno);
    
    public Aluno buscarAluno(String login);
    
    public boolean removerAluno(Aluno aluno);
    
    public Aluno loginAluno(String login, String senha);
    
    public Aluno buscarAlunoEmail(String email);
}
