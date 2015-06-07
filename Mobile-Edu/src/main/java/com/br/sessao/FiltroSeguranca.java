package com.br.sessao;

import com.br.entidades.Professor;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha de Sousa
 */
public class FiltroSeguranca implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();  
        ExternalContext ext = context.getExternalContext();  
        HttpSession session = (HttpSession) ext.getSession(false);  
        Professor professorLogado = (Professor) session.getAttribute("usuario");

        if (professorLogado == null) {
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            nh.handleNavigation(context, null, "index.jsf");
        }

    }

    @Override
    public void beforePhase(PhaseEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhaseId getPhaseId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
