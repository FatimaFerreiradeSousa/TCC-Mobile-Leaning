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
        List<Horario> horariosTurma = dao.consultarHorario("Segunda-Feira", "GEO-2016");
        
        System.out.println("Size: "+horariosTurma.size());

        
    }
    
}
