package com.br.util;

import java.util.Date;

/**
 *
 * @author Fatinha de Sousa
 */
public class GrupoJson {
    
    private int codigo;
    private String nome;
    private Date dataCriacao;
    private String descricao;
    private String professorNome;
    private String professorSobrenome;
    private boolean status;

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

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    public String getProfessorSobrenome() {
        return professorSobrenome;
    }

    public void setProfessorSobrenome(String professorSobrenome) {
        this.professorSobrenome = professorSobrenome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
