package com.br.interfaces;

import com.br.entidades.Presenca;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoPresenca {
    
    public boolean salvarPresenca(Presenca presenca);
    
    public boolean atualizarPresenca(Presenca presenca);
    
    public boolean removerPresenca(Presenca presenca);
    
    public List<Presenca> listarPresencasPorData(Date data);
    
    public List<Presenca> listarPresencasAluno(String login);
    
    public List<Presenca> listarPresencasTurma(String turma);
    
    public int qtdFaltas(String login, String turma);
    
    public List<Presenca> listarPresencaPorHorario(Date data, String horaInicio);
}
