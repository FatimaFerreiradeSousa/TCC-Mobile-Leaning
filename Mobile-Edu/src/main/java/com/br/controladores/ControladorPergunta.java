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

    private Pergunta questao;
    private Resposta resposta1;
    private Resposta resposta2;
    private Resposta resposta3;
    private Resposta resposta4;
    private Resposta resposta5;
    private String mensagem;

    private ExternalContext externalContext;
    private HttpSession session;

    @EJB
    Fachada fachada;

    public ControladorPergunta() {
        this.questao = new Pergunta();
        this.resposta1 = new Resposta();
        this.resposta2 = new Resposta();
        this.resposta3 = new Resposta();
        this.resposta4 = new Resposta();
        this.resposta5 = new Resposta();
    }

    public Resposta getResposta1() {
        return resposta1;
    }

    public void setResposta1(Resposta resposta1) {
        this.resposta1 = resposta1;
    }

    public Resposta getResposta2() {
        return resposta2;
    }

    public void setResposta2(Resposta resposta2) {
        this.resposta2 = resposta2;
    }

    public Resposta getResposta3() {
        return resposta3;
    }

    public void setResposta3(Resposta resposta3) {
        this.resposta3 = resposta3;
    }

    public Resposta getResposta4() {
        return resposta4;
    }

    public void setResposta4(Resposta resposta4) {
        this.resposta4 = resposta4;
    }

    public Resposta getResposta5() {
        return resposta5;
    }

    public void setResposta5(Resposta resposta5) {
        this.resposta5 = resposta5;
    }

    public Pergunta getQuestao() {
        return questao;
    }

    public void setQuestao(Pergunta questao) {
        this.questao = questao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String salvarPergunta() {

        if (fachada.consultarQuestao(questao.getCodigo()) == null) {
            this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
            this.session = (HttpSession) externalContext.getSession(false);
            Professor professorLogado = (Professor) this.session.getAttribute("professor");

            questao.setProfessor(professorLogado);
            fachada.salvarQuestao(questao);

            this.resposta1.setPergunta(questao);
            this.resposta2.setPergunta(questao);
            this.resposta3.setPergunta(questao);
            this.resposta4.setPergunta(questao);
            this.resposta5.setPergunta(questao);

            fachada.salvarResposta(resposta1);
            fachada.salvarResposta(resposta2);
            fachada.salvarResposta(resposta3);
            fachada.salvarResposta(resposta4);
            fachada.salvarResposta(resposta5);

            fachada.atualizarQuestao(questao);

            mensagem = "Salvo com sucesso!";
            this.questao = new Pergunta();
            this.resposta1 = new Resposta();
            this.resposta2 = new Resposta();
            this.resposta3 = new Resposta();
            this.resposta4 = new Resposta();
            this.resposta5 = new Resposta();
        } else {
            mensagem = "Codigo inválido!";
        }

        return null;
    }

    public List<Pergunta> listarPerguntas() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) this.session.getAttribute("professor");
        return fachada.listarQuestoes(professorLogado.getLogin());
    }

    public String removerPergunta(Pergunta questao) {
        this.resposta1 = this.questao.getRespostas().get(0);
        this.resposta2 = this.questao.getRespostas().get(1);
        this.resposta3 = this.questao.getRespostas().get(2);
        this.resposta4 = this.questao.getRespostas().get(3);
        this.resposta5 = this.questao.getRespostas().get(4);

        fachada.removerResposta(resposta1);
        fachada.removerResposta(resposta2);
        fachada.removerResposta(resposta3);
        fachada.removerResposta(resposta4);
        fachada.removerResposta(resposta5);

        fachada.removerQuestao(questao);
        return null;
    }

    public String atualizarPergunta() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) this.session.getAttribute("professor");

        this.questao.setProfessor(professorLogado);
        fachada.atualizarQuestao(questao);

        System.out.println("Resposta: " +respostasPergunta(questao).get(0).getCodigo());
        this.resposta1 = respostasPergunta(questao).get(0);
        this.resposta1.setPergunta(questao);
        fachada.atualizarResposta(resposta1);
        return null;
    }
    
    public List<Resposta> respostasPergunta(Pergunta pergunta){
        return fachada.listarRespostas(pergunta.getCodigo());
    }

    public String consultarPergunta() {
        this.questao = fachada.consultarQuestao(this.questao.getCodigo());

        if (this.questao != null) {
            List<Resposta> respostas = fachada.listarRespostas(this.questao.getCodigo());
            this.resposta1 = respostas.get(0);
            this.resposta2 = respostas.get(1);
            this.resposta3 = respostas.get(2);
            this.resposta4 = respostas.get(3);
            this.resposta5 = respostas.get(4);
        }
        return null;
    }

    public String visualizarPergunta(Pergunta pergunta) {
        return "visualizarPergunta?faces-redirect=true";
    }
}
