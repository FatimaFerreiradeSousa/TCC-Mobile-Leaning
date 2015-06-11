package com.br.controladores;

import com.br.entidades.Professor;
import com.br.entidades.Pergunta;
import com.br.entidades.Teste;
import com.br.fachada.Fachada;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "controladorExercicio")
@SessionScoped
public class ControladorExercicio implements Serializable {

    private Teste exercicio;
    private FacesMessage facesMessage;
    private FacesContext context;
    private List<Pergunta> questoesTeste;
    private Pergunta questao;
    private HttpSession session;
    private ExternalContext externalContext;

    @EJB
    Fachada fachada;

    public ControladorExercicio() {
        exercicio = new Teste();
        questoesTeste = new ArrayList();
        questao = new Pergunta();
    }

    public Teste getExercicio() {
        return exercicio;
    }

    public void setExercicio(Teste exercicio) {
        this.exercicio = exercicio;
    }

    public List<Pergunta> getQuestoesTeste() {
        return questoesTeste;
    }

    public Pergunta getQuestao() {
        return questao;
    }

    public void setQuestao(Pergunta questao) {
        this.questao = questao;
    }

    public String salvar() {

        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        exercicio = (Teste) this.session.getAttribute("teste");
        Professor professorLogado = (Professor) session.getAttribute("professor");
        
        if (exercicio.getCodigo() != null) {
            if (fachada.buscarExercicio(exercicio.getCodigo()) == null) {

                if (exercicio.getQuestoesExercicios().size() == 5) {
                    exercicio.setProfessor(professorLogado);
                    fachada.salvarExercicio(exercicio);
                    fachada.atualizarProfessor(professorLogado);
                    exercicio = new Teste();
                    questoesTeste = new ArrayList();
                    exercicio.setQuestoesExercicios(null);
                    fachada.concluirTeste();

                    facesMessage = new FacesMessage("Salvo Com Sucesso!");
                    this.session = (HttpSession) externalContext.getSession(false);
                    externalContext.getSessionMap().put("teste", exercicio);
                }else{
                    facesMessage = new FacesMessage("Seu teste precisa ter cinco questões!");
                    this.session = (HttpSession) externalContext.getSession(false);
                    externalContext.getSessionMap().put("teste", exercicio);
                    exercicio.setQuestoesExercicios(null);
                    questoesTeste = new ArrayList();
                    fachada.concluirTeste();
                }
            } else {
                facesMessage = new FacesMessage("Codigo Já Cadastrado!");
            }

            this.context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
        }

        return "voltarPage";
    }

    public String buscar() {
        exercicio = fachada.buscarExercicio(exercicio.getCodigo());

        if (exercicio == null) {
            facesMessage = new FacesMessage("Nenhum Registro Encontrado!");
            this.context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
        }

        return null;
    }

    public String atualizar() {
        exercicio.setQuestoesExercicios(questoesTeste);
        fachada.atualizarExercicio(exercicio);
        facesMessage = new FacesMessage("Nenhum Registro Encontrado!");
        this.context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);

        return null;
    }

    public String remover() {
        fachada.removerExercicio(exercicio);
        exercicio = new Teste();
        facesMessage = new FacesMessage("Dados Removiros Com Sucesso!");
        this.context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        return null;
    }

    public String proximaPagina() {

        if (fachada.buscarExercicio(exercicio.getCodigo()) == null) {
            externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            this.session = (HttpSession) externalContext.getSession(false);
            List<Pergunta> aux = new ArrayList();
            exercicio.setQuestoesExercicios(aux);
            externalContext.getSessionMap().put("teste", exercicio);
            return "addPerguntaTeste?faces-redirect=true";
        } else {
            facesMessage = new FacesMessage("Codigo Invalido!");
            this.context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
            return null;
        }
    }

    public List<Pergunta> listarQuestoes() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");
        return fachada.listarQuestoes(professorLogado.getLogin());
    }

    public String addQuestaoTeste(Pergunta questao) {
        fachada.addQuestaoTeste(questao);
        questoesTeste = fachada.listarQuestaoTeste();
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        exercicio = (Teste) this.session.getAttribute("teste");
        exercicio.setQuestoesExercicios(questoesTeste);

        return null;
    }

    public String cancelarCadastro() {
        return "cadExercicio?faces-redirect=true";
    }

    public List<Teste> testesCadastrados() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");
        return fachada.listarTestes(professorLogado.getLogin());
    }
}
