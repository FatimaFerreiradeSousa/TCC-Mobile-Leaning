package com.br.controladores;

import com.br.entidades.Professor;
import com.br.entidades.Questao;
import com.br.entidades.Resposta;
import com.br.fachada.Fachada;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Fatinha de Sousa
 */
@ManagedBean(name = "controladorQuestao")
@SessionScoped
public class ControladorQuestao implements Serializable {

    Questao questao;
    FacesMessage facesMessage;
    FacesContext context;
    Resposta resp1;
    Resposta resp2;
    Resposta resp3;
    Resposta resp4;
    Resposta resp5;
    private String mensagem;

    @EJB
    Fachada fachada;

    public ControladorQuestao() {
        questao = new Questao();
        resp1 = new Resposta();
        resp2 = new Resposta();
        resp3 = new Resposta();
        resp4 = new Resposta();
        resp5 = new Resposta();
    }

    public Resposta getResp1() {
        return resp1;
    }

    public void setResp1(Resposta resp1) {
        this.resp1 = resp1;
    }

    public Resposta getResp2() {
        return resp2;
    }

    public void setResp2(Resposta resp2) {
        this.resp2 = resp2;
    }

    public Resposta getResp3() {
        return resp3;
    }

    public void setResp3(Resposta resp3) {
        this.resp3 = resp3;
    }

    public Resposta getResp4() {
        return resp4;
    }

    public void setResp4(Resposta resp4) {
        this.resp4 = resp4;
    }

    public Resposta getResp5() {
        return resp5;
    }

    public void setResp5(Resposta resp5) {
        this.resp5 = resp5;
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

    public String salvar() {

        if (fachada.consultarQuestao(questao.getCodigo()) == null) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            HttpSession session = (HttpSession) externalContext.getSession(false);
            Professor professorLogado = (Professor) session.getAttribute("usuario"); 
           
            questao.setProfessor(professorLogado);
            fachada.salvarQuestao(questao);
            resp1.setQuestao(questao);
            resp2.setQuestao(questao);
            resp3.setQuestao(questao);
            resp4.setQuestao(questao);
            resp5.setQuestao(questao);

            fachada.salvarResposta(resp1);
            fachada.salvarResposta(resp2);
            fachada.salvarResposta(resp3);
            fachada.salvarResposta(resp4);
            fachada.salvarResposta(resp5);

            facesMessage = new FacesMessage("Salvo Com Sucesso!");
            mensagem = "Salvo com sucesso!";
            questao = new Questao();
            resp1 = new Resposta();
            resp2 = new Resposta();
            resp3 = new Resposta();
            resp4 = new Resposta();
            resp5 = new Resposta();
        } else {
            facesMessage = new FacesMessage("Codigo Já Cadastrado!");
            mensagem = "Codigo inválido!";
        }

        this.context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        return null;
    }

    public String consultar() {
        questao = fachada.consultarQuestao(questao.getCodigo());
        List<Resposta> respostas = fachada.listarRespostas(questao.getCodigo());

        if (questao == null && respostas.isEmpty()) {
            facesMessage = new FacesMessage("Nenhum Registro Encontrado!");
            this.context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
        } else {
            if (respostas.size() > 0) {
                if (respostas.size() == 1) {
                    resp1 = respostas.get(0);
                    resp2 = new Resposta();
                    resp3 = new Resposta();
                    resp4 = new Resposta();
                    resp5 = new Resposta();
                }

                if (respostas.size() == 2) {
                    resp1 = respostas.get(0);
                    resp2 = respostas.get(1);
                    resp3 = new Resposta();
                    resp4 = new Resposta();
                    resp5 = new Resposta();
                }

                if (respostas.size() == 3) {
                    resp1 = respostas.get(0);
                    resp2 = respostas.get(1);
                    resp3 = respostas.get(2); 
                    resp4 = new Resposta();
                    resp5 = new Resposta();
                }

                if (respostas.size() == 4) {
                    resp1 = respostas.get(0);
                    resp2 = respostas.get(1);
                    resp3 = respostas.get(2);
                    resp4 = respostas.get(3);
                    resp5 = new Resposta();
                }

                if (respostas.size() == 5) {
                    resp1 = respostas.get(0);
                    resp2 = respostas.get(1);
                    resp3 = respostas.get(2);
                    resp4 = respostas.get(3);
                    resp5 = respostas.get(4);
                }
            } else {
                resp1 = new Resposta();
                resp2 = new Resposta();
                resp3 = new Resposta();
                resp4 = new Resposta();
                resp5 = new Resposta();
            }
        }

        return null;
    }

    public String atualizar() {
        fachada.atualizarQuestao(questao);
        resp1.setQuestao(questao);
        resp2.setQuestao(questao);
        resp3.setQuestao(questao);
        resp4.setQuestao(questao);
        resp5.setQuestao(questao);
        
        fachada.atualizarResposta(resp1);
        fachada.atualizarResposta(resp2);
        fachada.atualizarResposta(resp3);
        fachada.atualizarResposta(resp4);
        fachada.atualizarResposta(resp5);
        facesMessage = new FacesMessage("Dados Atualizados Com Sucesso!");
        this.context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        return null;
    }

    public String remover() {
        fachada.removerResposta(resp1);
        fachada.removerResposta(resp2);
        fachada.removerResposta(resp3);
        fachada.removerResposta(resp4);
        fachada.removerResposta(resp5);
        fachada.removerQuestao(questao);
        facesMessage = new FacesMessage("Dados Removidos Com Sucesso!");
        this.context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        questao = new Questao();
        resp1 = new Resposta();
        resp2 = new Resposta();
        resp3 = new Resposta();
        resp4 = new Resposta();
        resp5 = new Resposta();
        return null;
    }
}
