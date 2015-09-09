package com.br.controladores;

import com.br.entidades.Grupo;
import com.br.entidades.Pergunta;
import com.br.entidades.Teste;
import com.br.entidades.Topico;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorExercicio")
@SessionScoped
public class ControladorExercicio implements Serializable {

    private Teste exercicio;
    private List<String> grupos;
    private String codigoGrupo;
    private Pergunta pergunta;

    @EJB
    Fachada fachada;

    public ControladorExercicio() {
        exercicio = new Teste();
        grupos = new ArrayList();
        pergunta = new Pergunta();
    }

    public Teste getExercicio() {
        return exercicio;
    }

    public void setExercicio(Teste exercicio) {
        this.exercicio = exercicio;
    }

    public List<String> getGrupos() {
        List<Grupo> gruposProfessor = fachada.meusGrupos(PegarUsuarioSessao.pegarProfessorSessao().getLogin());

        if (grupos.isEmpty()) {

            for (Grupo g : gruposProfessor) {
                grupos.add(String.valueOf(g.getCodigo()) + " - " + g.getNome());
            }
        }

        return grupos;
    }

    public void setGrupos(List<String> grupos) {
        this.grupos = grupos;
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public String salvarTeste() {
        exercicio.setProfessor(PegarUsuarioSessao.pegarProfessorSessao());
        fachada.salvarExercicio(exercicio);

        exercicio = new Teste();

        return "page-cad-teste?faces-redirect=true";
    }

    public String buscarPerguntas() {
        exercicio.setQuestoesExercicios(fachada.listarPerguntasCategoria(exercicio.getCategoria(), exercicio.getQtdPerguntas()));
        return "page-cad-teste?faces-redirect=true";
    }

    public List<Teste> testesCadastrados() {
        return fachada.listarTestes(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }

    public String removerTeste() {
        fachada.removerExercicio(exercicio);
        return "page-listar-testes?faces-redirect=true";
    }

    public String visualizarTeste(Teste teste) {
        this.exercicio = teste;

        return "page-alterar-teste?faces-redirect=true";
    }

    public String atualizarTeste() {
        fachada.atualizarExercicio(exercicio);
        return "page-alterar-teste?faces-redirect=true";
    }

    public String visualizarPerguntas(){
        return "page-perguntas-teste?faces-redirect=true";
    }

    public String paginaEnviar() {
        return "pagina-enviar-teste?faces-redirect=true";
    }

    public String enviarTeste() {
        String codigo [] = codigoGrupo.split(" ");
        
        Grupo grupo = fachada.buscarGrupoPorCodigo(Integer.parseInt(codigo[0]));
        Topico topico = new Topico();
        
        topico.setTipo("Atividade");
        topico.setGrupo(grupo);
        topico.setDisponivel(true);
        topico.setLoginUsuario(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
        
        fachada.salvarTopico(topico);
        
        return "page-listar-testes?faces-redirect=true";
    }

}
