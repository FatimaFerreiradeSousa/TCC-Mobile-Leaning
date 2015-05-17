package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Questao implements Serializable{
    
    @Id
    private String codigo;
    private String enunciado;
    @OneToMany(mappedBy = "questao")
    private List<Resposta> respostas;
    private float pontuacao;
    @ManyToMany(mappedBy = "questoesExercicios")
    private List<Teste> exerciciosQuestoes;
    @ManyToOne
    private Professor professor;
    
    public Questao(){
    
    }

    public Questao(String codigo, String enunciado, float pontuacao, Professor professor) {
        this.codigo = codigo;
        this.enunciado = enunciado;
        this.respostas = new ArrayList();
        this.pontuacao = pontuacao;
        this.exerciciosQuestoes = new ArrayList();
        this.professor = professor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public float getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(float pontuacao) {
        this.pontuacao = pontuacao;
    }

    public List<Teste> getExerciciosQuestoes() {
        return exerciciosQuestoes;
    }

    public void setExerciciosQuestoes(List<Teste> exerciciosQuestoes) {
        this.exerciciosQuestoes = exerciciosQuestoes;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }
}
