package com.br.fachada;

import com.br.entidades.*;
import com.br.gerenciadores.InterfaceGerenciadorQuestao;
import com.br.interfaces.*;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Fatinha de Sousa
 */
@Stateful
public class Fachada implements Serializable {

    @EJB
    private InterfaceDaoProfessor daoProfessor;
    @EJB
    private InterfacePergunta daoQuestao;
    @EJB
    private InterfaceExercicio daoExercicio;
    @EJB
    private InterfaceDaoResposta daoResposta;
    @EJB
    private InterfaceGerenciadorQuestao daoAddQuestao;
    @EJB
    private InterfaceDaoAluno daoAluno;
    @EJB
    private InterfaceDaoGrupo daoGrupo;
    @EJB
    private InterfaceDaoPGrupo daoPaGrupo;
    @EJB
    private InterfaceDaoTopico daoTopico;
    @EJB
    private InterfaceDaoAnotacao daoAnotacao;
    
    public Fachada() {
    }

    /*Crud Professor*/
    public boolean salvarProfessor(Professor professor) {
        daoProfessor.salvar(professor);
        return true;
    }

    public Professor buscarProfessor(Professor professor) {
        return daoProfessor.buscarProfessor(professor.getLogin());
    }

    public Professor loginProfessor(String login, String senha) {
        return daoProfessor.loginProfessor(login, senha);
    }

    public boolean atualizarProfessor(Professor professor) {
        daoProfessor.atualizar(professor);
        return true;
    }

    /*Crud Questão*/
    public void salvarQuestao(Pergunta questao) {
        daoQuestao.salvar(questao);
    }

    public void atualizarQuestao(Pergunta questao) {
        daoQuestao.atualizar(questao);
    }

    public Pergunta consultarQuestao(String codigo) {
        return daoQuestao.consultar(codigo);
    }

    public boolean removerQuestao(Pergunta questao) {
        return daoQuestao.remover(questao);
    }

    public List<Pergunta> listarQuestoes(String login) {
        return daoQuestao.listarQuestoes(login);
    }
    
    public List<Pergunta> listarPerguntasCategoria(String categoria, int qtd){
        return daoQuestao.listarPerguntasPorCategoria(categoria, qtd);
    }

    /*Crud Teste*/
    public boolean salvarExercicio(Teste exe) {
        return daoExercicio.salvar(exe);
    }

    public Teste buscarExercicio(String codigo) {
        return daoExercicio.consultar(codigo);
    }

    public void removerExercicio(Teste exe) {
        daoExercicio.remover(exe);
    }

    public void atualizarExercicio(Teste exe) {
        daoExercicio.atualizar(exe);
    }

    public List<Teste> listarTestes(String login) {
        return daoExercicio.testesCadastradosProfessor(login);
    }

    /*Crud Resposta*/
    public boolean salvarResposta(Resposta resposta) {
        return daoResposta.salvarResposta(resposta);
    }

    public List<Resposta> listarRespostas(String codigoQuestao) {
        return daoResposta.listarResposta(codigoQuestao);
    }

    public boolean atualizarResposta(Resposta resposta) {
        return daoResposta.atualizarResposta(resposta);
    }

    public boolean removerResposta(List<Resposta> respostas) {
        return daoResposta.removerResposta(respostas);
    }

    /*Crud AddQuestao*/
    public boolean addQuestaoTeste(Pergunta questao) {
        return daoAddQuestao.addQuestaoTeste(questao);
    }

    public boolean removerQuestaoTeste(Pergunta questao) {
        return daoAddQuestao.deletarQuestao(questao);
    }

    public List<Pergunta> listarQuestaoTeste() {
        return daoAddQuestao.listarQuestoes();
    }

    public void concluirTeste() {
        daoAddQuestao.concluir();
    }

    /*CRUD Aluno*/
    public boolean salvarAluno(Aluno aluno) {
        return daoAluno.salvarAluno(aluno);
    }

    public boolean atualizarAluno(Aluno aluno) {
        return daoAluno.atualizarAluno(aluno);
    }

    public Aluno buscarAluno(Aluno aluno) {
        return daoAluno.buscarAluno(aluno.getLogin());
    }

    public boolean removerAluno(Aluno aluno) {
        return daoAluno.removerAluno(aluno);
    }

    public Aluno loginAluno(String login, String senha) {
        return daoAluno.loginAluno(login, senha);
    }
    
    /*CRUD Grupo*/
    public boolean salvarGrupo(Grupo grupo){
        return daoGrupo.salvar(grupo);
    }
    
    public List<Grupo> meusGrupos(String login){
        return daoGrupo.gruposProfessor(login);
    }
    
    public boolean participarGrupo(ParticipaGrupo participaGrupo){
        return daoPaGrupo.participaGrupo(participaGrupo);
    }
    
    public boolean atualizarSolicitacao(ParticipaGrupo participaGrupo){
        return daoPaGrupo.atualizarSolicitacao(participaGrupo);
    }
    
    /*CRUD Topicos*/
    public boolean salvarTopico(Topico topico){
        return daoTopico.salvar(topico);
    }
    
    public boolean removerTopico(Topico topico){
        return daoTopico.remover(topico);
    }
    
    public boolean atualizarTopico(Topico topico){
        return daoTopico.atualizar(topico);
    }
    
    /*CRUD Anotacao*/
    public boolean salvarAnotacao(Anotacao anotacao){
        return daoAnotacao.salvarAnotacao(anotacao);
    }
    
    public boolean atualizarAnotacao(Anotacao anotacao){
        return daoAnotacao.atualizarAnotacao(anotacao);
    }
    
    public boolean removerAnotacao(Anotacao anotacao){
        return daoAnotacao.removerAnotacao(anotacao);
    }
    
    public List<Anotacao> listarAnotacao(Professor professor){
        return daoAnotacao.listarAnotacao(professor);
    }
}
