package com.br.util;

/**
 *
 * @author Fatinha de Sousa
 */
public class Solicitacao {
    
    private int codigoGrupo;
    private String alunoNome;
    private String alunoLogin;
    private String nomeGrupo;
    private String descricaoGrupo;
    private String dataCriacao;
    private boolean aceito;
    
    public Solicitacao(){
        
    }

    public int getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(int codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getAlunoLogin() {
        return alunoLogin;
    }

    public void setAlunoLogin(String alunoLogin) {
        this.alunoLogin = alunoLogin;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getDescricaoGrupo() {
        return descricaoGrupo;
    }

    public void setDescricaoGrupo(String descricaoGrupo) {
        this.descricaoGrupo = descricaoGrupo;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

}
