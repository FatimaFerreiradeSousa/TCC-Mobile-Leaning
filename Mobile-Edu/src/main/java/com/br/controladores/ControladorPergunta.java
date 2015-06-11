package com.br.controladores;

import com.br.entidades.Professor;
import com.br.entidades.Pergunta;
import com.br.entidades.Resposta;
import com.br.fachada.Fachada;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha de Sousa
 */
@ManagedBean(name = "ControladorPergunta")
@SessionScoped
public class ControladorPergunta implements Serializable {

    private Pergunta pergunta;
    private Resposta resposta;
    private String mensagem;

    private ExternalContext externalContext;
    private HttpSession session;

    @EJB
    Fachada fachada;

    public ControladorPergunta() {
        this.pergunta = new Pergunta();
        this.resposta = new Resposta();
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

    public String salvarPergunta() {

        if (fachada.consultarQuestao(pergunta.getCodigo()) == null) {
            this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
            this.session = (HttpSession) externalContext.getSession(false);
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            Professor professorLogado = (Professor) this.session.getAttribute("professor");

            pergunta.setQtdRespostas(0);
            pergunta.setProfessor(professorLogado);
            fachada.salvarQuestao(pergunta);

            context.getSessionMap().put("perguntaSessao", pergunta);
            mensagem = "Salvo com sucesso!";
            return "cadastrarResposta?faces-redirect=true";
        } else {
            mensagem = "Codigo inválido!";
            return null;
        }
    }

    public String salvarResposta() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);

        Pergunta perguntaAux = (Pergunta) this.session.getAttribute("perguntaSessao");
        Professor professorLogado = (Professor) this.session.getAttribute("professor");

        this.fachada.salvarResposta(resposta);
        perguntaAux.getRespostas().add(resposta);
        perguntaAux.setProfessor(professorLogado);
        perguntaAux.setQtdRespostas(perguntaAux.getQtdRespostas() + 1);
        fachada.atualizarQuestao(perguntaAux);

        this.resposta = new Resposta();
        return null;
    }

    public List<Pergunta> listarPerguntas() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) this.session.getAttribute("professor");
        return fachada.listarQuestoes(professorLogado.getLogin());
    }

    public String removerPergunta(Pergunta questao) {
        List<Resposta> respostas = questao.getRespostas();
        boolean status = fachada.removerQuestao(questao);
        boolean status1 = fachada.removerResposta(respostas);

        if (status == true && status1 == true) {
            this.mensagem = "Nenhuma Pergunta Cadastrada";
            return this.mensagem;
        } else {
            return null;
        }
    }

    public String removerAtributosSessao() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        this.session.removeAttribute("perguntaSessao");

        return "cadastrarPergunta?faces-redirect=true";
    }
}
