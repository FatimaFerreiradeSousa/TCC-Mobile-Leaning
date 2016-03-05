package com.br.app.conection;

import com.br.entidades.Aluno;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fatinha de Sousa
 */
public class Connection {

    public static EntityManager conn() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.br_App-Entidades_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        
        return em;

    }
}
