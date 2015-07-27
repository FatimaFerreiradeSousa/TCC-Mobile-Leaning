package com.br.controladores;

import com.br.entidades.Pergunta;
import com.br.entidades.Resposta;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

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

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
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
        this.fachada.salvarResposta(resposta);
        this.respostas.add(resposta);
        pergunta.setRespostas(respostas);
        pergunta.setProfessor(PegarUsuarioSessao.pegarProfessorSessao());
        pergunta.setQtdRespostas(respostas.size());
        
        this.resposta = new Resposta();
        return "cadastrarResposta?faces-redirect=true";
    }

    public List<Pergunta> listarPerguntas() {        
        return fachada.listarQuestoes(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }

    public String removerPergunta() {
        List<Resposta> respostas1 = pergunta.getRespostas();
        boolean status = fachada.removerQuestao(pergunta);
        boolean status1 = fachada.removerResposta(respostas1);

        if (status == true && status1 == true) {
            this.mensagem = "Nenhuma Pergunta Cadastrada";
            return "perguntasCadastradas?faces-redirect=true";
        } else {
            return "perguntasCadastradas?faces-redirect=true";
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
    
    public String removerResposta(){
        this.pergunta.getRespostas().remove(resposta);
        
        return "editarPergunta?faces-redirect=true";
    }
}
