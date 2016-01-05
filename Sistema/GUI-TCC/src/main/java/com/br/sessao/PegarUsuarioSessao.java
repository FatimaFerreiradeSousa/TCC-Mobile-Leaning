package com.br.sessao;

import com.br.entidades.Aluno;
import com.br.entidades.Professor;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fatinha
 */
public class PegarUsuarioSessao {

    private static ExternalContext context;
    private static HttpSession session;

    public static Professor pegarProfessorSessao() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) context.getSession(false);
        return (Professor) session.getAttribute("professor");
    }

    public static Aluno pegarAlunoSessao() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) context.getSession(false);
        return (Aluno) session.getAttribute("aluno");
    }
}
