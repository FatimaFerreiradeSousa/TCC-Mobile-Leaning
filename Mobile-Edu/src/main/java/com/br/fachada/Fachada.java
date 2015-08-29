package com.br.fachada;

import com.br.entidades.*;
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
    private InterfaceDaoAluno daoAluno;
    @EJB
    private InterfaceDaoGrupo daoGrupo;
    @EJB
    private InterfaceDaoPGrupo daoPaGrupo;
    @EJB
    private InterfaceDaoTopico daoTopico;
    @EJB
    private InterfaceDaoAnotacao daoAnotacao;
    @EJB
    private InterfaceDaoComentario daoComentario;
    @EJB
    private InterfaceDaoRespondeExercicio daoRespondeExercicio;
    
    public Fachada() {
    }

    /*Crud Professor*/
    public boolean salvarProfessor(Professor professor) {
        daoProfessor.salvar(professor);
        return true;
    }

    public Professor buscarProfessor(String login) {
        return daoProfessor.buscarProfessor(login);
    }

    public Professor loginProfessor(String login, String senha) {
        return daoProfessor.loginProfessor(login, senha);
    }

    public boolean atualizarProfessor(Professor professor) {
        return daoProfessor.atualizar(professor);
    }
    
    public Professor buscarProfessorEmail(String email){
        return daoProfessor.buscarProfessorEmail(email);
    }

    /*Crud Questão*/
    public void salvarQuestao(Pergunta questao) {
        daoQuestao.salvar(questao);
    }

    public void atualizarQuestao(Pergunta questao) {
        daoQuestao.atualizar(questao);
    }

    public Pergunta consultarQuestao(int codigo) {
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

    public Teste buscarExercicio(int codigo) {
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

    /*CRUD Aluno*/
    public boolean salvarAluno(Aluno aluno) {
        return daoAluno.salvarAluno(aluno);
    }

    public boolean atualizarAluno(Aluno aluno) {
        return daoAluno.atualizarAluno(aluno);
    }

    public Aluno buscarAluno(String login) {
        return daoAluno.buscarAluno(login);
    }

    public boolean removerAluno(Aluno aluno) {
        return daoAluno.removerAluno(aluno);
    }

    public Aluno loginAluno(String login, String senha) {
        return daoAluno.loginAluno(login, senha);
    }
    
    public Aluno buscarAlunoEmail(String email){
        return daoAluno.buscarAlunoEmail(email);
    }
    
    /*CRUD Grupo*/
    public boolean salvarGrupo(Grupo grupo){
        return daoGrupo.salvar(grupo);
    }
    
    public List<Grupo> meusGrupos(String login){
        return daoGrupo.gruposProfessor(login);
    }
    
    public boolean removerGrupo(Grupo grupo){
        return daoGrupo.remover(grupo);
    }
    
    public Grupo buscarGrupoPorCodigo(int codigo){
        return daoGrupo.consultar(codigo);
    }
    
    public boolean atualizarGrupo(Grupo grupo){
        return daoGrupo.atualizar(grupo);
    }
    
    public List<Topico> topicosGrupo(int codigoGrupo){
        return daoGrupo.topicosGrupo(codigoGrupo);
    }
    
    public List<Topico> listarTestesGrupo(int codigoGrupo){
        return daoTopico.listarTestesGrupo(codigoGrupo);
    }
    
    /*CRUD participar Grupo*/
    public boolean adicionarMembro(ParticipaGrupo participaGrupo){
        return daoPaGrupo.addMembro(participaGrupo);
    }
    
    public boolean atualizarSolicitacao(ParticipaGrupo participaGrupo){
        return daoPaGrupo.atualizarSolicitacao(participaGrupo);
    }
    
    public boolean removerMembro(String login, int codigoGrupo){
        return daoPaGrupo.removerMembro(login, codigoGrupo);
    }
    
    public List<Aluno> listarMembrosGrupo(int codGrupo){
        return daoPaGrupo.listarMembros(codGrupo);
    }
    
    public boolean verificaMembro(String login, int codGrupo){
        return daoPaGrupo.verificaSeJaEhMembro(login, codGrupo);
    }
    
    public List<Grupo> buscarGruposPorNome(String nome){
        return daoGrupo.pesquisarGrupoPorNome(nome);
    }
    
    public List<ParticipaGrupo> listarGruposAlunos(String login){
        return daoPaGrupo.listarGruposAluno(login);
    }
    
    public List<ParticipaGrupo> listarGruposPendentes(String login){
        return daoPaGrupo.listarGruposPendentes(login);
    }
    
    public boolean verificaSolicitacaoPendente(String login, int codigoGrupo){
        return daoPaGrupo.verificaSolicitacao(login, codigoGrupo);
    }
    
    public List<ParticipaGrupo> listarNotificacoesProfessor(String loginProfessor){
        return daoPaGrupo.solicitacoesRecebidas(loginProfessor);
    }
    
    public boolean removerMembroGrupo(int codigoGrupo){
        return daoPaGrupo.removerMembros(codigoGrupo);
    }
    
    public ParticipaGrupo buscarParticipaGrupo(String loginAluno, int codGrupo){
        return daoPaGrupo.buscarParticipaGrupo(loginAluno, codGrupo);
    }
    
    public List<ParticipaGrupo> buscarMembros(int codGrupo){
        return daoPaGrupo.listarRancking(codGrupo);
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
    
    public List<Comentario> listarComentariosTopico(int codigoTopico){
        return daoTopico.comentariosTopico(codigoTopico);
    }
    
    public Topico buscarTopico(int codigo){
        return daoTopico.consultarTopico(codigo);
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
    
    /*CRUD Comentarios*/
    public boolean salvarComentario(Comentario comentario){
        return daoComentario.salvar(comentario);
    }
    
    public boolean alterarComentario(Comentario comentario){
        return daoComentario.atualizar(comentario);
    }
    
    public boolean removerComentario(Comentario comentario){
        return daoComentario.remover(comentario);
    }
    
    public boolean atualizarComentario(Comentario comentario){
        return daoComentario.atualizar(comentario);
    }
    
    /*CRUD Responde Teste*/
    public boolean salvarRespondeTeste(RespondeExercicio respondeExercicio){
        return daoRespondeExercicio.salvar(respondeExercicio);
    }    
    
    public boolean testeRespondido(int codTeste, String loginAluno){
        return daoRespondeExercicio.respondeTeste(codTeste, loginAluno);
    }
}
