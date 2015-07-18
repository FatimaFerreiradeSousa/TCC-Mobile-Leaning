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
    private List<String> respostas;
    
    public ControladorTeste() {
        teste = new Teste();
        respostas = new ArrayList();
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
    }

    public String paginaVisualizarTeste(Topico topico){
        teste = fachada.buscarExercicio(topico.getCodigoTeste());
        return "pagina-responder-teste?faces-redirect=true";
    }
    
    public String enviarRespostas(){
        System.out.println("Aqui!");
        for(String resposta: respostas){
            System.out.println("Numero: " +resposta);
        }
        
        return "pagina-responder-teste?faces-redirect=true";
    }
}
