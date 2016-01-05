package com.br.interfaces;

import com.br.entidades.Teste;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceExercicio {

    public boolean salvar(Teste exercicio);
    
    public boolean atualizar(Teste exercicio);
    
    public Teste consultar(int codigo);
    
    public boolean remover(Teste exercicio);
    
    public List<Teste> testesCadastradosProfessor(String login);
    
}
