package com.br.interfaces;

import com.br.entidades.RespondeExercicio;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoRespondeExercicio {

    public boolean salvar(RespondeExercicio responde);
    
    public boolean atualiza(RespondeExercicio responde);
    
    public RespondeExercicio consulta(String codigo);
    
    public boolean remover(RespondeExercicio responde);
    
    public boolean respondeTeste(int codTeste, String loginAluno);
    
    public List<RespondeExercicio> resultados(int codTeste);
    
    public List<RespondeExercicio> listarExcerciciosAluno(String login);
}
