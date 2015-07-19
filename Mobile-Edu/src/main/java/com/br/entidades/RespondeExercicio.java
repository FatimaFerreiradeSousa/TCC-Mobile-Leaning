package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class RespondeExercicio implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float nota;
    @Temporal(TemporalType.DATE)
    private Date dataResposta;
    private int codTeste;
    private boolean respondido;
    
    @ManyToMany(mappedBy = "respondeExercicio")
    private List<Aluno> alunos;
    
    public RespondeExercicio(){
    
    }

    public RespondeExercicio(float nota, Date dataResposta, int codTeste, boolean respondido) {
        this.nota = nota;
        this.dataResposta = dataResposta;
        this.codTeste = codTeste;
        this.respondido = respondido;
        this.alunos = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Date getDataResposta(){
        return dataResposta;
    }
    
    public void setDataResposta(Date dataResposta){
        this.dataResposta = dataResposta;
    }

    public int getCodTeste() {
        return codTeste;
    }

    public void setCodTeste(int codTeste) {
        this.codTeste = codTeste;
    }

    public boolean isRespondido() {
        return respondido;
    }

    public void setRespondido(boolean respondido) {
        this.respondido = respondido;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
