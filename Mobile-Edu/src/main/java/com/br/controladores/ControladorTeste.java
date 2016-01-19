package com.br.controladores;

import com.br.datas.FormatData;
import com.br.entidades.*;
import com.br.fachada.Fachada;
import com.br.sessao.PegarUsuarioSessao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Fatinha
 */
@Named(value = "controladorTeste")
@SessionScoped
public class ControladorTeste implements Serializable {

    @EJB
    private Fachada fachada;
    private Teste teste;
    private int tamanho;
    private List<Pergunta> perguntas;
    private Pergunta pergunta;
    private boolean comecar = false;
    private int contador;
    private float resultado;
    private boolean salvarTeste;
    private RespondeExercicio respondeExercicio;
    private boolean disponivel;
    private String mensagem;

    public ControladorTeste() {
        teste = new Teste();
        tamanho = 0;
        perguntas = new ArrayList();
        pergunta = new Pergunta();
        contador = 1;
        resultado = 0;
        salvarTeste = false;
        disponivel = true;
        respondeExercicio = new RespondeExercicio();
        mensagem = null;
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public boolean isComecar() {
        return comecar;
    }

    public void setComecar(boolean comecar) {
        this.comecar = comecar;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    public boolean isSalvarTeste() {
        return salvarTeste;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setSalvarTeste(boolean salvarTeste) {
        this.salvarTeste = salvarTeste;
    }

    public RespondeExercicio getRespondeExercicio() {
        return respondeExercicio;
    }

    public void setRespondeExercicio(RespondeExercicio respondeExercicio) {
        this.respondeExercicio = respondeExercicio;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String paginaVisualizarTeste(Topico topico) {
        teste = fachada.buscarExercicio(topico.getCodigoTeste());
        disponivel = topico.isDisponivel();
        perguntas = teste.getQuestoesExercicios();

        boolean opcao = fachada.testeRespondido(teste.getCodigo(), PegarUsuarioSessao.pegarAlunoSessao().getLogin());
        boolean date = FormatData.verificarData(teste.getDataEntrega());
        
        if(opcao == false || date == true){
            disponivel = false;
        }
        
        return "page-responder-teste?faces-redirect=true";
    }

    public String comecarAResponder() {
        //disponivel = false;
        comecar = true;
        tamanho = 0;
        pergunta = teste.getQuestoesExercicios().get(tamanho);
        tamanho++;
        respondeExercicio = new RespondeExercicio();
        return "page-responder-teste?faces-redirect=true";
    }

    public String enviarRespostas(Resposta resposta, float pontuacao) {

        if (resposta.getRespostaCerta() == true) {
            resultado += pontuacao;
        }

        int aux = teste.getQtdPerguntas();

        if (tamanho < aux) {
            pergunta = teste.getQuestoesExercicios().get(tamanho);
        }

        if (tamanho == aux) {
            tamanho = 0;
            return "page-visualizar-resultado?faces-redirect=true";
        }

        tamanho++;
        contador++;

        return "page-responder-teste?faces-redirect=true";
    }

    public String salvarTeste() {
        respondeExercicio.setCodTeste(teste.getCodigo());
        respondeExercicio.setDataResposta(new Date());
        respondeExercicio.setNota(resultado);
        respondeExercicio.setRespondido(true);
        Aluno aluno = PegarUsuarioSessao.pegarAlunoSessao();
        respondeExercicio.setAluno(aluno);

        fachada.salvarRespondeTeste(respondeExercicio);
        atualizarPontuacao();

        contador = 0;
        tamanho = 0;
        salvarTeste = true;
        comecar = false;
        return "page-visualizar-resultado?faces-redirect=true";
    }

    public void atualizarPontuacao() {
        String codGrupo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codGrupo");
        ParticipaGrupo pg = fachada.buscarParticipaGrupo(PegarUsuarioSessao.pegarAlunoSessao().getLogin(), Integer.parseInt(codGrupo));

        if (pg != null) {
            pg.setPontuacao(pg.getPontuacao() + resultado);
            fachada.atualizarSolicitacao(pg);
        }
    }
    
    public void verificaTeste(){
        
        boolean opcao = fachada.testeRespondido(teste.getCodigo(), PegarUsuarioSessao.pegarAlunoSessao().getLogin());
        
        if(opcao == false){
            mensagem = "Você já respondeu esse teste";
        }else{
            mensagem = "Este teste estava disponivel até o dia " +FormatData.parseDateString(teste.getDataEntrega());
        }
    }
    
    /*Resultado de cada teste*/
    public String resultadoIndividual(Topico topico){
        this.teste = fachada.buscarExercicio(topico.getCodigoTeste());
        return "page-resultado-individual?faces-redirect=true";
    }
    
    public List<RespondeExercicio> resultadoTestes(){
        return fachada.resultadosTeste(teste.getCodigo());
    }
    
    public String pageTestesResolvidos(){
        return "page-testes-resolvidos?faces-redirect=true";
    }
    
    public String pageRackingTestes(){
        return "page-racking-grupo?faces-redirect=true";
    }
    
    public String pageResultado(Topico topico){
        this.teste = fachada.buscarExercicio(topico.getCodigoTeste());
        return "page-resultado?faces-redirect=true";
    }
}
