package com.br.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Presenca implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;
    private String horaAula;
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date dataPreseca;
    @OneToOne
    private Turma turma;
    @OneToOne
    private Aluno aluno;
    
    public Presenca(){
        this.dataPreseca = new Date();
    }

    public Presenca(boolean status, String horaAula, String descricao, Turma turma, Aluno aluno) {
        this.status = status;
        this.horaAula = horaAula;
        this.descricao = descricao;
        this.dataPreseca = new Date();
        this.turma = turma;
        this.aluno = aluno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getHoraAula() {
        return horaAula;
    }

    public void setHoraAula(String horaAula) {
        this.horaAula = horaAula;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataPreseca() {
        return dataPreseca;
    }

    public void setDataPreseca(Date dataPreseca) {
        this.dataPreseca = dataPreseca;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
