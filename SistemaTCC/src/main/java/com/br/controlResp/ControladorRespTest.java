package com.br.controlResp;

import com.br.entidades.Questao;
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
    
    private Questao questao1;
    private Questao questao2;
    private Questao questao3;
    private Questao questao4;
    private Questao questao5;
    
    public ControladorRespTest() {
        this.testeSelecionado = new Teste();
        
        this.questao1 = new Questao();
        this.questao2 = new Questao();
        this.questao3 = new Questao();
        this.questao4 = new Questao();
        this.questao5 = new Questao();
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

    public Questao getQuestao1() {
        return questao1 = testeSelecionado.getQuestoesExercicios().get(0);
    }

    public Questao getQuestao2() {
        return questao2 = testeSelecionado.getQuestoesExercicios().get(1);
    }

    public Questao getQuestao3() {
        return questao3 = testeSelecionado.getQuestoesExercicios().get(2);
    }
    
    public Questao getQuestao4() {
        return questao4 = testeSelecionado.getQuestoesExercicios().get(3);
    }

    public Questao getQuestao5() {
        return questao5 = testeSelecionado.getQuestoesExercicios().get(4);
    }
}
