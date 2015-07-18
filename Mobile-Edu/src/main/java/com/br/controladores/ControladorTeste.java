package com.br.controladores;

import com.br.entidades.*;
import com.br.fachada.Fachada;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Fatinha
 */
@Named(value = "controladorTeste")
@SessionScoped
public class ControladorTeste implements Serializable {

    @EJB
    private Fachada fachada; 
    private Teste teste;
    private List<Resposta> respostas;
    private int tamanho;
    private List<Pergunta> perguntas;
    private Pergunta pergunta;
    private boolean comecar = false;
    private int contador;
    private float resultado;
    private List<Integer> codPerguntas;
    
    public ControladorTeste() {
        teste = new Teste();
        respostas = new ArrayList();
        tamanho = 0;
        perguntas = new ArrayList();
        pergunta = new Pergunta();
        contador = 1;
        resultado = 0;
        codPerguntas = new ArrayList();
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public boolean isComecar() {
        return comecar;
    }

    public void setComecar(boolean comecar) {
        this.comecar = comecar;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    public List<Integer> getCodPerguntas() {
        return codPerguntas;
    }

    public void setCodPerguntas(List<Integer> codPerguntas) {
        this.codPerguntas = codPerguntas;
    }
    
    public String comecarAResponder(){
        comecar = true;
        tamanho = 0;
        pergunta = teste.getQuestoesExercicios().get(tamanho);
        tamanho ++;
        return "md-visualizar-teste?faces-redirect=true";
    }

    public String paginaVisualizarTeste(Topico topico){
        teste = fachada.buscarExercicio(topico.getCodigoTeste());
        perguntas = teste.getQuestoesExercicios();
        return "md-visualizar-teste?faces-redirect=true";
    }
    
    public String enviarRespostas(Resposta resposta, int codigoPergunta){
        this.respostas.add(resposta);
        this.codPerguntas.add(codigoPergunta);
        
        int aux = teste.getQtdPerguntas();
        
        if(tamanho < aux){
            pergunta = teste.getQuestoesExercicios().get(tamanho);
        }
        
        if(tamanho == aux){
            tamanho = 0;
            return "pagina-visualizar-resultado?faces-redirect=true";
        }
        
        tamanho++;
        contador++;
        
        return "md-visualizar-teste?faces-redirect=true";
    }
    
    public String corrigirTeste(){
        
        for(Resposta r: respostas){
            System.out.println(pergarValorPergunta(r));
        }
        
        return "pagina-visualizar-resultado?faces-redirect=true";
    }
    
    public float pergarValorPergunta(Resposta resposta){
        float valor = 0;
        
        for(Pergunta p: perguntas){
            for(Resposta r: p.getRespostas()){
                if(r.getNumero() == resposta.getNumero()){
                    valor = p.getPontuacao();
                }
            }
        }
        
        return valor;
    }
}
