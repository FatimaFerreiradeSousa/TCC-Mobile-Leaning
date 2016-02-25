package com.br.controladores;

import com.br.entidades.Aluno;
import com.br.entidades.Comentario;
import com.br.entidades.Grupo;
import com.br.entidades.Notificacao;
import com.br.entidades.ParticipaGrupo;
import com.br.entidades.RespondeExercicio;
import com.br.entidades.Topico;
import com.br.fachada.Service;
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
    private Service fachada;
    private Grupo grupo;
    private Topico topico;
    private Topico topicoTemp;
    private Topico topicoTeste;
    private UploadedFile fileUpload;
    private StreamedContent fileDownload;
    private Comentario comentarioTopico;
    private Comentario comentarioAlterar;
    private String mensagem;
    private Aluno aluno;
    private ParticipaGrupo participaGrupo;
    private String nomeGrupo;
    private boolean aceito;

    public GerenciadorGrupo() {
        grupo = new Grupo();
        topico = new Topico();
        topicoTemp = new Topico();
        topicoTeste = new Topico();
        comentarioTopico = new Comentario();
        comentarioAlterar = new Comentario();
        aluno = new Aluno();
        participaGrupo = new ParticipaGrupo();
        nomeGrupo = null;
        aceito = false;
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

    public Topico getTopicoTemp() {
        return topicoTemp;
    }

    public void setTopicoTemp(Topico topicoTemp) {
        this.topicoTemp = topicoTemp;
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

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public Topico getTopicoTeste() {
        return topicoTeste;
    }

    public void setTopicoTeste(Topico topicoTeste) {
        this.topicoTeste = topicoTeste;
    }

    public Comentario getComentarioAlterar() {
        return comentarioAlterar;
    }

    public void setComentarioAlterar(Comentario comentarioAlterar) {
        this.comentarioAlterar = comentarioAlterar;
    }

    /*operações da entidade grupo*/
    public String paginaAddGrupo() {
        grupo = new Grupo();

        return "page-cad-grupo?faces-redirect=true";
    }

    public String salvarGrupo() {
        grupo.setDataCriacao(new Date());
        grupo.setProfessorGrupos(PegarUsuarioSessao.pegarProfessorSessao());

        if (fachada.salvarGrupo(grupo)) {
            grupo = new Grupo();
            return "page-grupo?faces-redirect=true";
        } else {
            return "page-cad-grupo?faces-redirect=true";
        }
    }

    public List<Grupo> gruposCriados() {
        return fachada.meusGrupos(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }

    public String removerGrupo() {
        fachada.removerGrupo(grupo);

        return "page-grupo?faces-redirect=true";
    }

    public String pagInicialGrupo(Grupo grupo) {
        this.grupo = grupo;
        return "page-inicial-grupo?faces-redirect=true";
    }

    public String atualizarGrupo() {
        fachada.atualizarGrupo(grupo);

        return "page-alterar-grupo?faces-redirect=true";
    }

    public String pageCriarTopico() {
        return "page-publicar?faces-redirect=true";
    }

    public String paginaEnviarArquivo() {
        return "page-arquivo?faces-redirect=true";
    }

    /*Topicos Professor*/
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
    
    public String removerArquivo(Topico topico) {

        fachada.removerTopico(topico);
        return "page-arquivo?faces-redirect=true";
    }

    public String pageAlterarTopico(Topico topico) {
        topicoTemp = topico;
        return "page-alterar-topico?faces-redirect=true";
    }

    public String alterarTopico() {
        fachada.atualizarTopico(topicoTemp);
        return "page-inicial-grupo?faces-redirect=true";
    }

    public String cancelarAtualizacaoTopico() {
        topicoTemp = new Topico();
        return "page-inicial-grupo?faces-redirect=true";
    }

    /*COMENTARIOS TOPICO PROFESSOR*/
    public Comentario getComentarioTopico() {
        return comentarioTopico;
    }

    public void setComentarioTopico(Comentario comentarioTopico) {
        this.comentarioTopico = comentarioTopico;
    }

    public String pageComentarioTopico(Topico topico) {
        this.topicoTemp = topico;
        return "page-comentario-topico?faces-redirect=true";
    }

    public List<Comentario> comentariosTopico() {
        return fachada.listarComentariosTopico(topicoTemp.getCodigo());
    }

    public String comentarTopico() {
        return "page-comentario?faces-redirect=true";
    }

    public String salvarComentarioProfessor() {
        comentarioTopico.setDataComentario(new Date());
        comentarioTopico.setLoginUsuario(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
        comentarioTopico.setTopico(topicoTemp);

        if (fachada.salvarComentario(comentarioTopico) == true) {

            comentarioTopico = new Comentario();
        }

        return "page-comentario-topico?faces-redirect=true";
    }

    public String pageAlterarComentario(Comentario comentario) {
        comentarioAlterar = comentario;
        return "page-alterar-comentario?faces-redirect=true";
    }

    public String alterarComentario() {
        fachada.alterarComentario(comentarioAlterar);
        return "page-comentario-topico?faces-redirect=true";
    }

    public String removerComentario(Comentario comentario) {
        fachada.removerComentario(comentario);

        return "page-comentario-topico?faces-redirect=true";
    }

    /*UPLOAD ARQUIVOS PROFESSOR*/
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

    public void uploadProfessor() {
        String caminho = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Arquivos\\"
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
        File file = new File(caminho);
        InputStream stream = new FileInputStream(file);
        fileDownload = new DefaultStreamedContent(stream, "application/pdf",
                nome);

        return fileDownload;
    }

    public String voltarPaginaGrupo() {
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

    public List<ParticipaGrupo> solicitacoesGrupoProfessor() {
        return fachada.listarSolicitacoesRecebidas(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }

    public String rejeitarSolicitacao(ParticipaGrupo participaGrupo) {
        fachada.removerMembro(participaGrupo.getAluno().getLogin(), participaGrupo.getGrupo().getCodigo());
        return "page-inicial-professor?faces-redirect=true";
    }

    public String aceitarSolicitacao(ParticipaGrupo participaGrupo) {
        participaGrupo.setAceito(true);

        if (fachada.atualizarSolicitacao(participaGrupo) == true) {

        }

        return "page-solicitacoes-grupos?faces-redirect=true";
    }

    public ParticipaGrupo buscarParticipaGrupo() {
        return fachada.buscarParticipaGrupo(aluno.getLogin(), grupo.getCodigo());
    }

    public List<RespondeExercicio> listarRespondeExercicios() {
        return fachada.listarRespondeExercicio(aluno.getLogin());
    }

    /*Membros Grupo*/
    public List<Aluno> listarMembrosGrupo() {
        List<Aluno> alunos = fachada.listarMembrosGrupo(this.grupo.getCodigo());
        return alunos;
    }
    
    public List<Aluno> listarAlunosGrupo(Grupo g) {
        List<Aluno> alunos = fachada.listarMembrosGrupo(g.getCodigo());
        return alunos;
    }
    
    public int qtdMembrosGrupo(Grupo g) {
        List<Aluno> alunos = fachada.listarMembrosGrupo(g.getCodigo());
        return alunos.size();
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
    public List<Topico> listarArquivos() {
        return fachada.arquivosGrupo(grupo.getCodigo());
    }

    public List<Topico> listarPublicacoes() {
        return fachada.postagensGrupo(grupo.getCodigo());
    }

    public List<ParticipaGrupo> listaRanckingGrupo() {
        return fachada.buscarMembros(grupo.getCodigo());
    }

    public String pageInfoAluno(Aluno aluno) {
        this.aluno = aluno;
        return "page-info-aluno?faces-redirect=true";
    }

    /*Operações realizadas pelo aluno*/
    public List<Grupo> listarGruposPorNome() {
        return fachada.buscarGruposPorNome(nomeGrupo);
    }

    public String paginaSolicitacaoGrupo(Grupo grupo) {
        this.grupo = grupo;

        if (fachada.verificaMembro(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), grupo.getCodigo()) == false) {
            return "page-inicial-grupo?faces-redirect=true";
        } else {
            this.aceito = fachada.verificaSolicitacaoPendente(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), this.grupo.getCodigo());
            return "page-solicitacao-grupo?faces-redirect=true";
        }
    }

    public String participarGrupo() {

        participaGrupo = new ParticipaGrupo();
        participaGrupo.setAceito(false);
        participaGrupo.setAluno(PegarUsuarioSessao.pegarAlunoSessao());
        participaGrupo.setDataParticipacao(new Date());
        participaGrupo.setGrupo(grupo);

        fachada.adicionarMembro(participaGrupo);
        participaGrupo = new ParticipaGrupo();

        aceito = fachada.verificaSolicitacaoPendente(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), this.grupo.getCodigo());
        return "page-solicitacao-grupo-solicitacao?faces-redirect=true";
    }

    public String cancelarSolicitacao() {
        fachada.removerMembro(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), grupo.getCodigo());
        aceito = fachada.verificaSolicitacaoPendente(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), this.grupo.getCodigo());
        return "page-solicitacao-grupo?faces-redirect=true";
    }

    public List<ParticipaGrupo> solicitacoesPendentes() {
        return fachada.listarGruposPendentes(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
    }

    /*Topicos do grupo - aluno*/
    public String paginaTopicoAluno() {
        topico = new Topico();
        return "page-publicar-topico?faces-redirect=true";
    }

    public String paginaArquivoAluno() {
        topico = new Topico();
        return "page-publicar-arquivo?faces-redirect=true";
    }

    public String salvarTopicoAluno() {

        topico.setDataCriacao(new Date());
        topico.setGrupo(grupo);
        topico.setLoginUsuario(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
        topico.setTipo("Publicacao");

        fachada.salvarTopico(topico);

        topico = new Topico();

        return "page-inicial-grupo?faces-redirect=true";
    }

    public String uploadAluno() {
        String caminho = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Arquivos\\"
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

                topico.setCaminho(caminho + fileUpload.getFileName());
                topico.setNome(fileUpload.getFileName());
                topico.setGrupo(grupo);
                topico.setLoginUsuario(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
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

        return "page-inicial-grupo?faces-redirect=true";
    }

    public List<ParticipaGrupo> gruposAluno() {
        return fachada.listarGruposAlunos(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
    }

    public String paginaGrupoAluno(Grupo grupo) {
        this.grupo = grupo;
        return "page-inicial-grupo?faces-redirect=true";
    }

    /*Comentarios topico aluno*/
    public String paginaComentarioAluno() {

        return "page-criar-comentario?faces-redirect=true";
    }

    public String salvarComentarioAluno() {
        comentarioTopico.setDataComentario(new Date());
        comentarioTopico.setLoginUsuario(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
        comentarioTopico.setTopico(topicoTemp);

        if (fachada.salvarComentario(comentarioTopico) == true) {
            comentarioTopico = new Comentario();
        }

        return "page-comentario-topico?faces-redirect=true";
    }

    public String sairGrupo() {
        fachada.removerMembro(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), grupo.getCodigo());
        aceito = fachada.verificaSolicitacaoPendente(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), this.grupo.getCodigo());
        return "page-solicitacao-grupo?faces-redirect=true";
    }

    public int topicosPublicados() {
        return fachada.listarTopicosUsuario(aluno.getLogin(), grupo.getCodigo()).size();
    }
}
