package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
    private List<Arquivo> arquivos;
    @OneToMany(mappedBy = "professor")
    private List<Questao> questoes;
    @OneToMany(mappedBy = "professor")
    private List<Teste> testes;
    @ManyToMany
    private List<Comentario> comentarios;
    @OneToMany
    private List<Topico> topicosCriados;
    @OneToMany(mappedBy = "professorGrupos")
    private List<Grupo> gruposCriados;
    @OneToMany(mappedBy = "professor")
    private List<Anotacao> anotacoes;

    public Professor() {
    }

    public Professor(Date dataNascimento, String email, String login, String senha, 
            String nome, String instituicao, String foto, String descricao, Date dataParticipacao) {
        super(email, login, senha, nome, instituicao, foto, descricao, dataParticipacao);
        this.dataNascimento = dataNascimento;
        this.arquivos = new ArrayList();
        this.questoes = new ArrayList();
        this.testes = new ArrayList();
        this.comentarios = new ArrayList();
        this.topicosCriados = new ArrayList();
        this.gruposCriados = new ArrayList();
        this.anotacoes = new ArrayList();
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Teste> getTestes() {
        return testes;
    }

    public void setTestes(List<Teste> testes) {
        this.testes = testes;
    }

    public List<Topico> getTopicosCriados() {
        return topicosCriados;
    }

    public void setTopicosCriados(List<Topico> topicosCriados) {
        this.topicosCriados = topicosCriados;
    }

    public List<Grupo> getGruposCriados() {
        return gruposCriados;
    }

    public void setGruposCriados(List<Grupo> gruposCriados) {
        this.gruposCriados = gruposCriados;
    }   

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }
}
