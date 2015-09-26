package com.br.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Notificacao implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mensagem;
    @Temporal(TemporalType.DATE)
    private Date dataNot;
    private boolean lido;
    private String loginAluno;
    private String loginProfessor;
    
    public Notificacao(){
    }

    public Notificacao(String mensagem, Date dataNot, boolean lido, String loginAluno, String loginProfessor) {
        this.mensagem = mensagem;
        this.dataNot = dataNot;
        this.lido = lido;
        this.loginAluno = loginAluno;
        this.loginProfessor = loginProfessor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataNot() {
        return dataNot;
    }

    public void setDataNot(Date dataNot) {
        this.dataNot = dataNot;
    }

    public boolean isLido() {
        return lido;
    }

    public void setLido(boolean lido) {
        this.lido = lido;
    }

    public String getLoginAluno() {
        return loginAluno;
    }

    public void setLoginAluno(String loginAluno) {
        this.loginAluno = loginAluno;
    }

    public String getLoginProfessor() {
        return loginProfessor;
    }

    public void setLoginProfessor(String loginProfessor) {
        this.loginProfessor = loginProfessor;
    }
}
