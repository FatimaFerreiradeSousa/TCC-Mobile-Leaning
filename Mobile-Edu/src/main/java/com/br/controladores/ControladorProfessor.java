package com.br.controladores;

import com.br.datas.FormatData;
import com.br.entidades.Professor;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
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
    private HttpSession session;
    private StreamedContent content;

    private UploadedFile file;
    private String mes;
    private String ano;
    private String mensagem;
    private String usuario;

    @EJB
    Fachada fachada;

    public ControladorProfessor() {
        professor = new Professor();
        professorLogado = new Professor();
        usuario = new String();
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
        professorLogado = PegarUsuarioSessao.pegarProfessorSessao();
        this.mes = FormatData.pegarMes(professorLogado.getDataParticipacao());
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        professorLogado = PegarUsuarioSessao.pegarProfessorSessao();
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String atualizarProfessor() {
        professorLogado = PegarUsuarioSessao.pegarProfessorSessao();
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

    public void upload() {
        professorLogado = PegarUsuarioSessao.pegarProfessorSessao();
        String caminho = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\Professor\\"
                + professorLogado.getLogin() + "\\";

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
                professorLogado.setFoto(caminho + professorLogado.getLogin() + tipoArquivo);
                atualizarProfessor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String paginaInicialProfessor() {
        return "indexProfessor?faces-redirect=true";
    }

    public StreamedContent mostrarFoto() {

        String loginProfessor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("loginProfessor");
        System.out.println("Login: " +loginProfessor);
        
        if (loginProfessor != null) {
            Professor p = fachada.buscarProfessor(loginProfessor);

            File fotoUsuario = new File(p.getFoto());

            try {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(fotoUsuario));
                byte[] bytes = new byte[in.available()];
                in.read(bytes);
                in.close();
                
                this.content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/jpeg");
                return content;
            } catch (Exception e) {
                e.printStackTrace();
            };
        }

        return new DefaultStreamedContent();
    }
}
