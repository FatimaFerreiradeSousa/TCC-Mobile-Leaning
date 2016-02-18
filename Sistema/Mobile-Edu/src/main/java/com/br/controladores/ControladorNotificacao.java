package com.br.controladores;

import com.br.entidades.Notificacao;
import com.br.fachada.Service;
import com.br.sessao.PegarUsuarioSessao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorNotificacao")
@SessionScoped
public class ControladorNotificacao implements Serializable {

}
