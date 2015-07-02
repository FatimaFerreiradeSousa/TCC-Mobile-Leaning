package com.br.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Arquivo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nome;
    private String descricao;
    private String foto;
    private String caminho;
    
    @ManyToOne
    private Grupo grupoArquivo;
    @ManyToOne
    private Pessoa pessoa;
    
    public Arquivo(){
    
    }

    public Arquivo(String nome, String descricao, String foto, String caminho, Pessoa pessoa, Grupo grupo) {
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.caminho = caminho;
        this.pessoa = pessoa;
        this.grupoArquivo = new Grupo();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
    public Pessoa getPessoa(){
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }

    public Grupo getGrupoArquivo() {
        return grupoArquivo;
    }

    public void setGrupoArquivo(Grupo grupo) {
        this.grupoArquivo = grupo;
    }
}
