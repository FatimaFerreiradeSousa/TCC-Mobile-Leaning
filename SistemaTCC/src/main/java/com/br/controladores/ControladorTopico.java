package com.br.controladores;

import com.br.entidades.Grupo;
import com.br.entidades.Professor;
import com.br.entidades.Topico;
import com.br.fachada.Fachada;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static javafx.scene.input.KeyCode.P;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha
 */
@ManagedBean(name = "controladorTopico")
@SessionScoped
public class ControladorTopico implements Serializable {

    @EJB
    private Fachada fachada;
    private Topico topico;
    private String mensagem;

    public ControladorTopico() {
        topico = new Topico();
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void salvarTopico() {
        if (topico.getConteudo().length() > 0) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) context.getSession(false);
            Grupo grupo = (Grupo) session.getAttribute("grupo");
            Professor professorLogado = (Professor) session.getAttribute("professor");
            List<Topico> topicos = professorLogado.getTopicosCriados();
            topicos.size();
            
            topico.setDataCriacao(new Date());
            topico.setGrupo(grupo);
            fachada.salvarTopico(topico);
            
            topicos.add(topico);
            professorLogado.setTopicosCriados(topicos);
            fachada.atualizarProfessor(professorLogado);
            
            topico = new Topico();
            
        } else {
            mensagem = "Informações Inválidas!";
        }
    }
}
