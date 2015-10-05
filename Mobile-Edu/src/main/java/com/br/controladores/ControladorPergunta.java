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
@Named(value = "controladorPergunta")
@SessionScoped
public class ControladorPergunta implements Serializable {

    private Pergunta pergunta;
    private Resposta resposta;
    private String mensagem;
    
    @EJB
    Fachada fachada;

    public ControladorPergunta() {
        this.pergunta = new Pergunta();
        this.resposta = new Resposta();
        this.pergunta.setRespostas(new ArrayList());
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
            return "page-cad-resposta?faces-redirect=true";
        } else {
            return "page-cad-pergunta?faces-redirect=true";
        }
    }

    public String salvarResposta() {
        this.fachada.salvarResposta(resposta);
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
        fachada.salvarQuestao(pergunta);
        this.pergunta = new Pergunta();
        
        this.pergunta = new Pergunta();
        return "page-cad-pergunta?faces-redirect=true";
    }
    
    public String paginaAtualizar(Pergunta pergunta) {
        this.pergunta = pergunta;
        return "page-alterar-pergunta?faces-redirect=true";
    }
    
    public String atualizarPergunta(){
        fachada.atualizarQuestao(pergunta);
        
        return "page-alterar-pergunta?faces-redirect=true";
    }
    
    public String paginaAtualizarResposta(Resposta resposta){
        this.resposta = resposta;
        return "page-alterar-resposta?faces-redirect=true";
    }
    
    public String atualizarResposta(){
        fachada.atualizarResposta(resposta);
        return "page-alterar-pergunta?faces-redirect=true";
    }
    
    public String removerResposta(){
        this.pergunta.getRespostas().remove(resposta);
        
        return "page-alterar-pergunta?faces-redirect=true";
    }
    
    /*inserir uma nova resposta em uma pergunta ja cadastrada*/
     public String paginaCadResposta(){
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
        this.fachada.salvarResposta(resposta);
        pergunta.getRespostas().add(resposta);
        pergunta.setProfessor(PegarUsuarioSessao.pegarProfessorSessao());
        pergunta.setQtdRespostas(pergunta.getRespostas().size());
        
        this.resposta = new Resposta();
        return "page-add-resposta-alt-perg?faces-redirect=true";
    }
    
    public String cancelarPergunta(){
        
        pergunta = new Pergunta();
        resposta = new Resposta();
        return "page-cad-pergunta?faces-redirect=true";
    }
}
