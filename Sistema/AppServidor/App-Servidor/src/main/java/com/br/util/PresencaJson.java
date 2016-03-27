package com.br.util;

import java.io.Serializable;

/**
 *
 * @author Fatinha de Sousa
 */

public class PresencaJson implements Serializable{
    
    private boolean status;
    private String horaAula;
    private String descricao;
    private String dataPreseca;
    
    public PresencaJson(){
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getHoraAula() {
        return horaAula;
    }

    public void setHoraAula(String horaAula) {
        this.horaAula = horaAula;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataPreseca() {
        return dataPreseca;
    }

    public void setDataPreseca(String dataPreseca) {
        this.dataPreseca = dataPreseca;
    }
}