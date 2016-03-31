package com.br.controladores;

import com.br.entidades.Aluno;
import com.br.entidades.Professor;
import com.br.fachada.Service;
import com.br.senha.RecuperarEmail;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorSenha")
@SessionScoped
public class ControladorSenha implements Serializable {

    @EJB
    private Service fachada;
    private String login;
    private String mensagem;
    private String usuario;

    public ControladorSenha() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String enviarEmail() throws EmailException {

        if (usuario.equalsIgnoreCase("Aluno")) {

            Aluno aluno = fachada.buscarAluno(login);

            if (aluno != null) {

                RecuperarEmail recuperarEmail = new RecuperarEmail();
                recuperarEmail.enviarEmail(aluno.getEmail(), aluno.getSenha());
                this.mensagem = "Um email contendo a sua senha foi enviado para " + aluno.getEmail();

            } else if (aluno == null) {
                this.mensagem = "Nenhum Login Encontrado";
            }

        } else if (usuario.equalsIgnoreCase("Professor")) {

            Professor professor = fachada.buscarProfessor(login);

            if (professor != null) {

                RecuperarEmail recuperarEmail = new RecuperarEmail();
                recuperarEmail.enviarEmail(professor.getEmail(), professor.getSenha());
                this.mensagem = "Um email contendo a sua senha foi enviado para " + professor.getEmail();

            } else if (professor == null) {
                this.mensagem = "Nenhum Login Cadastrado";
            }
        }

        this.login = null;
        this.usuario = "Tipo";

        return "recuperar-senha?faces-redirect=true";
    }

}
