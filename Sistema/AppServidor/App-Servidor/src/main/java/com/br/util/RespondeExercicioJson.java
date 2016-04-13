package com.br.util;

import java.io.Serializable;

/**
 *
 * @author Fatinha de Sousa
 */

public class RespondeExercicioJson implements Serializable{

    private int id;
    private float nota;
    private String dataResposta;
    private int codTeste;
    private boolean respondido;
    private String alunoLogin;
    private String alunoNome;
    private String alunoSobrenome;
    private String fotoAluno;
    private String nomeTeste;
    
    public RespondeExercicioJson(){
    
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

    public String getFotoAluno() {
        return fotoAluno;
    }

    public void setFotoAluno(String fotoAluno) {
        this.fotoAluno = fotoAluno;
    }

    public String getNomeTeste() {
        return nomeTeste;
    }

    public void setNomeTeste(String nomeTeste) {
        this.nomeTeste = nomeTeste;
    }
}
