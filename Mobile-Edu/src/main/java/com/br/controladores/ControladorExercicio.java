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
import java.util.Map;
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
    private String grupoCodigo;

    @EJB
    Fachada fachada;

    public ControladorExercicio() {
        exercicio = new Teste();
        grupos = new ArrayList();
        pergunta = new Pergunta();
        codigoGrupo = new String();
        grupoCodigo = new String();
    }

    public Teste getExercicio() {
        return exercicio;
    }

    public void setExercicio(Teste exercicio) {
        this.exercicio = exercicio;
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

    public String visualizarPerguntas() {
        return "page-perguntas-teste?faces-redirect=true";
    }

    public String paginaEnviar() {
        return "page-enviar-teste?faces-redirect=true";
    }

    public List<String> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<String> grupos) {
        this.grupos = grupos;
    }

    public List<String> meusGrupos() {
        List<Grupo> gruposProfessor = fachada.meusGrupos("Fatinha");

        if (grupos.isEmpty()) {

            for (Grupo g : gruposProfessor) {
                grupos.add(String.valueOf(g.getCodigo()) + " - " + g.getNome());
            }
        }

        return grupos;
    }

    public String getGrupoCodigo() {
        return grupoCodigo;
    }

    public void setGrupoCodigo(String grupoCodigo) {
        this.grupoCodigo = grupoCodigo;
    }

    public void pegarCodigoGrupo() {
        System.out.println("Codigo do grupo com Ajax: " + codigoGrupo);
    }

    public void enviarTeste() {
        String codigo[] = codigoGrupo.split(" ");

        Grupo grupo = fachada.buscarGrupoPorCodigo(Integer.parseInt(codigo[0]));
        Topico topico = new Topico();

        topico.setCodigoTeste(exercicio.getCodigo());
        topico.setConteudo(exercicio.getAssunto());
        topico.setDataCriacao(exercicio.getDataEntrega());
        topico.setTipo("Atividade");
        topico.setGrupo(grupo);
        topico.setDisponivel(true);
        topico.setLoginUsuario(PegarUsuarioSessao.pegarProfessorSessao().getLogin());

        fachada.salvarTopico(topico);
    }

    public String cancelarEnvio() {
        return "page-alterar-teste?faces-redirect=true";
    }
    
    public String concluirEnvio(){
        return "page-listar-testes?faces-redirect=true";
    }
}
