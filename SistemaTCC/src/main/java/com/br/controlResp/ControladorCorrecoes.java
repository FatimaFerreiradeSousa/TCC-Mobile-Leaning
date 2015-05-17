package com.br.controlResp;

import com.br.entidades.Resposta;
import com.br.fachada.Fachada;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Fatinha
 */
@Named(value = "controladorCorrecoes")
@SessionScoped
public class ControladorCorrecoes implements Serializable {

    @EJB
    Fachada fachada;

    private String respostaSelecionada1;
    private String respostaSelecionada2;
    private String respostaSelecionada3;
    private String respostaSelecionada4;
    private String respostaSelecionada5;
    private float pontuacao;

    public ControladorCorrecoes() {
        this.respostaSelecionada1 = new String();
        this.respostaSelecionada2 = new String();
        this.respostaSelecionada3 = new String();
        this.respostaSelecionada4 = new String();
        this.respostaSelecionada5 = new String();
        this.pontuacao = 0;
    }

    /*Resposta Selecionada*/
    public String getRespostaSelecionada1() {
        return respostaSelecionada1;
    }

    public void setRespostaSelecionada1(String respostaSelecionada1) {
        this.respostaSelecionada1 = respostaSelecionada1;
    }

    public String getRespostaSelecionada2() {
        return respostaSelecionada2;
    }

    public void setRespostaSelecionada2(String respostaSelecionada2) {
        this.respostaSelecionada2 = respostaSelecionada2;
    }

    public String getRespostaSelecionada3() {
        return respostaSelecionada3;
    }

    public void setRespostaSelecionada3(String respostaSelecionada3) {
        this.respostaSelecionada3 = respostaSelecionada3;
    }

    public String getRespostaSelecionada4() {
        return respostaSelecionada4;
    }

    public void setRespostaSelecionada4(String respostaSelecionada4) {
        this.respostaSelecionada4 = respostaSelecionada4;
    }

    public String getRespostaSelecionada5() {
        return respostaSelecionada5;
    }

    public void setRespostaSelecionada5(String respostaSelecionada5) {
        this.respostaSelecionada5 = respostaSelecionada5;
    }

    public float getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(float pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String respostasRecebidas() {
        System.out.println("Resposta Selecionada 1: " + respostaSelecionada1);
        System.out.println("Resposta Selecionada 2: " + respostaSelecionada2);
        System.out.println("Resposta Selecionada 3: " + respostaSelecionada3);
        System.out.println("Resposta Selecionada 4: " + respostaSelecionada4);
        System.out.println("Resposta Selecionada 5: " + respostaSelecionada5);

        Resposta resp1 = fachada.buscarRespostaCodigo(Integer.parseInt(respostaSelecionada1));
        Resposta resp2 = fachada.buscarRespostaCodigo(Integer.parseInt(respostaSelecionada2));
        Resposta resp3 = fachada.buscarRespostaCodigo(Integer.parseInt(respostaSelecionada3));
        Resposta resp4 = fachada.buscarRespostaCodigo(Integer.parseInt(respostaSelecionada4));
        Resposta resp5 = fachada.buscarRespostaCodigo(Integer.parseInt(respostaSelecionada5));

        if (resp1.getRespostaCerta() == true) {
            System.out.println("Resposta Correta: " + resp1.getConteudo());
            this.pontuacao += resp1.getQuestao().getPontuacao();
        }

        if (resp2.getRespostaCerta() == true) {
            System.out.println("Resposta Correta: " + resp2.getConteudo());
            this.pontuacao += resp2.getQuestao().getPontuacao();
        }

        if (resp3.getRespostaCerta() == true) {
            System.out.println("Resposta Correta: " + resp3.getConteudo());
            this.pontuacao += resp3.getQuestao().getPontuacao();
        }

        if (resp4.getRespostaCerta() == true) {
            System.out.println("Resposta Correta: " + resp4.getConteudo());
            this.pontuacao += resp4.getQuestao().getPontuacao();
        }

        if (resp5.getRespostaCerta() == true) {
            System.out.println("Resposta Correta: " + resp5.getConteudo());
            this.pontuacao += resp5.getQuestao().getPontuacao();
        }

        System.out.println("Pontuação: " +this.pontuacao);
        return null;
    }
}
