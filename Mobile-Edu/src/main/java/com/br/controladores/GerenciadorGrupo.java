package com.br.controladores;

import com.br.entidades.Aluno;
import com.br.entidades.Comentario;
import com.br.entidades.Grupo;
import com.br.entidades.ParticipaGrupo;
import com.br.entidades.Topico;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
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
import javax.inject.Named;
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
    private Topico topico;
    private UploadedFile fileUpload;
    private StreamedContent fileDownload;
    private Comentario comentarioTopico;
    private String mensagem;
    private Aluno aluno;
    private ParticipaGrupo participaGrupo;
    private StreamedContent content;

    public GerenciadorGrupo() {
        grupo = new Grupo();
        topico = new Topico();
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

    /*operações da entidade grupo*/
    public String salvarGrupo() {
        grupo.setDataCriacao(new Date());
        grupo.setProfessorGrupos(PegarUsuarioSessao.pegarProfessorSessao());
        fachada.salvarGrupo(grupo);
        grupo = new Grupo();
        return null;
    }

    public List<Grupo> gruposCriados() {
        return fachada.meusGrupos(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }

    public String removerGrupo() {
        fachada.removerGrupo(grupo);

        return "page-listar-grupos?faces-redirect=true";
    }

    public String pagInicialGrupo(Grupo grupo) {
        this.grupo = grupo;
        return "page-inicial-grupo?faces-redirect=true";
    }

    public String atualizarGrupo() {
        fachada.atualizarGrupo(grupo);

        return "page-alterar-grupo?faces-redirect=true";
    }

    /*Topicos*/
    public String salvarTopicoProfessor() {

        topico.setDataCriacao(new Date());
        topico.setGrupo(grupo);
        topico.setLoginUsuario(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
        topico.setTipo("Publicacao");

        fachada.salvarTopico(topico);
        topico = new Topico();

        return "page-inicial-grupo?faces-redirect=true";
    }

    public List<Topico> topicos() {
        return fachada.topicosGrupo(grupo.getCodigo());
    }

    public String removerTopico(Topico topico) {

        fachada.removerTopico(topico);
        return "page-inicial-grupo?faces-redirect=true";
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

    public void upload() {
        String caminho = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\Arquivos\\"
                + grupo.getCodigo() + " - " + grupo.getNome() + "\\";

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

                topico.setQtdDownloads(0);
                topico.setCaminho(caminho + fileUpload.getFileName());
                topico.setNome(fileUpload.getFileName());
                topico.setGrupo(grupo);
                topico.setLoginUsuario(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
                topico.setDataCriacao(new Date());
                topico.setTipo("Arquivo");

                fachada.salvarTopico(topico);
                topico = new Topico();
                inputStream.close();
                out.flush();
                out.close();

                topico = new Topico();
                fileUpload = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Faz Download
    public StreamedContent donwload(String caminho, String nome, int codigo) throws FileNotFoundException {
        InputStream stream = new FileInputStream("C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\Arquivos\\1 - Historia\\NIS.txt");
        fileDownload = new DefaultStreamedContent(stream, "application/pdf",
                nome);

        topico = fachada.buscarTopico(codigo);
        topico.setQtdDownloads(topico.getQtdDownloads() + 1);

        fachada.atualizarTopico(topico);

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
        comentarioTopico.setDataComentario(new Date());
        comentarioTopico.setLoginUsuario(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
        comentarioTopico.setTopico(topico);

        if (fachada.salvarComentario(comentarioTopico) == true) {
            comentarioTopico = new Comentario();
        }

        return "page-inicial-grupo?faces-redirect=true";
    }

    public String alterarComentario(Comentario comentario) {
        fachada.alterarComentario(comentario);

        return "pagInicialGrupo?faces-redirect=true";
    }

    public String removerComentario(Comentario comentario) {
        System.out.println("Comentario: " + comentario.getConteudo());
        fachada.removerComentario(comentario);

        return "page-inicial-grupo?faces-redirect=true";
    }

    public List<Comentario> comentariosTopico(Topico topico) {
        return fachada.listarComentariosTopico(topico.getCodigo());
    }

    public String atualizarMaisUm(Topico topico) {
        topico.setMaisUm(topico.getMaisUm() + 1);
        fachada.atualizarTopico(topico);
        return "page-inicial-grupo?faces-redirect=true";
    }

    /*Membros do grupo*/
    public String buscarAluno() {
        this.aluno = fachada.buscarAluno(aluno.getLogin());

        if (this.aluno != null) {
            return "page-buscar-usuario?faces-redirect=true";
        } else {
            aluno = new Aluno();
            mensagem = "Nenhum usuário encontrado";
            return "page-buscar-usuario?faces-redirect=true";
        }
    }

    public String adicionarMembro() {

        if (fachada.verificaMembro(aluno.getLogin(), grupo.getCodigo()) == true) {

            this.participaGrupo.setAceito(true);
            this.participaGrupo.setAluno(aluno);
            this.participaGrupo.setGrupo(grupo);
            this.participaGrupo.setDataParticipacao(new Date());

            fachada.adicionarMembro(participaGrupo);
            this.participaGrupo = new ParticipaGrupo();
            this.aluno = new Aluno();
        }

        return "page-listar-membros?faces-redirect=true";
    }

    public List<Aluno> listarMembros() {
        List<Aluno> alunos = fachada.listarMembrosGrupo(this.grupo.getCodigo());
        return alunos;
    }

    public boolean verificarMembro() {
        return fachada.verificaMembro(aluno.getLogin(), grupo.getCodigo());
    }

    public String paginaRemover(Aluno aluno) {
        this.aluno = aluno;

        return "remover-membro-grupo?faces-redirect=true";
    }

    public String removerMembro() {
        fachada.removerMembro(aluno.getLogin(), grupo.getCodigo());

        return "page-buscar-usuario?faces-redirect=true";
    }

    public List<ParticipaGrupo> notificacoes() {

        return fachada.listarNotificacoesProfessor(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }

    public String rejeitarSolicitacao(ParticipaGrupo participaGrupo) {
        fachada.removerMembro(participaGrupo.getAluno().getLogin(), participaGrupo.getGrupo().getCodigo());

        return "pagina-notificacoes?faces-redirect=true";
    }

    public String aceitarSolicitacao(ParticipaGrupo participaGrupo) {
        participaGrupo.setAceito(true);

        if (fachada.atualizarSolicitacao(participaGrupo) == true) {
            System.out.println("Okay");
        } else {
            System.out.println("Erro!");
        }

        return "pagina-notificacoes?faces-redirect=true";
    }

    /*Membros Grupo*/
    public List<Aluno> listarAlunosGrupo() {
        return fachada.listarMembrosGrupo(grupo.getCodigo());
    }

    public Aluno membrosGrupo(int posicao) {
        List<Aluno> alunos = fachada.listarMembrosGrupo(grupo.getCodigo());
        Aluno aluno = null;

        if (alunos.isEmpty()) {
            if (alunos.size() == 1) {
                aluno = alunos.get(0);
            }
        }

        return aluno;
    }

    public String paginaInicialGrupo() {
        return "page-inicial-grupo?faces-redirect=true";
    }

    /*Exercicios*/
    public List<Topico> listarTestesGrupo() {
        return fachada.listarTestesGrupo(grupo.getCodigo());
    }

    public List<Topico> listarArquivos() {
        return fachada.arquivosGrupo(grupo.getCodigo());
    }
    
    public List<Topico> listarPublicacoes() {
        return fachada.postagensGrupo(grupo.getCodigo());
    }

    public List<ParticipaGrupo> listaRanckingGrupo() {
        return fachada.buscarMembros(grupo.getCodigo());
    }

    public String paginaResultadoTestes(Grupo grupo) {
        this.grupo = grupo;

        return "md-listar-resultados?faces-redirect=true";
    }

    public ParticipaGrupo buscarPrimeiroLugar() {
        List<ParticipaGrupo> list = fachada.buscarMembros(grupo.getCodigo());

        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }

    }
}
