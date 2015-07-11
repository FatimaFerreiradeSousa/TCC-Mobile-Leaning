package com.br.controladores;

import com.br.datas.FormatData;
import com.br.entidades.Aluno;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
import java.io.File;
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
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Fatinha
 */
@Named(value = "controladorAluno")
@SessionScoped
public class ControladorAluno implements Serializable {

    Aluno aluno;
    Aluno alunoLogado;
    HttpSession session;
    private UploadedFile file;
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getMes() {
        alunoLogado = PegarUsuarioSessao.pegarAlunoSessao();
        this.mes = FormatData.pegarMes(alunoLogado.getDataParticipacao());
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        alunoLogado = PegarUsuarioSessao.pegarAlunoSessao();
        this.ano = FormatData.pegarAno(alunoLogado.getDataParticipacao());
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void salvarAluno() throws IOException {
        String caminho
                = "C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\imgPadrao\\perfil.png";

        if (aluno.getNome().length() > 0 && aluno.getEmail().length() > 0 && aluno.getLogin().length() > 0
                && aluno.getSenha().length() > 0) {
            if (fachada.buscarAluno(aluno.getLogin()) == null && fachada.buscarProfessor(aluno.getLogin()) == null
                    && fachada.buscarAlunoEmail(aluno.getEmail()) == null && fachada.buscarProfessorEmail(aluno.getEmail()) == null) {
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
                String loginPage = "/md-aluno/pageInicialAluno.jsf";
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
        alunoLogado = PegarUsuarioSessao.pegarAlunoSessao();
        fachada.atualizarAluno(alunoLogado);
        return null;
    }
    
    public String paginaInicialAluno(){
        return "indexAluno?faces-redirect=true";
    }
    
    public void upload() {
        alunoLogado = PegarUsuarioSessao.pegarAlunoSessao();
        String caminho = "C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\Aluno\\"
                +alunoLogado.getLogin()+"\\";
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
                        alunoLogado.getLogin() + tipoArquivo));
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputStream.close();
                out.flush();
                out.close();
                alunoLogado.setFoto(caminho + alunoLogado.getLogin() + tipoArquivo);
                atualizarAluno();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
