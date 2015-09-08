package com.br.controladores;

import com.br.entidades.*;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
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
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorUsuario")
@SessionScoped
public class ControladorUsuarios implements Serializable {

    @EJB
    Fachada fachada;
    private String usuario;
    private String login;
    private String senha;
    private Professor professor;
    private Aluno aluno;

    HttpSession session;

    public ControladorUsuarios() {
        login = null;
        senha = null;
        usuario = null;
        aluno = new Aluno();
        professor = new Professor();
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String salvarUsuario() throws IOException {
        System.out.println("Usuario: " + usuario);
        String caminho
                = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\imgPadrao\\perfil.png";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        if (usuario.equalsIgnoreCase("Aluno")) {
            if (fachada.buscarProfessor(login) == null && fachada.buscarAluno(login) == null
                    && fachada.buscarAlunoEmail(login) == null && fachada.buscarProfessorEmail(login) == null) {
                Aluno aluno = new Aluno();
                aluno.setLogin(login);
                aluno.setSenha(senha);
                aluno.setFoto(caminho);
                aluno.setDataParticipacao(new Date());
                fachada.salvarAluno(aluno);
                login = null;
                senha = null;
                context.redirect(request.getContextPath());
            }
        } else {
            if (fachada.buscarProfessor(login) == null && fachada.buscarAluno(login) == null
                    && fachada.buscarAlunoEmail(login) == null && fachada.buscarProfessorEmail(login) == null) {

                Professor professor = new Professor();
                professor.setLogin(login);
                professor.setSenha(senha);
                professor.setFoto(caminho);
                professor.setDataParticipacao(new Date());

                fachada.salvarProfessor(professor);
                context.redirect(request.getContextPath());
            }
        }

        return null;
    }

    public void loginUsuario() throws IOException {

        if (usuario.equalsIgnoreCase("Aluno")) {

            Aluno a = fachada.loginAluno(login, senha);

            if (a != null) {
                String loginPage = "/md-aluno/pageInicialAluno.jsf";
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                session = (HttpSession) context.getSession(false);
                context.getSessionMap().put("aluno", a);
                context.redirect(request.getContextPath() + loginPage);
                aluno = PegarUsuarioSessao.pegarAlunoSessao();
            } else {
                /*Java Script*/
            }
        } else {

            Professor p = fachada.loginProfessor(login, senha);

            if (p != null) {
                String loginPage = "/md-professor/page-inicial-professor.jsf";
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                session = (HttpSession) context.getSession(false);
                context.getSessionMap().put("professor", p);
                context.redirect(request.getContextPath() + loginPage);
                professor = PegarUsuarioSessao.pegarProfessorSessao();
            } else {
                /*Java Script*/
            }
        }
    }

    public String logout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        this.session = (HttpSession) context.getSession(false);
        session.invalidate();
        try {
            context.redirect(request.getContextPath());
        } catch (IOException e) {

        }

        return null;
    }

    /*Atualizar Usuarios*/
    public String atualizarProfessor() {
        professor = PegarUsuarioSessao.pegarProfessorSessao();
        fachada.atualizarProfessor(professor);
        return "page-config-professor?faces-redirect=true";
    }
}
