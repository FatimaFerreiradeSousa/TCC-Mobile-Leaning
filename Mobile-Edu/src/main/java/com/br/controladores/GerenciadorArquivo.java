package com.br.controladores;

import com.br.entidades.Arquivo;
import com.br.entidades.Grupo;
import com.br.entidades.Professor;
import com.br.fachada.Fachada;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Fatinha
 */
@Named(value = "gerenciadorArquivo")
@SessionScoped
public class GerenciadorArquivo implements Serializable {

    private UploadedFile fileUpload;
    private UploadedFile fileDownload;
    private Arquivo arquivo;
    @EJB
    Fachada fachadaModArquivo;

    public GerenciadorArquivo() {
        arquivo = new Arquivo();
    }

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public UploadedFile getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(UploadedFile fileDownload) {
        this.fileDownload = fileDownload;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
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
                
                arquivo.setFoto(caminhoFoto);
                arquivo.setCaminho(caminho+fileUpload.getFileName());
                arquivo.setNome(fileUpload.getFileName());
                arquivo.setGrupoArquivo(grupo);
                arquivo.setPessoa(professorLogado);
                arquivo.setDescricao("Arquivo de teste");
                fachadaModArquivo.salvarArquivo(arquivo);
                arquivo = new Arquivo();
                
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        return null;
    }
}
