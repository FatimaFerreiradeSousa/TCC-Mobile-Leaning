package com.br.app.conection;

import com.br.dao.Dao;
import com.br.entidades.*;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public class App {
    
    public static void main(String[] args) {
        
        Dao daoAluno = new Dao();
        List<Grupo> t = daoAluno.pesquisarGrupoPorNome("História");
        
        for (Grupo grupo : t) {
            System.out.println(grupo.getNome());
        }
        
    }
    
}
