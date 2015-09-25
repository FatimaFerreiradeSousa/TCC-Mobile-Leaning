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
    private String loginDestinatario;
    private String loginUsuario;
    
    public Notificacao(){
    }

    public Notificacao(String mensagem, Date dataNot, boolean lido, String loginDestinatario, String loginUsuario) {
        this.mensagem = mensagem;
        this.dataNot = dataNot;
        this.lido = lido;
        this.loginDestinatario = loginDestinatario;
        this.loginUsuario = loginUsuario;
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

    public String getLoginDestinatario() {
        return loginDestinatario;
    }

    public void setLoginDestinatario(String loginDestinatario) {
        this.loginDestinatario = loginDestinatario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }
}
