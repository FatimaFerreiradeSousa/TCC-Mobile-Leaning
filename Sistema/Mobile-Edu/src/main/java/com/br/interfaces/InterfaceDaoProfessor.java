package com.br.interfaces;

import com.br.entidades.Professor;

/**
 *
 * @author Fatinha de Sousa
 */

public interface InterfaceDaoProfessor {
    
    public boolean salvar(Professor professor);
    
    public boolean atualizar(Professor professor);
    
    public boolean remover(Professor professor);
    
    public Professor buscarProfessor(String login);
    
    public Professor loginProfessor(String login, String senha);
    
    public Professor buscarProfessorEmail(String email);
    
}
