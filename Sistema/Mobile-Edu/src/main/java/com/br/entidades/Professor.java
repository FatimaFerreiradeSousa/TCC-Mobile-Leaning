package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */
@Entity
public class Professor extends Pessoa implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @OneToMany(mappedBy = "professor")
    private List<Pergunta> questoes;
    @OneToMany(mappedBy = "professor")
    private List<Teste> testes;
    @OneToMany(mappedBy = "professorGrupos")
    private List<Grupo> gruposCriados;

    @OneToMany(mappedBy = "professor")
    private List<Turma> turmas;

    public Professor() {
        this.questoes = new ArrayList();
        this.testes = new ArrayList();
        this.gruposCriados = new ArrayList();
        this.turmas = new ArrayList();
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Pergunta> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Pergunta> questoes) {
        this.questoes = questoes;
    }

    public List<Teste> getTestes() {
        return testes;
    }

    public void setTestes(List<Teste> testes) {
        this.testes = testes;
    }

    public List<Grupo> getGruposCriados() {
        return gruposCriados;
    }

    public void setGruposCriados(List<Grupo> gruposCriados) {
        this.gruposCriados = gruposCriados;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
