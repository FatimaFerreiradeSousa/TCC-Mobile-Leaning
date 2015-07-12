package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private String foto;
    private String caminho;
    private String tipo;
    private int codigoTeste;
    
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @OneToMany(mappedBy = "topico")
    private List<Comentario> comentarios;
    @ManyToOne
    private Grupo grupo;
    @ManyToOne
    private Pessoa pessoa;
    
    public Topico(){
    
    }

    public Topico(String conteudo, String nome, String foto, String caminho, String tipo, int codigoTeste,
            Date dataCriacao, Grupo grupo, Pessoa pessoa) {
        this.conteudo = conteudo;
        this.nome = nome;
        this.foto = foto;
        this.caminho = caminho;
        this.tipo = tipo;
        this.codigoTeste = codigoTeste;
        this.dataCriacao = dataCriacao;
        this.comentarios = new ArrayList();
        this.grupo = grupo;
        this.pessoa = pessoa;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}