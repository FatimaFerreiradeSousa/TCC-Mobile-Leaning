package com.br.util;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fatinha de Sousa
 */

public class RespondeExercicioAux implements Serializable{

    private int id;
    private float nota;
    private String dataResposta;
    private int codTeste;
    private boolean respondido;
    private String alunoLogin;
    private String alunoNome;
    private String alunoSobrenome;
    
    public RespondeExercicioAux(){
    
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

    public String getDataResposta(){
        return dataResposta;
    }
    
    public void setDataResposta(String dataResposta){
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

    public String getAlunoLogin() {
        return alunoLogin;
    }

    public void setAlunoLogin(String alunoLogin) {
        this.alunoLogin = alunoLogin;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getAlunoSobrenome() {
        return alunoSobrenome;
    }

    public void setAlunoSobrenome(String alunoSobrenome) {
        this.alunoSobrenome = alunoSobrenome;
    }
}
