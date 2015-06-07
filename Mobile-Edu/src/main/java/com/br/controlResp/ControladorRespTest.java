package com.br.controlResp;

import com.br.entidades.Pergunta;
import com.br.entidades.Teste;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
/**
 *
 * @author Fatinha
 */
@Named(value = "controladorRespTest")
@SessionScoped
public class ControladorRespTest implements Serializable {

    private Teste testeSelecionado;
    
    private Pergunta questao1;
    private Pergunta questao2;
    private Pergunta questao3;
    private Pergunta questao4;
    private Pergunta questao5;
    
    public ControladorRespTest() {
        this.testeSelecionado = new Teste();
        
        this.questao1 = new Pergunta();
        this.questao2 = new Pergunta();
        this.questao3 = new Pergunta();
        this.questao4 = new Pergunta();
        this.questao5 = new Pergunta();
    }

    public Teste getTesteSelecionado() {
        return testeSelecionado;
    }

    public void setTesteSelecionado(Teste testeSelecionado) {
        this.testeSelecionado = testeSelecionado;
    }

    public String mostrarTesteSelecionado(Teste teste) {
        this.testeSelecionado = teste;
        return "mostrarTeste";
    }

    public Pergunta getQuestao1() {
        return questao1 = testeSelecionado.getQuestoesExercicios().get(0);
    }

    public Pergunta getQuestao2() {
        return questao2 = testeSelecionado.getQuestoesExercicios().get(1);
    }

    public Pergunta getQuestao3() {
        return questao3 = testeSelecionado.getQuestoesExercicios().get(2);
    }
    
    public Pergunta getQuestao4() {
        return questao4 = testeSelecionado.getQuestoesExercicios().get(3);
    }

    public Pergunta getQuestao5() {
        return questao5 = testeSelecionado.getQuestoesExercicios().get(4);
    }
}
