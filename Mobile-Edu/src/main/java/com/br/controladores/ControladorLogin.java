package com.br.controladores;

import com.br.entidades.*;
import com.br.fachada.Fachada;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Fatinha de Sousa
 */

@Named(value = "controladorLogin")
@SessionScoped
public class ControladorLogin implements Serializable{

    @EJB
    Fachada fachada;
    private Pessoa pessoa;
    private Aluno aluno;
    private Professor professor;
    private String usuario;
    
    public ControladorLogin() {
        pessoa = new Pessoa();
        aluno = new Aluno();
        professor = new Professor();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String salvarUsuario(){
        System.out.println("Usuario: " +usuario);
        
        return null;
    }
}
