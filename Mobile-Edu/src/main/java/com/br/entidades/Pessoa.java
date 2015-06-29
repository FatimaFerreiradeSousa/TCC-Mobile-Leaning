package com.br.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa implements Serializable{
    
    @Id
    private String login;
    private String senha;
    private String nome;
    private String instituicao;
    private String foto;
    @Column(unique = true)
    private String email;
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date dataParticipacao;
    
    public Pessoa(){
    
    }

    public Pessoa(String login, String senha, String nome, String instituicao, 
            String foto, String email, String descricao, Date dataParticipacao) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.instituicao = instituicao;
        this.foto = foto;
        this.email = email;
        this.descricao = descricao;
        this.dataParticipacao = dataParticipacao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Date getDataParticipacao(){
        return dataParticipacao;
    }
    
    public void setDataParticipacao(Date dataParticipacao){
        this.dataParticipacao = dataParticipacao;
    }
}