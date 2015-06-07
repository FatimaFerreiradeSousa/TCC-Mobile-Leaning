package com.br.fachada;

import com.br.daos.DaoArquivo;
import com.br.entidades.Arquivo;
import com.br.interfaces.InterfaceDaoArquivo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Fatinha
 */

@Stateful
public class FachadaModArquivo implements Serializable{
    
    @EJB
    private InterfaceDaoArquivo interfaceDaoArquivo;
    
    public FachadaModArquivo(){
        interfaceDaoArquivo = new DaoArquivo();
    }
    
    public void salvarArquivo(Arquivo arquivo){
        interfaceDaoArquivo.salvar(arquivo);
    }
    
    public void removerArquivo(Arquivo arquivo){
        interfaceDaoArquivo.remover(arquivo);
    }
    
    public List<Arquivo> listarArquivos(String login){
        return interfaceDaoArquivo.listarArquivos(login);
    }
    
}
