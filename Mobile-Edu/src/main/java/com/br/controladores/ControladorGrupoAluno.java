package com.br.controladores;

import com.br.entidades.*;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Fatinha
 */
@Named(value = "controladorGrupoAluno")
@SessionScoped
public class ControladorGrupoAluno implements Serializable {

    @EJB
    Fachada fachada;
    private String nomeGrupo;
    private Grupo grupo;
    private ParticipaGrupo participaGrupo;
    private boolean aceito;
    private Topico topico;
    private Comentario comentario;
    private UploadedFile fileUpload;

    public ControladorGrupoAluno() {
        nomeGrupo = new String();
        grupo = new Grupo();
        participaGrupo = new ParticipaGrupo();
        aceito = false;
        topico = new Topico();
        comentario = new Comentario();
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public ParticipaGrupo getParticipaGrupo() {
        return participaGrupo;
    }

    public void setParticipaGrupo(ParticipaGrupo participaGrupo) {
        this.participaGrupo = participaGrupo;
    }

    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public List<Grupo> listarGruposPorNome() {
        return fachada.buscarGruposPorNome(nomeGrupo);
    }

    public List<ParticipaGrupo> meusGrupos() {
        return fachada.listarGruposAlunos(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
    }

    public List<ParticipaGrupo> solicitacoesPendentes() {
        return fachada.listarGruposPendentes(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
    }

    public String paginaSolicitacaoGrupo(Grupo grupo) {
        this.grupo = grupo;

        if (fachada.verificaMembro(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), grupo.getCodigo()) == false) {
            return "pagina-inicial-grupo?faces-redirect=true";
        } else {
            this.aceito = fachada.verificaSolicitacaoPendente(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), this.grupo.getCodigo());
            return "pagina-grupo-solicitacao?faces-redirect=true";
        }
    }

    public String paginaInicialGrupo(Grupo grupo) {
        this.grupo = grupo;
        return "pagina-inicial-grupo?faces-redirect=true";
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
        return "pagina-grupo-solicitacao?faces-redirect=true";
    }

    public String cancelarSolicitacao() {
        fachada.removerMembro(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), grupo.getCodigo());
        aceito = fachada.verificaSolicitacaoPendente(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), this.grupo.getCodigo());
        return "pagina-grupo-solicitacao?faces-redirect=true";
    }

    public String sairGrupo() {
        fachada.removerMembro(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), grupo.getCodigo());
        aceito = fachada.verificaSolicitacaoPendente(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), this.grupo.getCodigo());
        return "pagina-grupo-solicitacao?faces-redirect=true";
    }

    public List<Topico> topicos() {
        return fachada.topicosGrupo(grupo.getCodigo());
    }

    public List<Comentario> comentariosTopico(Topico topico) {
        return fachada.listarComentariosTopico(topico.getCodigo());
    }

    public String salvarTopicoAluno() {

        if (topico.getConteudo().length() > 0) {

            topico.setDataCriacao(new Date());
            topico.setGrupo(grupo);
            topico.setLoginUsuario(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
            topico.setTipo("Publicacao");
            fachada.salvarTopico(topico);
            topico = new Topico();
        }

        return "pagina-inicial-grupo?faces-redirect=true";
    }

    public void uploadArquivo() {
        String caminho = "C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Arquivos\\doc\\"
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

                String caminhoFoto = "C:\\Users\\Fatinha\\Documents\\Repositorios\\TCC-Mobile-Learning\\Mobile-Edu\\Imagens\\imgPadrao\\doc.png";

                topico.setFoto(caminhoFoto);
                topico.setCaminho(caminho + fileUpload.getFileName());
                topico.setNome(fileUpload.getFileName());
                topico.setGrupo(grupo);
                topico.setLoginUsuario(PegarUsuarioSessao.pegarAlunoSessao().getLogin());
                topico.setDataCriacao(new Date());
                topico.setTipo("Arquivo");

                fachada.salvarTopico(topico);
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
}
