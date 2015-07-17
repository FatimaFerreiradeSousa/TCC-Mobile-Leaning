package com.br.controladores;

import com.br.entidades.Pergunta;
import com.br.entidades.Teste;
import com.br.entidades.Topico;
import com.br.fachada.Fachada;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    
    public ControladorTeste() {
        teste = new Teste();
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public String paginaVisualizarTeste(Topico topico){
        teste = fachada.buscarExercicio(topico.getCodigoTeste());
        
        return "pagina-responder-teste?faces-redirect=true";
    }
}
