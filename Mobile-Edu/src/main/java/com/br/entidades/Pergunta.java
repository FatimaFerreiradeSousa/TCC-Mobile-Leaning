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
public class Pergunta implements Serializable{
    
    @Id
    private String codigo;
    private String enunciado;
    private int qtdRespostas;
    
    @OneToMany
    private List<Resposta> respostas;
    private float pontuacao;
    @ManyToMany(mappedBy = "questoesExercicios")
    private List<Teste> exerciciosQuestoes;
    @ManyToOne
    private Professor professor;
    
    public Pergunta(){
    
    }

    public Pergunta(String codigo, String enunciado, int qtdRespostas, float pontuacao, Professor professor) {
        this.codigo = codigo;
        this.enunciado = enunciado;
        this.qtdRespostas = qtdRespostas;
        this.respostas = new ArrayList();
        this.pontuacao = pontuacao;
        this.exerciciosQuestoes = new ArrayList();
        this.professor = professor;
        this.respostas = new ArrayList();
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

    public int getQtdRespostas() {
        return qtdRespostas;
    }

    public void setQtdRespostas(int qtdRespostas) {
        this.qtdRespostas = qtdRespostas;
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
