package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Turma implements Serializable{
    
    @Id
    private String codigo;
    private String nome;
    private String categoria;
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Temporal(TemporalType.DATE)
    private Date dataTerminio;
    
    @ManyToMany
    private List<Aluno> alunos;
    @ManyToOne
    private Professor professor;
    @OneToMany(mappedBy = "turma")
    private List<Horario> horarios;
    
    public Turma(){
        
    }

    public Turma(String codigo, String nome, String categoria, String descricao, Date dataInicio, Date dataTerminio,
            Professor professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminio = dataTerminio;
        this.alunos = new ArrayList();
        this.professor = professor;
        this.horarios = new ArrayList();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTerminio() {
        return dataTerminio;
    }

    public void setDataTerminio(Date dataTerminio) {
        this.dataTerminio = dataTerminio;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
