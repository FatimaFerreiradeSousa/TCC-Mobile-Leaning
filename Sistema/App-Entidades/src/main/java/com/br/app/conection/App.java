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
        
        Dao dao = new Dao();

        Topico topico = new Topico();
        topico.setCodigo(24);
        topico.setConteudo("A morte não e nada, eu somente passei para o outro lado do caminho...");
        
        System.out.println(dao.alterarTopico(topico));
    }
    
}
