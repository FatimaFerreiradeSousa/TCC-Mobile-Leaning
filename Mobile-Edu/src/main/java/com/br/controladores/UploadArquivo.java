package com.br.controladores;

import com.br.entidades.Arquivo;
import com.br.entidades.Grupo;
import com.br.entidades.Professor;
import com.br.fachada.Fachada;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
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
@Named(value = "gerenciadorUpload")
@SessionScoped
public class UploadArquivo implements Serializable {

    private UploadedFile fileUpload;
    private StreamedContent content;
    private StreamedContent fileDownload;
    private Arquivo arquivo;

    @EJB
    Fachada fachadaModArquivo;

    public UploadArquivo() {
        arquivo = new Arquivo();
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

    public StreamedContent getContent() {
        File foto = new File("C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Leaning\\Mobile-Edu\\Imagens\\imgPadrao\\perfil.png");

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

    //Faz Upload
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

                //String tipoArquivo = fileUpload.getFileName();
                //tipoArquivo = tipoArquivo.substring(tipoArquivo.lastIndexOf("."), tipoArquivo.length());
               
                OutputStream out = new FileOutputStream(new File(targetFolder,
                        fileUpload.getFileName()));
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

                String caminhoFoto = "C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\imgPadrao\\doc.png";
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                HttpSession session = (HttpSession) context.getSession(false);
                Grupo grupo = (Grupo) session.getAttribute("grupo");
                Professor professorLogado = (Professor) session.getAttribute("professor");

                arquivo.setFoto(caminhoFoto);
                arquivo.setCaminho(caminho+fileUpload.getFileName());
                arquivo.setNome(fileUpload.getFileName());
                arquivo.setGrupoArquivo(grupo);
                arquivo.setPessoa(professorLogado);
                fachadaModArquivo.salvarArquivo(arquivo);
                arquivo = new Arquivo();
                
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    //Faz Download
    public StreamedContent donwload() throws FileNotFoundException {
        InputStream stream = new FileInputStream("C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Arquivos\\doc\\doc.pdf");
        fileDownload = new DefaultStreamedContent(stream, "application/pdf",
                "test.pdf");
        return fileDownload;
    }

    /*Salvar Arquivo*/
}
