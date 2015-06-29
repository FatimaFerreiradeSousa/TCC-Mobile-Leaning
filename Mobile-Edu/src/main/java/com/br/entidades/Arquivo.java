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
    private Professor professor;
    @ManyToOne
    private Grupo grupoArquivo;
    
    public Arquivo(){
    
    }

    public Arquivo(String nome, String descricao, String foto, String caminho, Professor professor, Grupo grupo) {
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.caminho = caminho;
        this.professor = professor;
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
    
    public Professor getProfessor(){
        return professor;
    }
    
    public void setProfessor(Professor professor){
        this.professor = professor;
    }

    public Grupo getGrupoArquivo() {
        return grupoArquivo;
    }

    public void setGrupoArquivo(Grupo grupo) {
        this.grupoArquivo = grupo;
    }
}
