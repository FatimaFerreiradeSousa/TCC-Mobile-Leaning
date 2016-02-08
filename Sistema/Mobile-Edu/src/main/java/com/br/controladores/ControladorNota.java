package com.br.controladores;

import com.br.daos.DaoNota;
import com.br.entidades.Nota;
import com.br.entidades.Turma;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorNota")
@SessionScoped
public class ControladorNota implements Serializable {

    @EJB
    private Fachada fachada;
    private Nota nota;
    private Turma turma;

    public ControladorNota() {
        nota = new Nota();
        turma = new Turma();
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public String pageNota(Turma turma) {
        this.turma = turma;

        return "page-add-notas?faces-redirect=true";
    }

    public String salvarNota() {
        nota.setProfessor(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
        nota.setTurma(turma);
        nota.setDataNota(new Date());

        if (fachada.salvarNota(nota)) {
            return "page-add-notas?faces-redirect=true";
        }

        return "page-add-notas?faces-redirect=true";
    }

    public List<Nota> listarNotas() {
        return fachada.listarNotas(turma.getCodigo());
    }

    public String paginaVerNota(Nota nota) {
        this.nota = nota;

        return "page-ver-nota?faces-redirect=true";
    }

    public String paginaVoltar() {
        this.nota = new Nota();
        return "page-add-notas?faces-redirect=true";
    }

    public String removerNota() {
        if (fachada.removerNota(nota)) {
            return "page-add-notas?faces-redirect=true";
        }

        return "page-add-notas?faces-redirect=true";
    }

    public String paginaAlterarNota(Nota nota){
        
        this.nota = nota;
        
        return "page-alterar-nota?faces-redirect=true";
    }
    
    public String alterarNota() {
        if (fachada.alterarNota(nota)) {
            return "page-add-notas?faces-redirect=true";
        }

        return "page-add-notas?faces-redirect=true";
    }
    
    public String cancelarEdicao(){
        this.nota = new Nota();
        
        return "page-add-notas?faces-redirect=true";
    }
}
