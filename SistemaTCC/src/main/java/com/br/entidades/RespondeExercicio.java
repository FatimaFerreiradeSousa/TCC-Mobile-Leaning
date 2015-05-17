package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class RespondeExercicio implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float nota;
    @Temporal(TemporalType.DATE)
    private Date dataResposta;
    @ManyToMany(mappedBy = "respondeExercicio")
    private List<Aluno> alunos;
    @ManyToMany(mappedBy = "exerciciosRespondidos")
    private List<Teste> exercicios;
    
    public RespondeExercicio(){
    
    }

    public RespondeExercicio(float nota, Date dataResposta) {
        this.nota = nota;
        this.dataResposta = dataResposta;
        this.alunos = new ArrayList();
        this.exercicios = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Date getDataResposta(){
        return dataResposta;
    }
    
    public void setDataResposta(Date dataResposta){
        this.dataResposta = dataResposta;
    }
    
    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Teste> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Teste> exercicios) {
        this.exercicios = exercicios;
    }
}
