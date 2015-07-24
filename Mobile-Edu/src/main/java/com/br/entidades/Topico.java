package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class Topico implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String conteudo;
    private String nome;
    private String caminho;
    private String tipo;
    private int codigoTeste;
    private String loginUsuario;
    private boolean disponivel;
    private int qtdDownloads;
    
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    
    @OneToMany(mappedBy = "topico")
    private List<Comentario> comentarios;
    
    @ManyToOne
    private Grupo grupo;
    
    public Topico(){
    
    }

    public Topico(String conteudo, String nome, String caminho, String tipo, int codigoTeste,
            String loginUsuario, boolean disponivel, int qtdDownloads, Date dataCriacao, Grupo grupo, Pessoa pessoa) {
        this.conteudo = conteudo;
        this.nome = nome;
        this.caminho = caminho;
        this.tipo = tipo;
        this.codigoTeste = codigoTeste;
        this.loginUsuario = loginUsuario;
        this.disponivel = disponivel;
        this.qtdDownloads = qtdDownloads;
        this.dataCriacao = dataCriacao;
        this.comentarios = new ArrayList();
        this.grupo = grupo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getCodigoTeste() {
        return codigoTeste;
    }

    public void setCodigoTeste(int codigoTeste) {
        this.codigoTeste = codigoTeste;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getQtdDownloads() {
        return qtdDownloads;
    }

    public void setQtdDownloads(int qtdDownloads) {
        this.qtdDownloads = qtdDownloads;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}