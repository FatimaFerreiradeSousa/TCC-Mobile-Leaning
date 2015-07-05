package com.br.controladores;

import com.br.datas.FormatData;
import com.br.entidades.Professor;
import com.br.fachada.Fachada;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorProfessor")
@SessionScoped
public class ControladorProfessor implements Serializable {

    Professor professor;
    Professor professorLogado;
    HttpSession session;

    private UploadedFile file;
    private StreamedContent content;
    private String mes;
    private String ano;
    private String mensagem;

    @EJB
    Fachada fachada;

    public ControladorProfessor() {
        professor = new Professor();
        professorLogado = new Professor();
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessorLogado() {
        return professorLogado;
    }

    public void setProfessorLogado(Professor professorLogado) {
        this.professorLogado = professorLogado;
    }

    public String getMes() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        professorLogado = (Professor) this.session.getAttribute("professor");
        this.mes = FormatData.pegarMes(professorLogado.getDataParticipacao());
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        professorLogado = (Professor) this.session.getAttribute("professor");
        this.ano = FormatData.pegarAno(professorLogado.getDataParticipacao());
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void salvarProfessor() throws IOException {
        String caminho
                = "C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\imgPadrao\\perfil.png";

        if (professor.getLogin().length() > 0 && professor.getNome().length() > 0 && professor.getEmail().length() > 0
                && professor.getSenha().length() > 0) {
            if (fachada.buscarProfessor(professor.getLogin()) == null && fachada.buscarAluno(professor.getLogin()) == null) {
                professor.setFoto(caminho);
                professor.setDataParticipacao(new Date());
                fachada.salvarProfessor(professor);
                professor = new Professor();
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                context.redirect(request.getContextPath());
            } else {
                mensagem = "Login Inválido! Por Favor Tente Outro.";
            }
        } else {
            mensagem = "Preencha todos os campos corretamente!";
        }
    }

    public void loginProfessor() throws IOException {

        if (professor.getLogin().length() > 0 && professor.getSenha().length() > 0) {

            Professor p = fachada.loginProfessor(professor.getLogin(), professor.getSenha());

            if (p != null) {
                professor = p;
                String loginPage = "/md-professor/paginaInicialProfessor.jsf";
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                this.session = (HttpSession) context.getSession(false);
                context.getSessionMap().put("professor", professor);
                professorLogado = (Professor) session.getAttribute("professor");
                context.redirect(request.getContextPath() + loginPage);
            } else {
                mensagem = "Login ou senha inválidos!";
                professor = new Professor();
            }
        } else {
            mensagem = "Preencha todos os campos";
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

    public String atualizarProfessor() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        professorLogado = (Professor) this.session.getAttribute("professor");
        fachada.atualizarProfessor(professorLogado);
        return "paginaConfig?faces-redirect=true";
    }

    public String urlCadProfessor() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        context.redirect(request.getContextPath());
        return null;
    }

    /*Manipulação de foto*/
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getContent() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        professorLogado = (Professor) this.session.getAttribute("professor");
        File foto = new File(professorLogado.getFoto());

        //DefaultStreamedContent content = null;
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(foto));
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/jpeg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public void upload() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        professorLogado = (Professor) this.session.getAttribute("professor");
        String caminho = "C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\Professor\\"
                +professorLogado.getLogin()+"\\";
        
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
                        professorLogado.getLogin() + tipoArquivo));
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputStream.close();
                out.flush();
                out.close();
                professorLogado.setFoto(caminho+professorLogado.getLogin()+tipoArquivo);
                atualizarProfessor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String paginaInicialProfessor() {
        return "indexProfessor?faces-redirect=true";
    }
}
