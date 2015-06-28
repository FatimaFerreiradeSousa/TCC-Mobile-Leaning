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
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha de Sousa
 */
@ManagedBean(name = "controladorExercicio")
@SessionScoped
public class ControladorExercicio implements Serializable {

    private Teste exercicio;
    private Teste teste;
    private List<Pergunta> questoesTeste;
    private HttpSession session;
    private ExternalContext externalContext;

    @EJB
    Fachada fachada;

    public ControladorExercicio() {
        exercicio = new Teste();
        questoesTeste = new ArrayList();
        teste = new Teste();
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

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public String salvarPergunta() {

        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");
        exercicio = (Teste) this.session.getAttribute("teste");

        exercicio.setProfessor(professorLogado);
        fachada.salvarExercicio(exercicio);

        this.session.removeAttribute("teste");
        exercicio = new Teste();
        this.questoesTeste = new ArrayList();

        return null;
    }

    public String buscarPerguntas() {

        questoesTeste = fachada.listarPerguntasCategoria(exercicio.getCategoria(), exercicio.getQtdPerguntas());
        exercicio.setQuestoesExercicios(questoesTeste);

        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) externalContext.getSession(false);
        externalContext.getSessionMap().put("teste", exercicio);

        exercicio = (Teste) this.session.getAttribute("teste");
        return null;
    }

    public List<Teste> testesCadastrados() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");
        return fachada.listarTestes(professorLogado.getLogin());
    }
    
    public String removerTeste(Teste teste){
        fachada.removerExercicio(teste);
        return "testesCadastrados?faces-redirect=true";
    }
    
    public String removerPergunta(){
        System.out.println("Chamando o metodo");
        
        return null;
    }
}
