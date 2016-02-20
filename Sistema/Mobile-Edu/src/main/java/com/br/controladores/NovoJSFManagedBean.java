
package com.br.controladores;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Fatinha de Sousa
 */

@Named(value = "novoJSFManagedBean")
@SessionScoped
public class NovoJSFManagedBean implements Serializable {

    private Date data;

    public NovoJSFManagedBean() {
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public String enviarData(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", 
                "Salvo Com Sucesso"));

        return null;
    }
}
