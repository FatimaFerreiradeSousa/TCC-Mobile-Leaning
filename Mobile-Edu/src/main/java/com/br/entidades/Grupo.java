package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Grupo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    private String descricao;
    @OneToMany(mappedBy = "grupo")
    private List<Topico> topicos;
    @ManyToOne
    private Professor professorGrupos;
    @OneToMany(mappedBy = "grupoArquivo")
    private List<Arquivo> meusArquivos;
    
    public Grupo(){
    
    }

    public Grupo(String nome, Date dataCriacao, String descricao, Professor professor) {
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.topicos = new ArrayList();
        this.professorGrupos = professor;
        this.meusArquivos = new ArrayList();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    public Professor getProfessorGrupos() {
        return professorGrupos;
    }

    public void setProfessorGrupos(Professor professorGrupos) {
        this.professorGrupos = professorGrupos;
    }

    public List<Arquivo> getMeusArquivos() {
        return meusArquivos;
    }

    public void setMeusArquivos(List<Arquivo> meusArquivos) {
        this.meusArquivos = meusArquivos;
    }
}