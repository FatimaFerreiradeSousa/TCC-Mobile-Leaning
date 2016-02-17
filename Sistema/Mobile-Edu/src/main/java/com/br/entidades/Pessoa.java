package com.br.entidades;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Fatinha de Sousa
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa implements Serializable {

    @Id
    private String login;
    private String senha;
    private String nome;
    private String sobrenome;
    private String instituicao;
    private String foto;
    private String descricao;
    
    @Column(unique = true)
    private String email;
    
    @Temporal(TemporalType.DATE)
    private Date dataParticipacao;
    
    @Transient
    private StreamedContent content;

    public Pessoa() {

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataParticipacao() {
        return dataParticipacao;
    }

    public void setDataParticipacao(Date dataParticipacao) {
        this.dataParticipacao = dataParticipacao;
    }

    public StreamedContent getContent() {

        File fotoPerfil = new File(foto);

        try {

            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fotoPerfil));
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            this.content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/jpeg");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.login);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }
}
