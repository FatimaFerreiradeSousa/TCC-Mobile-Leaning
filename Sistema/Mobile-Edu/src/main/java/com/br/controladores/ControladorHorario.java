package com.br.controladores;

import com.br.entidades.Horario;
import com.br.entidades.Turma;
import com.br.fachada.Fachada;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorHorario")
@SessionScoped
public class ControladorHorario implements Serializable {

    @EJB
    private Fachada fachada;
    private Turma turma;
    private Horario horario;
    
    public ControladorHorario() {
        turma = new Turma();
        horario = new Horario();
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    public String pageHorario(Turma turma){
        this.turma = turma;
        
        return "page-add-horario?faces-redirect=true";
    }
    
    public String salvarHorario(){
        horario.setTurma(turma);
        if(fachada.salvarHorario(horario)){
            horario = new Horario();
            return "page-add-horario?faces-redirect=true";
        }
        
        return "page-add-horario?faces-redirect=true";
    }
    
    public List<Horario> buscarHorario(String dia){
        return fachada.buscarHorario(dia, turma.getCodigo());
    }
    
}
