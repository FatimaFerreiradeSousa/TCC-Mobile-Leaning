package com.br.controladores;

import com.br.entidades.*;
import com.br.fachada.Fachada;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorLogin")
@SessionScoped
public class ControladorLogin implements Serializable {

    @EJB
    Fachada fachada;
    private String usuario;
    private String login;
    private String senha;
    
    HttpSession session;

    public ControladorLogin() {
        login = null;
        senha = null;
        usuario = null;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String salvarUsuario() throws IOException {
        System.out.println("Usuario: " + usuario);
        String caminho
                = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\imgPadrao\\perfil.png";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        if (usuario.equalsIgnoreCase("Aluno")) {
            Aluno aluno = new Aluno();
            aluno.setLogin(login);
            aluno.setSenha(senha);
            aluno.setFoto(caminho);
            aluno.setDataParticipacao(new Date());
            fachada.salvarAluno(aluno);
            login = null;
            senha = null;
            context.redirect(request.getContextPath());
        } else {
            Professor professor = new Professor();
            professor.setLogin(login);
            professor.setSenha(senha);
            professor.setFoto(caminho);
            professor.setDataParticipacao(new Date());

            fachada.salvarProfessor(professor);
            context.redirect(request.getContextPath());
        }

        return null;
    }

    public void loginUsuario() throws IOException {
        
        System.out.println("Usuario: " +usuario);
        
        if (usuario.equalsIgnoreCase("Aluno")) {

            Aluno aluno = fachada.loginAluno(login, senha);

            if (aluno != null) {
                String loginPage = "/md-aluno/pageInicialAluno.jsf";
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                session = (HttpSession) context.getSession(false);
                context.getSessionMap().put("aluno", aluno);
                context.redirect(request.getContextPath() + loginPage);
            }
        } else {
            
            Professor professor = fachada.loginProfessor(login, senha);

            if (professor != null) {
                String loginPage = "/md-professor/page-inicial-professor.jsf";
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                session = (HttpSession) context.getSession(false);
                context.getSessionMap().put("professor", professor);
                context.redirect(request.getContextPath() + loginPage);
            }
        }
    }
}
