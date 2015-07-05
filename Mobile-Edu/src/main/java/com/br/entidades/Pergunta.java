package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String enunciado;
    private int qtdRespostas;
    private String categoria;
    
    @OneToMany
    private List<Resposta> respostas;
    private float pontuacao;
    @ManyToMany(mappedBy = "questoesExercicios")
    private List<Teste> exerciciosQuestoes;
    @ManyToOne
    private Professor professor;
    
    public Pergunta(){
    
    }

    public Pergunta(int codigo, String enunciado, int qtdRespostas, String categoria, float pontuacao, Professor professor) {
        this.codigo = codigo;
        this.enunciado = enunciado;
        this.qtdRespostas = qtdRespostas;
        this.categoria = categoria;
        this.respostas = new ArrayList();
        this.pontuacao = pontuacao;
        this.exerciciosQuestoes = new ArrayList();
        this.professor = professor;
        this.respostas = new ArrayList();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.codigo);
        hash = 79 * hash + Objects.hashCode(this.enunciado);
        hash = 79 * hash + this.qtdRespostas;
        hash = 79 * hash + Objects.hashCode(this.categoria);
        hash = 79 * hash + Objects.hashCode(this.respostas);
        hash = 79 * hash + Float.floatToIntBits(this.pontuacao);
        hash = 79 * hash + Objects.hashCode(this.exerciciosQuestoes);
        hash = 79 * hash + Objects.hashCode(this.professor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pergunta other = (Pergunta) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.enunciado, other.enunciado)) {
            return false;
        }
        if (this.qtdRespostas != other.qtdRespostas) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.respostas, other.respostas)) {
            return false;
        }
        if (Float.floatToIntBits(this.pontuacao) != Float.floatToIntBits(other.pontuacao)) {
            return false;
        }
        if (!Objects.equals(this.exerciciosQuestoes, other.exerciciosQuestoes)) {
            return false;
        }
        if (!Objects.equals(this.professor, other.professor)) {
            return false;
        }
        return true;
    }
}
