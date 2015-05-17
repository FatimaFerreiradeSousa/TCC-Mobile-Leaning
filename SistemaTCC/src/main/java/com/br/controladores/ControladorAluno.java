package com.br.controladores;

import com.br.datas.FormatData;
import com.br.entidades.Aluno;
import com.br.fachada.Fachada;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Fatinha
 */
@ManagedBean(name = "controladorAluno")
@SessionScoped
public class ControladorAluno implements Serializable {

    Aluno aluno;
    Aluno alunoLogado;
    HttpSession session;
    private StreamedContent content;
    private String mes;
    private String ano;
    private String mensagem;

    @EJB
    Fachada fachada;

    public ControladorAluno() {
        aluno = new Aluno();
        alunoLogado = new Aluno();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aluno getAlunoLogado() {
        return alunoLogado;
    }

    public void setAlunoLogado(Aluno alunoLogado) {
        this.aluno = alunoLogado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public StreamedContent getContent() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        alunoLogado = (Aluno) this.session.getAttribute("aluno");
        File foto = new File(alunoLogado.getFoto());

        DefaultStreamedContent content = null;
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

    public String getMes() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        alunoLogado = (Aluno) this.session.getAttribute("aluno");
        this.mes = FormatData.pegarMes(alunoLogado.getDataParticipacao());
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        alunoLogado = (Aluno) this.session.getAttribute("aluno");
        this.ano = FormatData.pegarAno(alunoLogado.getDataParticipacao());
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void salvarAluno() throws IOException {
        String caminho
                = "C:\\Users\\Fatinha\\Documents\\Repositorios\\sistema-tcc\\SistemaTCC\\Imagens\\imgPadrao\\perfil.png";

        if (aluno.getNome().length() > 0 && aluno.getEmail().length() > 0 && aluno.getLogin().length() > 0
                && aluno.getSenha().length() > 0) {
            if (fachada.buscarAluno(aluno) == null) {
                aluno.setFoto(caminho);
                aluno.setDataParticipacao(new Date());
                fachada.salvarAluno(aluno);
                aluno = new Aluno();
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

    public void loginAluno() throws IOException {

        if (aluno.getLogin().length() > 0 && aluno.getSenha().length() > 0) {
            Aluno a = fachada.loginAluno(aluno.getLogin(), aluno.getSenha());

            if (a != null) {
                aluno = a;
                String loginPage = "/faces/moduloAluno/pageInicialAluno.xhtml";
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) context.getRequest();
                this.session = (HttpSession) context.getSession(false);
                context.getSessionMap().put("aluno", aluno);
                alunoLogado = (Aluno) session.getAttribute("aluno");
                context.redirect(request.getContextPath() + loginPage);
            } else {
                mensagem = "Login ou senha inválidos!";
                aluno = new Aluno();
            }
        } else {
            mensagem = "Preencha todos os campos!";
        }
    }

    public String logoutAluno() {
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

    public String atualizarAluno() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        alunoLogado = (Aluno) this.session.getAttribute("aluno");
        fachada.atualizarAluno(aluno);
        return null;
    }
    
    public String paginaInicialAluno(){
        return "indexAluno?faces-redirect=true";
    }
}
