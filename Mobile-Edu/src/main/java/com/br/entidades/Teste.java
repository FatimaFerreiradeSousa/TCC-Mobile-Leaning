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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Teste implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String disciplina;
    private String assunto;
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;
    private String categoria;
    private int qtdPerguntas;
    
    @ManyToMany
    private List<Pergunta> questoesExercicios;
    @ManyToOne
    private Professor professor;
    
    @ManyToMany(mappedBy = "testesGrupo")
    private List<Grupo> grupos;
    
    public Teste(){
    
    }

    public Teste(String disciplina, String assunto, Date dataEntrega, String categoria,
            int qtdPerguntas, Professor professor) {
        this.disciplina = disciplina;
        this.assunto = assunto;
        this.dataEntrega = dataEntrega;
        this.categoria = categoria;
        this.qtdPerguntas = qtdPerguntas;
        this.questoesExercicios = new ArrayList();
        this.professor = professor;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQtdPerguntas() {
        return qtdPerguntas;
    }

    public void setQtdPerguntas(int qtdPerguntas) {
        this.qtdPerguntas = qtdPerguntas;
    }

    public List<Pergunta> getQuestoesExercicios() {
        return questoesExercicios;
    }

    public void setQuestoesExercicios(List<Pergunta> questoesExercicios) {
        this.questoesExercicios = questoesExercicios;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Teste other = (Teste) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
}
