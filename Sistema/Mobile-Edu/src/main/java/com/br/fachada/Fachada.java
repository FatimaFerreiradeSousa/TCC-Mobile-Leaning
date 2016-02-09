package com.br.fachada;

import com.br.daos.DaoHorario;
import com.br.entidades.*;
import com.br.interfaces.*;
import java.io.Serializable;
import java.util.Date;
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
    private InterfaceDaoComentario daoComentario;
    @EJB
    private InterfaceDaoRespondeExercicio daoRespondeExercicio;
    @EJB
    private InterfaceNotificacao daoNotificacao;
    @EJB
    private InterfaceDaoTurma daoTurma;
    @EJB
    private InterfaceDaoPresenca daoPresenca;
    @EJB
    private InterfaceDaoHorario daoHorario;
    @EJB
    private InterfaceDaoNota daoNota;
    
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
    
    public List<Topico> arquivosGrupo(int codigoGrupo){
        return daoTopico.listarArquivos(codigoGrupo);
    }
    
    public List<Topico> postagensGrupo(int codigo){
        return daoTopico.listarTopicos(codigo);
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
    
    public boolean removerMembroGrupo(int codigoGrupo){
        return daoPaGrupo.removerMembros(codigoGrupo);
    }
    
    public ParticipaGrupo buscarParticipaGrupo(String loginAluno, int codGrupo){
        return daoPaGrupo.buscarParticipaGrupo(loginAluno, codGrupo);
    }
    
    public List<ParticipaGrupo> buscarMembros(int codGrupo){
        return daoPaGrupo.listarRancking(codGrupo);
    }
    
    public List<ParticipaGrupo> listarSolicitacoesRecebidas(String loginProfessor){
        return daoPaGrupo.solicitacoesRecebidas(loginProfessor);
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
    
    public List<Topico> listarTopicosUsuario(String login, int codigoGrupo){
        return daoTopico.listarTopicosUsuario(login, codigoGrupo);
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
    
    public Comentario buscarComentario(int codigo){
        return daoComentario.consultar(codigo);
    }
    
    /*CRUD Responde Teste*/
    public boolean salvarRespondeTeste(RespondeExercicio respondeExercicio){
        return daoRespondeExercicio.salvar(respondeExercicio);
    }    
    
    public boolean testeRespondido(int codTeste, String loginAluno){
        return daoRespondeExercicio.respondeTeste(codTeste, loginAluno);
    }
    
    public List<RespondeExercicio> resultadosTeste(int codTeste){
        return daoRespondeExercicio.resultados(codTeste);
    }
    
    public List<RespondeExercicio> listarRespondeExercicio(String login){
        return daoRespondeExercicio.listarExcerciciosAluno(login);
    }
    
    /*Notificações do sistema*/
    public boolean salvarNotificacao(Notificacao notificacao){
        return daoNotificacao.salvarNotificacao(notificacao);
    }
    
    public List<Notificacao> listarNotificacoes(String login){
        return daoNotificacao.listarNotificacoes(login);
    }
    
    public boolean marcarComoLido(Notificacao notificacao){
        return daoNotificacao.atualizarNotificacao(notificacao);
    }
    
    public int listarQTDNotificacoes(String login){
        return daoNotificacao.listarQTDNotificacoes(login);
    }
    
    /*CRUD TURMA*/
    public boolean salvarTurma(Turma turma){
        return daoTurma.salvarTurma(turma);
    }
    
    public boolean alterarTurma(Turma turma){
        return daoTurma.atualizarTurma(turma);
    }
    
    public boolean removerTurma(Turma turma){
        return daoTurma.removerTurma(turma);
    }
    
    public List<Turma> listarTurmas(String login){
        return daoTurma.listarTurmas(login);
    }
    
    /*CRUD PRESENCA*/
    public boolean salvarPresenca(Presenca presenca){
        return daoPresenca.salvarPresenca(presenca);
    }
    
    public List<Presenca> listarPresencaData(Date data){
        return daoPresenca.listarPresencasPorData(data);
    }
    
    public List<Presenca> listarPresencaTurma(String turma){
        return daoPresenca.listarPresencasTurma(turma);
    }
    
    public int qtdFaltas(String login, String turma){
        return daoPresenca.qtdFaltas(login, turma);
    }
    
    public List<Presenca> listarPresencaPorHorario(Date data, String horaTurma){
        return daoPresenca.listarPresencaPorHorario(data, horaTurma);
    }
    
    /*CRUD HORARIO*/
    public boolean salvarHorario(Horario horario){
        return daoHorario.salvarHorario(horario);
    }
    
    public List<Horario> buscarHorario(String dia, String turma){
        return daoHorario.consultarHorario(dia, turma);
    }
    
    public boolean alterarHorario(Horario horario){
        return daoHorario.alterarHorario(horario);
    }
    
    public boolean removerHorario(Horario horario){
        return daoHorario.removerHorario(horario);
    }
    
    /*CRUD NOTA*/
    public boolean salvarNota(Nota nota){
        return daoNota.salvarNota(nota);
    }
    
    public List<Nota> listarNotas(String turma){
        return daoNota.listarNotas(turma);
    }
    
    public boolean removerNota(Nota nota){
        return daoNota.removerNota(nota);
    }
    
    public boolean alterarNota(Nota nota){
        return daoNota.alterarNota(nota);
    }
}
