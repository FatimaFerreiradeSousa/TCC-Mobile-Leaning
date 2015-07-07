package com.br.controladores;

import com.br.entidades.*;
import com.br.fachada.Fachada;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
    private ExternalContext context;
    private HttpSession session;

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

    public List<Grupo> listarGruposPorNome() {
        return fachada.buscarGruposPorNome(nomeGrupo);
    }

    public String paginaInicialGrupo(Grupo grupo) {
        this.grupo = grupo;

        return "pagina-inicial-grupo?faces-redirect=true";
    }

    public String participarGrupo() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) context.getSession(false);
        Aluno aluno = (Aluno) session.getAttribute("aluno");

        participaGrupo = new ParticipaGrupo();
        participaGrupo.setAceito(false);
        participaGrupo.setAluno(aluno);
        participaGrupo.setDataParticipacao(new Date());
        participaGrupo.setGrupo(grupo);

        aceito = true;

        fachada.adicionarMembro(participaGrupo);
        participaGrupo = new ParticipaGrupo();

        return "pagina-inicial-grupo?faces-redirect=true";
    }

    public List<Topico> topicos() {
        return fachada.topicosGrupo(grupo.getCodigo());
    }

    public List<Comentario> comentariosTopico(Topico topico) {
        return fachada.listarComentariosTopico(topico.getCodigo());
    }

    public String salvarTopicoAluno() {

        if (topico.getConteudo().length() > 0) {
            context = FacesContext.getCurrentInstance().getExternalContext();
            this.session = (HttpSession) context.getSession(false);
            Aluno aluno = (Aluno) session.getAttribute("aluno");

            topico.setDataCriacao(new Date());
            topico.setGrupo(grupo);
            topico.setPessoa(aluno);

            fachada.salvarTopico(topico);
            topico = new Topico();
        }

        return "pagina-inicial-grupo?faces-redirect=true";
    }
}
