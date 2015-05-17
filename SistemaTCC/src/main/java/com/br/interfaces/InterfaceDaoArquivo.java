package com.br.interfaces;

import com.br.entidades.Arquivo;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public interface InterfaceDaoArquivo {

    public boolean salvar(Arquivo arquivo);
    
    public Arquivo consultar(String codigo);
    
    public boolean remover(Arquivo arquivo);
    
    public List<Arquivo> listarArquivos(String professor);
}
