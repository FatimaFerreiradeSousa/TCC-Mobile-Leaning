package com.br.util;

import com.br.entidades.Aluno;
import com.br.entidades.Grupo;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Fatinha
 */

public class ParticipaGrupoAux implements Serializable{
    
    private int id;
    private boolean aceito;
    private String dataParticipacao;
    private String alunoNome;
    private String alunoSobrenome;
    private String fotoAluno;
    private String alunoCurso;
    private String alunoDescricao;
    private float pontuacao;
    private int testesRespondidos;
    private int publicacoesFeitas;
    
    public ParticipaGrupoAux(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public float getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(float pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getDataParticipacao() {
        return dataParticipacao;
    }

    public void setDataParticipacao(String dataParticipacao) {
        this.dataParticipacao = dataParticipacao;
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

    public String getAlunoCurso() {
        return alunoCurso;
    }

    public void setAlunoCurso(String alunoCurso) {
        this.alunoCurso = alunoCurso;
    }

    public String getAlunoDescricao() {
        return alunoDescricao;
    }

    public void setAlunoDescricao(String alunoDescricao) {
        this.alunoDescricao = alunoDescricao;
    }

    public int getTestesRespondidos() {
        return testesRespondidos;
    }

    public void setTestesRespondidos(int testesRespondidos) {
        this.testesRespondidos = testesRespondidos;
    }

    public int getPublicacoesFeitas() {
        return publicacoesFeitas;
    }

    public void setPublicacoesFeitas(int publicacoesFeitas) {
        this.publicacoesFeitas = publicacoesFeitas;
    }
}