package com.br.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Aluno extends Pessoa implements Serializable{
    
    private String curso;
    
    public Aluno(){
    
    }

    public Aluno(String curso, String email, String login, String senha, 
            String nome, String instituicao, String foto, Date dataParticipacao) {
        super(email, login, senha, nome, instituicao, foto, dataParticipacao);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
