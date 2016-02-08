package com.br.interfaces;

import com.br.entidades.Horario;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoHorario {

    public boolean salvarHorario(Horario horario);
    
    public boolean alterarHorario(Horario horario);
    
    public boolean removerHorario(Horario horario);
    
    public List<Horario> consultarHorario(String dia, String turma);
}
