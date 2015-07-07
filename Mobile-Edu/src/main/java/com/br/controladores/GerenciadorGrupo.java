package com.br.controladores;

import com.br.entidades.Aluno;
import com.br.entidades.Arquivo;
import com.br.entidades.Comentario;
import com.br.entidades.Grupo;
import com.br.entidades.ParticipaGrupo;
import com.br.entidades.Pessoa;
import com.br.entidades.Professor;
import com.br.entidades.Topico;
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
    private StreamedContent fileDownload;
    private Arquivo arquivo;
    private Comentario comentarioTopico;
    private String mensagem;
    private Aluno aluno;
    private ParticipaGrupo participaGrupo;

    public GerenciadorGrupo() {
        grupo = new Grupo();
        topico = new Topico();
        arquivo = new Arquivo();
        comentarioTopico = new Comentario();
        aluno = new Aluno();
        participaGrupo = new ParticipaGrupo();
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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ParticipaGrupo getParticipaGrupo() {
        return participaGrupo;
    }

    public void setParticipaGrupo(ParticipaGrupo participaGrupo) {
        this.participaGrupo = participaGrupo;
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
        return fachada.topicosGrupo(grupo.getCodigo());
    }

    public Topico buscarTopico() {
        return fachada.topicosGrupo(grupo.getCodigo()).get(0);
    }

    public String removerTopico(Topico topico) {

        fachada.removerTopico(topico);
        return "pagInicialGrupo?faces-redirect=true";
    }

    /*Upload e download de Arquivos*/
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
                arquivo.setCaminho(caminho + fileUpload.getFileName());
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

    /*Comentario*/
    public Comentario getComentarioTopico() {
        return comentarioTopico;
    }

    public void setComentarioTopico(Comentario comentarioTopico) {
        this.comentarioTopico = comentarioTopico;
    }

    public String salvarComentarioProfessor(Topico topico) {
        context = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) context.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");

        comentarioTopico.setDataComentario(new Date());
        comentarioTopico.setPessoa(professorLogado);
        comentarioTopico.setTopico(topico);

        if (fachada.salvarComentario(comentarioTopico) == true) {
            mensagem = "Salvo com sucesso!";
            comentarioTopico = new Comentario();
        }

        return "pagInicialGrupo?faces-redirect=true";
    }

    public String alterarComentario(Comentario comentario) {
        fachada.alterarComentario(comentario);

        return "pagInicialGrupo?faces-redirect=true";
    }

    public String removerComentario(Comentario comentario) {
        fachada.removerComentario(comentario);

        return "pagInicialGrupo?faces-redirect=true";
    }

    public List<Comentario> comentariosTopico(Topico topico) {
        return fachada.listarComentariosTopico(topico.getCodigo());
    }

    /*Membros do grupo*/
    public String buscarAluno() {
        this.aluno = fachada.buscarAluno(aluno.getLogin());

        if (this.aluno != null) {
            return "pag-buscar-usuario?faces-redirect=true";
        } else {
            mensagem = "Nenhum Usuario Encontrado";
            aluno = new Aluno();
            return "pag-buscar-usuario?faces-redirect=true";
        }
    }

    public String adicionarMembro() {

        if (fachada.verificaMembro(aluno.getLogin(), grupo.getCodigo()) == false) {

            this.participaGrupo.setAceito(true);
            this.participaGrupo.setAluno(aluno);
            this.participaGrupo.setGrupo(grupo);
            this.participaGrupo.setDataParticipacao(new Date());

            fachada.adicionarMembro(participaGrupo);
            this.participaGrupo = new ParticipaGrupo();
            this.aluno = new Aluno();
        }
        
        return "pag-listar-membros?faces-redirect=true";
    }

    public List<Aluno> listarMembros() {
        List<Aluno> alunos = fachada.listarMembrosGrupo(this.grupo.getCodigo());
        return alunos;
    }
    
    public boolean verificarMembro(){
        return fachada.verificaMembro(aluno.getLogin(), grupo.getCodigo());
    }
    
    public String paginaRemover(Aluno aluno){
        this.aluno = aluno;
        
        return "remover-membro-grupo?faces-redirect=true";
    }
    
    public String removerMembro(){
        fachada.removerMembro(aluno.getLogin(), grupo.getCodigo());
        
        return "pag-listar-membros?faces-redirect=true";
    }
}
