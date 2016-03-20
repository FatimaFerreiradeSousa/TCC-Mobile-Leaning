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
        
        System.out.println("Opção: " +dao.verificaSeJaEhMembro("aliu", 77));
    }
    
}
