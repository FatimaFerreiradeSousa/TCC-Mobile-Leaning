package com.br.controladores;

import com.br.entidades.*;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

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
    private String email;
    private Professor professor;
    private Aluno aluno;
    private StreamedContent content;
    private UploadedFile file;
    private String mensagem;

    HttpSession session;

    public ControladorUsuarios() {
        login = null;
        senha = null;
        email = null;
        usuario = null;
        mensagem = null;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String salvarUsuario() throws IOException {
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
                aluno.setEmail(email);
                aluno.setFoto(caminho);
                aluno.setDataParticipacao(new Date());
                fachada.salvarAluno(aluno);
                login = null;
                senha = null;
                mensagem = null;
                context.redirect(request.getContextPath());
            } else {
                mensagem = "Email/login não disponível!";
            }
        } else {
            if (fachada.buscarProfessor(login) == null && fachada.buscarAluno(login) == null
                    && fachada.buscarAlunoEmail(login) == null && fachada.buscarProfessorEmail(login) == null) {

                Professor professor = new Professor();
                professor.setLogin(login);
                professor.setSenha(senha);
                professor.setEmail(email);
                professor.setFoto(caminho);
                professor.setDataParticipacao(new Date());

                fachada.salvarProfessor(professor);
                mensagem = null;
                context.redirect(request.getContextPath());
            }else{
                mensagem = "Email/Login não disponível!";
            }
        }

        return null;
    }

    public void loginUsuario() throws IOException {

        if (usuario.equalsIgnoreCase("Aluno")) {

            Aluno a = fachada.loginAluno(login, senha);

            if (a != null) {
                String loginPage = "/md-aluno/page-inicial-aluno.jsf";
                
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                session = (HttpSession) context.getSession(false);
                context.getSessionMap().put("aluno", a);
                context.redirect(request.getContextPath() + loginPage);
                
                aluno = PegarUsuarioSessao.pegarAlunoSessao();
                mensagem = null;
                
            } else {
                mensagem = "Login/Senha inválidos!";
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
                
                mensagem = null;
                
            } else {
                mensagem = "Login/Senha inválidos!";
            }
        }
    }

    public String sair(){
        System.out.println("Hello");
        return null;
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

    /*Professor*/
    public String atualizarProfessor() {
        professor = PegarUsuarioSessao.pegarProfessorSessao();
        fachada.atualizarProfessor(professor);
        return "page-config-professor?faces-redirect=true";
    }

    public void uploadProfessor() {
        professor = PegarUsuarioSessao.pegarProfessorSessao();
        String caminho = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\Professor\\"
                + professor.getLogin() + "\\";

        File dir = new File(caminho);
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (file != null) {
            try {
                File targetFolder = new File(caminho);
                InputStream inputStream = file.getInputstream();

                String tipoArquivo = file.getFileName();
                OutputStream out = new FileOutputStream(new File(targetFolder,
                        professor.getLogin() + tipoArquivo));
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputStream.close();
                out.flush();
                out.close();
                professor.setFoto(caminho + professor.getLogin() + tipoArquivo);
                atualizarProfessor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*Aluno*/
    public void uploadAluno() {
        String caminho = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\Aluno\\"
                + aluno.getLogin() + "\\";

        File dir = new File(caminho);
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (file != null) {
            try {
                File targetFolder = new File(caminho);
                InputStream inputStream = file.getInputstream();

                String tipoArquivo = file.getFileName();
                OutputStream out = new FileOutputStream(new File(targetFolder,
                        aluno.getLogin() + tipoArquivo));
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputStream.close();
                out.flush();
                out.close();
                aluno.setFoto(caminho + aluno.getLogin() + tipoArquivo);
                fachada.atualizarAluno(aluno);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String atualizarAluno() {
        aluno = PegarUsuarioSessao.pegarAlunoSessao();
        fachada.atualizarAluno(aluno);

        return "page-config-aluno?faces-redirect=true";
    }
}
