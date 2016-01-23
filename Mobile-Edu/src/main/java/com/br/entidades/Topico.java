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
    private String caminho;
    private String loginUsuario;
    private int maisUm;
    private String tipo;
    private String nome;
    
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    
    @OneToMany(mappedBy = "topico")
    private List<Comentario> comentarios;
    
    @ManyToOne
    private Grupo grupo;
    
    public Topico(){
    
    }

    public Topico(int codigo, String conteudo, String caminho, String loginUsuario, 
            int maisUm, String tipo, String nome, Date dataCriacao, Grupo grupo) {
        this.codigo = codigo;
        this.conteudo = conteudo;
        this.caminho = caminho;
        this.loginUsuario = loginUsuario;
        this.maisUm = maisUm;
        this.tipo = tipo;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.grupo = grupo;
        this.comentarios = new ArrayList();
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

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public int getMaisUm() {
        return maisUm;
    }

    public void setMaisUm(int maisUm) {
        this.maisUm = maisUm;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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