package com.br.controladores;

import com.br.entidades.Arquivo;
import com.br.entidades.Grupo;
import com.br.entidades.Professor;
import com.br.entidades.Topico;
import com.br.fachada.Fachada;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Fatinha
 */
@Named(value = "controladorGrupo")
@SessionScoped
public class GerenciadorGrupo implements Serializable {

    @EJB
    private Fachada fachada;
    private Grupo grupo;
    private HttpSession session;
    private Topico topico;
    private ExternalContext context;
    private UploadedFile fileUpload;
    private StreamedContent content;
    private StreamedContent fileDownload;
    private Arquivo arquivo;

    public GerenciadorGrupo() {
        grupo = new Grupo();
        topico = new Topico();
        arquivo = new Arquivo();
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public String salvarGrupo() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");

        grupo.setDataCriacao(new Date());
        grupo.setProfessorGrupos(professorLogado);
        fachada.salvarGrupo(grupo);
        grupo = new Grupo();
        return null;
    }

    public List<Grupo> gruposCriados() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");

        return fachada.meusGrupos(professorLogado.getLogin());
    }

    public String removerGrupo(Grupo grupo) {
        this.fachada.removerGrupo(grupo);
        return "cadGrupo?faces-redirect=true";
    }

    public String pagInicialGrupo(Grupo grupo) {
        this.grupo = grupo;
        return "pagInicialGrupo?faces-redirect=true";
    }

    /*Topicos*/
    public String salvarTopicoProfessor() {
        if (topico.getConteudo().length() > 0) {
            context = FacesContext.getCurrentInstance().getExternalContext();
            this.session = (HttpSession) context.getSession(false);
            Professor professorLogado = (Professor) session.getAttribute("professor");

            topico.setDataCriacao(new Date());
            topico.setGrupo(grupo);
            topico.setPessoa(professorLogado);

            fachada.salvarTopico(topico);
            topico = new Topico();
        }

        return "pagInicialGrupo?faces-redirect=true";
    }

    public List<Topico> topicos() {
        for(Topico t: fachada.topicosGrupo(grupo.getCodigo())){
            System.out.println("Usuario: "+t.getPessoa().getFoto());
        }
        
        return fachada.topicosGrupo(grupo.getCodigo());
        
        
    }
    
    /*Upload de Arquivos*/
    public void upload() {
        String caminho = "C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Arquivos\\doc\\";

        File dir = new File(caminho);
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (fileUpload != null) {
            try {
                File targetFolder = new File(caminho);
                InputStream inputStream = fileUpload.getInputstream();

                OutputStream out = new FileOutputStream(new File(targetFolder,
                        fileUpload.getFileName()));
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

                String caminhoFoto = "C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\imgPadrao\\doc.png";
                context = FacesContext.getCurrentInstance().getExternalContext();
                session = (HttpSession) context.getSession(false);
                Professor professorLogado = (Professor) session.getAttribute("professor");

                arquivo.setFoto(caminhoFoto);
                arquivo.setCaminho(caminho+fileUpload.getFileName());
                arquivo.setNome(fileUpload.getFileName());
                arquivo.setGrupoArquivo(grupo);
                arquivo.setPessoa(professorLogado);
                fachada.salvarArquivo(arquivo);
                arquivo = new Arquivo();
                
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Faz Download
    public StreamedContent donwload() throws FileNotFoundException {
        InputStream stream = new FileInputStream("C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Arquivos\\doc\\doc.pdf");
        fileDownload = new DefaultStreamedContent(stream, "application/pdf",
                "test.pdf");
        return fileDownload;
    }
}
