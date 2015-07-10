package com.br.controladores;

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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Fatinha
 */
@Named(value = "gerenciadorArquivo")
@SessionScoped
public class GerenciadorArquivo implements Serializable {

    private UploadedFile fileUpload;
    private StreamedContent fileDownload;
    private Topico topico;
            
    @EJB
    Fachada fachadaModArquivo;

    public GerenciadorArquivo() {
        topico = new Topico();
    }

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    
    public String uploadArquivo(){
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
                
                Grupo grupo = new Grupo();
                grupo.setCodigo(1);
                
                Professor professorLogado = new Professor();
                professorLogado.setLogin("Fatinha");
                
                topico.setFoto(caminhoFoto);
                topico.setCaminho(caminho+fileUpload.getFileName());
                topico.setNome(fileUpload.getFileName());
                topico.setGrupo(grupo);
                topico.setPessoa(professorLogado);
                topico.setConteudo("Arquivo de teste");
                topico.setDataCriacao(new Date());
                
                fachadaModArquivo.salvarTopico(topico);
                topico = new Topico();
                
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        return null;
    }
    
     public StreamedContent donwload(String caminho) throws FileNotFoundException {
        InputStream stream = new FileInputStream("C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Arquivos\\doc\\doc.pdf");
        fileDownload = new DefaultStreamedContent(stream, "application/pdf",
                "test.pdf");
        return fileDownload;
    }
}
