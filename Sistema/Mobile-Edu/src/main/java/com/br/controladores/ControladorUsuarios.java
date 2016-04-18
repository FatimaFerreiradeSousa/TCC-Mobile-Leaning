package com.br.controladores;

import com.br.entidades.*;
import com.br.fachada.Service;
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
    Service fachada;
    private String usuario;
    private Professor professor;
    private Aluno aluno;
    private StreamedContent content;
    private UploadedFile file;
    private String mensagem;
    private Pessoa pessoa;

    HttpSession session;

    public ControladorUsuarios() {
        usuario = null;
        mensagem = null;
        aluno = new Aluno();
        professor = new Professor();
        pessoa = new Pessoa();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
                = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Imagens\\imagens_padrao\\perfil.png";

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        if (usuario.equalsIgnoreCase("Aluno")) {
            if (fachada.buscarProfessor(pessoa.getLogin()) == null && fachada.buscarAluno(pessoa.getLogin()) == null
                    && fachada.buscarAlunoEmail(pessoa.getLogin()) == null && fachada.buscarProfessorEmail(pessoa.getLogin()) == null) {
                Aluno a = new Aluno();
                a.setLogin(pessoa.getLogin());
                a.setSenha(pessoa.getSenha());
                a.setEmail(pessoa.getEmail());
                a.setNome(pessoa.getNome());
                a.setSobrenome(pessoa.getSobrenome());
                a.setInstituicao(pessoa.getInstituicao());
                a.setFoto(caminho);
                a.setDataParticipacao(new Date());
                fachada.salvarAluno(a);
                mensagem = null;
                pessoa = new Pessoa();
                context.redirect(request.getContextPath());
            } else {
                mensagem = "Email/login não disponível!";
            }
        } else if (fachada.buscarProfessor(pessoa.getLogin()) == null && fachada.buscarAluno(pessoa.getLogin()) == null
                && fachada.buscarAlunoEmail(pessoa.getLogin()) == null && fachada.buscarProfessorEmail(pessoa.getLogin()) == null) {

            Professor p = new Professor();
            p.setLogin(pessoa.getLogin());
            p.setSenha(pessoa.getSenha());
            p.setEmail(pessoa.getEmail());
            p.setNome(pessoa.getNome());
            p.setSobrenome(pessoa.getSobrenome());
            p.setFoto(caminho);
            p.setDataParticipacao(new Date());

            fachada.salvarProfessor(p);
            mensagem = null;
            pessoa = new Pessoa();
            context.redirect(request.getContextPath());
        } else {
            mensagem = "Email/Login não disponível!";
        }

        return null;
    }

    public void loginUsuario() throws IOException {

        if (usuario.equalsIgnoreCase("Aluno")) {

            Aluno a = fachada.loginAluno(pessoa.getLogin(), pessoa.getSenha());

            if (a != null) {
                String loginPage = "/md-aluno/page-inicial-aluno.jsf";

                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                session = (HttpSession) context.getSession(false);
                context.getSessionMap().put("aluno", a);
                context.redirect(request.getContextPath() + loginPage);

                aluno = PegarUsuarioSessao.getAluno();
                mensagem = null;

            } else {
                mensagem = "Login/Senha inválidos!";
            }
        } else {

            Professor p = fachada.loginProfessor(pessoa.getLogin(), pessoa.getSenha());

            if (p != null) {
                String loginPage = "/md-professor/page-inicial-professor.jsf";

                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                session = (HttpSession) context.getSession(false);
                context.getSessionMap().put("professor", p);
                context.redirect(request.getContextPath() + loginPage);
                professor = PegarUsuarioSessao.getProfessor();

                mensagem = null;

            } else {
                mensagem = "Login/Senha inválidos!";
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

    /*Professor*/
    public String atualizarProfessor() {
        professor = PegarUsuarioSessao.getProfessor();
        fachada.atualizarProfessor(professor);
        return "page-config-professor?faces-redirect=true";
    }

    public void uploadProfessor() {
        professor = PegarUsuarioSessao.getProfessor();
        String caminho = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Imagens\\"
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
    public String uploadAluno() {
        String caminho = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Imagens\\"
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

        return "page-config-aluno?faces-redirect=true";
    }

    public String atualizarAluno() {
        aluno = PegarUsuarioSessao.getAluno();
        fachada.atualizarAluno(aluno);

        return "page-config-aluno?faces-redirect=true";
    }
}
