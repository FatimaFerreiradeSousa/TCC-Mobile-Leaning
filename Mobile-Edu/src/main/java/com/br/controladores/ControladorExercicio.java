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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorExercicio")
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

    public String salvarTeste() {

        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.session = (HttpSession) externalContext.getSession(false);
        Professor professorLogado = (Professor) session.getAttribute("professor");
        
        exercicio.setProfessor(professorLogado);
        fachada.salvarExercicio(exercicio);

        exercicio = new Teste();
        this.questoesTeste = new ArrayList();

        return "cadExercicio?faces-redirect=true";
    }
    
        
    public String removerPerguntaCadastrar(Pergunta pergunta){
        
        this.questoesTeste.remove(pergunta);
        this.exercicio.setQtdPerguntas(this.questoesTeste.size());
        
        return "cadExercicio?faces-redirect=true";
    }

    public String buscarPerguntas() {

        questoesTeste = fachada.listarPerguntasCategoria(exercicio.getCategoria(), exercicio.getQtdPerguntas());
        exercicio.setQuestoesExercicios(questoesTeste);

        return "cadExercicio?faces-redirect=true";
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
    
    public String visualizarTeste(Teste teste){
        this.teste = teste;
        this.questoesTeste = teste.getQuestoesExercicios();
        
        return "editarTeste?faces-redirect=true";
    }
    
    public String atualizarTeste(){
        teste.setQtdPerguntas(this.questoesTeste.size());
        teste.setQuestoesExercicios(questoesTeste);
        fachada.atualizarExercicio(teste);
        
        return "editarTeste?faces-redirect=true";
    }
    
    public String removerPergunta(Pergunta pergunta){
        
        this.questoesTeste.remove(pergunta);
        this.teste.setQtdPerguntas(this.questoesTeste.size());
        
        return "editarTeste?faces-redirect=true";
    }

}
