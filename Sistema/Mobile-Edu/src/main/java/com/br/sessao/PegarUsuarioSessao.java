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
    private static String login;
    
    public static Professor getProfessor() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) context.getSession(false);
        
        Professor professor = (Professor) session.getAttribute("professor");
        //login = professor.getLogin();
        
        return professor;
        
    }

    public static Aluno getAluno() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) context.getSession(false);
        Aluno aluno = (Aluno) session.getAttribute("aluno");
        login = aluno.getLogin();

        return aluno;
    }

    public static String usuarioLogado() {
        return login;
    }
}
