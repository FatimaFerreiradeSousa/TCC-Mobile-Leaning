package com.br.controladores;

import com.br.entidades.Professor;
import com.br.entidades.Pergunta;
import com.br.entidades.Resposta;
import com.br.fachada.Fachada;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "ControladorPergunta")
@SessionScoped
public class ControladorPergunta implements Serializable {

    private Pergunta pergunta;
    private Resposta resposta;
    private String mensagem;
    private ExternalContext externalContext;
    private HttpSession session;
    private List<Resposta> respostas;

    @EJB
    Fachada fachada;

    public ControladorPergunta() {
        this.pergunta = new Pergunta();
        this.resposta = new Resposta();
        this.respostas = new ArrayList();
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

    public String adicionarRespostas() {

        if (fachada.consultarQuestao(pergunta.getCodigo()) == null) {
            pergunta.setQtdRespostas(0);
            return "cadastrarResposta?faces-redirect=true";
        } else {
            mensagem = "Codigo inválido!";
            return "cadastrarPergunta?faces-redirect=true";
        }
    }

    public String salvarResposta() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) this.session.getAttribute("professor");

        this.fachada.salvarResposta(resposta);
        this.respostas.add(resposta);
        pergunta.setRespostas(respostas);
        pergunta.setProfessor(professorLogado);
        pergunta.setQtdRespostas(respostas.size());
        
        this.resposta = new Resposta();
        return "cadastrarResposta?faces-redirect=true";
    }

    public List<Pergunta> listarPerguntas() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) this.session.getAttribute("professor");
        return fachada.listarQuestoes(professorLogado.getLogin());
    }

    public String removerPergunta(Pergunta questao) {
        List<Resposta> respostas1 = questao.getRespostas();
        boolean status = fachada.removerQuestao(questao);
        boolean status1 = fachada.removerResposta(respostas1);

        if (status == true && status1 == true) {
            this.mensagem = "Nenhuma Pergunta Cadastrada";
            return this.mensagem;
        } else {
            return null;
        }
    }

    public String salvarPergunta() {
        fachada.salvarQuestao(pergunta);
        this.pergunta = new Pergunta();
        this.respostas = new ArrayList();
        
        this.pergunta = new Pergunta();
        return "cadastrarPergunta?faces-redirect=true";
    }
    
    public String paginaAtualizar(Pergunta pergunta) {
        this.pergunta = pergunta;
        return "editarPergunta?faces-redirect=true";
    }
    
    public String atualizarPergunta(){
        fachada.atualizarQuestao(pergunta);
        return "editarPergunta?faces-redirect=true";
    }
    
    public String paginaAtualizarResposta(Resposta resposta){
        this.resposta = resposta;
        return "editarResposta?faces-redirect=true";
    }
    
    public String atualizarResposta(){
        fachada.atualizarResposta(resposta);
        return "editarPergunta?faces-redirect=true";
    }
}
