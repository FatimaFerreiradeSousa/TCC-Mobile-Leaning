package com.br.controladores;

import com.br.entidades.Pergunta;
import com.br.entidades.Resposta;
import com.br.fachada.Service;
import com.br.sessao.PegarUsuarioSessao;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorPergunta")
@SessionScoped
public class ControladorPergunta implements Serializable {

    private Pergunta pergunta;
    private Resposta resposta;
    private String mensagem;
    private boolean opcao;
    private FacesContext context;

    @EJB
    Service fachada;

    public ControladorPergunta() {
        this.pergunta = new Pergunta();
        this.resposta = new Resposta();
        this.pergunta.setRespostas(new ArrayList());
        opcao = false;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isOpcao() {
        return opcao;
    }

    public String adicionarRespostas() {

        if (fachada.consultarQuestao(pergunta.getCodigo()) == null) {
            pergunta.setQtdRespostas(0);
            return "page-cad-resposta?faces-redirect=true";
        } else {
            return "page-cad-pergunta?faces-redirect=true";
        }
    }

    public String salvarResposta() {
        pergunta.getRespostas().add(resposta);
        pergunta.setProfessor(PegarUsuarioSessao.pegarProfessorSessao());
        pergunta.setQtdRespostas(pergunta.getRespostas().size());

        this.resposta = new Resposta();
        return "page-cad-resposta?faces-redirect=true";
    }

    public List<Pergunta> listarPerguntas() {
        return fachada.listarQuestoes(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }

    public String removerPergunta() {
        List<Resposta> respostas1 = pergunta.getRespostas();
        boolean status = fachada.removerQuestao(pergunta);
        boolean status1 = fachada.removerResposta(respostas1);

        if (status == true && status1 == true) {
            return "page-listar-perguntas?faces-redirect=true";
        } else {
            return "page-listar-perguntas?faces-redirect=true";
        }
    }

    public String salvarPergunta() {
        this.context = FacesContext.getCurrentInstance();

        if (fachada.salvarQuestao(pergunta)) {
            this.resposta = new Resposta();
            this.pergunta = new Pergunta();
            this.pergunta.setRespostas(new ArrayList());

            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
                    "Pergunta cadastrada com sucesso.");

            context.addMessage(null, facesMessage);
            opcao = true;

        } else {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Erro ao realizar cadastro.",
                    "");

            context.addMessage(null, facesMessage);
            opcao = true;
        }

        return null;
    }

    public String paginaCadastrarPergunta() {
        this.opcao = false;
        return "page-cad-pergunta?faces-redirect=true";
    }

    public String paginaAtualizar(Pergunta pergunta) {
        this.pergunta = pergunta;
        return "page-alterar-pergunta?faces-redirect=true";
    }

    public String atualizarPergunta() {
        this.context = FacesContext.getCurrentInstance();

        if (fachada.atualizarQuestao(pergunta)) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Pergunta alterada com sucesso.",
                    "");

            context.addMessage(null, facesMessage);
            opcao = true;
        } else {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Erro ao alterar pergunta.",
                    "");

            context.addMessage(null, facesMessage);
            opcao = true;
        }

        return null;
    }

    public String paginaAtualizarResposta(Resposta resposta) {
        this.resposta = resposta;
        return "page-alterar-resposta?faces-redirect=true";
    }

    public String atualizarResposta() {
        this.context = FacesContext.getCurrentInstance();

        if (fachada.atualizarResposta(resposta)) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Resposta alterada com sucesso.",
                    "");

            context.addMessage(null, facesMessage);
            opcao = true;
        } else {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Erro ao alterar resposta.",
                    "");

            context.addMessage(null, facesMessage);
            opcao = true;
        }

        return null;
    }

    public String removerResposta() {
        this.pergunta.getRespostas().remove(resposta);
        pergunta.setQtdRespostas(pergunta.getRespostas().size());
        fachada.atualizarQuestao(pergunta);
        fachada.removerResp(resposta);

        return "page-alterar-pergunta?faces-redirect=true";
    }

    /*inserir uma nova resposta em uma pergunta ja cadastrada*/
    public String paginaCadResposta() {
        this.resposta = new Resposta();
        return "page-add-resposta-alt-perg?faces-redirect=true";
    }

    public String addRespostaAlterarPergunta() {
        pergunta.setProfessor(PegarUsuarioSessao.pegarProfessorSessao());
        pergunta.setQtdRespostas(pergunta.getRespostas().size());

        fachada.atualizarQuestao(pergunta);
           
        this.resposta = new Resposta();
        return "page-alterar-pergunta?faces-redirect=true";
    }

    public String novaResposta() {
        this.context = FacesContext.getCurrentInstance();
        if (this.fachada.salvarResposta(resposta)) {;
            pergunta.getRespostas().add(resposta);
            pergunta.setProfessor(PegarUsuarioSessao.pegarProfessorSessao());
            pergunta.setQtdRespostas(pergunta.getRespostas().size());

            this.resposta = new Resposta();

            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Nova resposta cadastrada.",
                    "");

            context.addMessage(null, facesMessage);
            opcao = true;
        } else {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Erro ao salvar resposta.",
                    "");

            context.addMessage(null, facesMessage);
            opcao = true;
        }
        return null;
    }

    public String cancelarPergunta() {
        pergunta = new Pergunta();
        resposta = new Resposta();
        opcao = false;
        return "page-cad-pergunta?faces-redirect=true";
    }
    
    public String paginas(){
        opcao = false;
        return "page-listar-perguntas?faces-redirect=true";
    }
}
