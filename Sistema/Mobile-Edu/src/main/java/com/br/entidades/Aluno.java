package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Aluno extends Pessoa implements Serializable{
    
    private String curso;
    
    @ManyToMany(mappedBy = "alunos")
    private List<Turma> turmas;
    
    public Aluno(){
        this.turmas = new ArrayList();
    }

    public Aluno(String curso, String email, String login, String senha, 
            String nome, String instituicao, String foto, Date dataParticipacao) {
        super(email, login, senha, nome, instituicao, foto, dataParticipacao);
        this.curso = curso;
        this.turmas = new ArrayList();
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
