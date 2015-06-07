package com.br.controladores;

import com.br.entidades.Professor;
import com.br.entidades.Questao;
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

    private Questao questao;
    private Resposta resposta1;
    private Resposta resposta2;
    private Resposta resposta3;
    private Resposta resposta4;
    private Resposta resposta5;
    private String mensagem;

    @EJB
    Fachada fachada;

    public ControladorPergunta() {
        this.questao = new Questao();
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

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
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
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) externalContext.getSession(false);
            Professor professorLogado = (Professor) session.getAttribute("professor");

            questao.setProfessor(professorLogado);
            fachada.salvarQuestao(questao);

            this.questao = fachada.consultarQuestao(this.questao.getCodigo());

            fachada.salvarResposta(resposta1);
            fachada.salvarResposta(resposta2);
            fachada.salvarResposta(resposta3);
            fachada.salvarResposta(resposta4);
            fachada.salvarResposta(resposta5);

            this.questao.getRespostas().add(resposta1);
            this.questao.getRespostas().add(resposta2);
            this.questao.getRespostas().add(resposta3);
            this.questao.getRespostas().add(resposta4);
            this.questao.getRespostas().add(resposta5);

            fachada.atualizarQuestao(questao);

            mensagem = "Salvo com sucesso!";
            this.questao = new Questao();
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

    public List<Questao> listarPerguntas() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");
        return fachada.listarQuestoes(professorLogado.getLogin());
    }
    
    public String removerPergunta(Questao questao){
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
    
    public String visualizarPergunta(Questao pergunta){
        this.questao = pergunta;
        return "cadastrarPergunta?faces-redirect=true";
    }
    
    public String atualizarPergunta(){
        return null;
    }
}
