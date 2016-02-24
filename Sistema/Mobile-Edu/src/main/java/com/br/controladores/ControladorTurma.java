package com.br.controladores;

import com.br.datas.FormatData;
import com.br.entidades.Aluno;
import com.br.entidades.Horario;
import com.br.entidades.Presenca;
import com.br.entidades.Turma;
import com.br.fachada.Service;
import com.br.sessao.PegarUsuarioSessao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Fatinha de Sousa
 */
@Named(value = "controladorTurma")
@SessionScoped
public class ControladorTurma implements Serializable {

    @EJB
    private Service fachada;
    private Turma turma;
    private Aluno aluno;
    private String mensagem;
    private Presenca presenca;
    private List<String> horarios;
    private String hora;
    private boolean status;
    private boolean code;
    private boolean checkChamada;

    public ControladorTurma() {
        this.turma = new Turma();
        this.aluno = new Aluno();
        this.presenca = new Presenca();
        this.horarios = new ArrayList();
        this.status = false;
        this.checkChamada = false;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Presenca getPresenca() {
        return presenca;
    }

    public void setPresenca(Presenca presenca) {
        this.presenca = presenca;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public boolean isCheckChamada() {
        return checkChamada;
    }

    public void setCheckChamada(boolean checkChamada) {
        this.checkChamada = checkChamada;
    }

    public String salvarTurma() {

        turma.setProfessor(PegarUsuarioSessao.pegarProfessorSessao());

        if (fachada.salvarTurma(turma)) {
            turma = new Turma();
            return "page-turmas?faces-redirect=true";
        }

        return "page-add-turma?faces-redirect=true";
    }

    public String paginaInicialTurma(Turma turma) {
        this.turma = turma;

        return "page-inicial-turma?faces-redirect=true";
    }

    public String paginaTurma(Turma turma) {
        this.turma = turma;
        return "page-inicial-turma?faces-redirect=true;";
    }

    public String atualizarTurma() {

        if (fachada.alterarTurma(turma)) {
            return "page-alterar-turma?faces-redirect=true";
        }

        return "page-alterar-turma?faces-redirect=true";
    }

    public String removerTurma() {
        if (fachada.removerTurma(turma)) {
            return "page-listar-turmas?faces-redirect=true";
        }

        return "page-alterar-turma?faces-redirect=true";
    }

    public List<Turma> listarTurmas() {
        return fachada.listarTurmas(PegarUsuarioSessao.pegarProfessorSessao().getLogin());
    }

    public String buscarAluno() {
        this.aluno = fachada.buscarAluno(aluno.getLogin());

        if (this.aluno != null) {
            return "page-buscar-aluno?faces-redirect=true";
        } else {
            aluno = new Aluno();
            mensagem = "Nenhum usuário encontrado";
            return "page-buscar-aluno?faces-redirect=true";
        }
    }

    public String adicionarMembro() {
        turma.getAlunos().add(aluno);
        fachada.alterarTurma(turma);
        aluno = new Aluno();
        return "page-listar-alunos?faces-redirect=true";
    }

    public boolean verificarMembro() {
        return turma.getAlunos().indexOf(aluno) == -1;
    }

    public String removerMembro() {

        turma.getAlunos().remove(aluno);

        if (fachada.alterarTurma(turma)) {
            return "page-buscar-aluno?faces-redirect=true";
        }

        return "page-buscar-aluno?faces-redirect=true";

    }

    /*Presença*/
    public String presencaPagina() {
        return "page-add-presenca?faces-redirect=true";
    }

    public String salvarPresenca(Aluno aluno) {
        presenca.setAluno(aluno);
        presenca.setTurma(turma);
        presenca.setStatus(true);
        presenca.setHoraAula(hora);

        presenca.setDescricao("Presente");
        if (fachada.salvarPresenca(presenca)) {
            presenca = new Presenca();

            return "page-add-presenca?faces-redirect=true";
        }

        return "page-add-presenca?faces-redirect=true";
    }

    public String salvarFalta(Aluno aluno) {
        presenca.setAluno(aluno);
        presenca.setTurma(turma);
        presenca.setStatus(false);
        presenca.setDescricao("Faltou");
        presenca.setHoraAula(hora);

        if (fachada.salvarPresenca(presenca)) {
            presenca = new Presenca();
            return "page-add-presenca?faces-redirect=true";
        }

        return "page-add-presenca?faces-redirect=true";
    }

    public List<Presenca> listarPresencaData() {
        return fachada.listarPresencaData(new Date());
    }

    public String paginaPresenca() {
        this.status = false;
        this.code = true;
        return "page-add-presenca?faces-redirect=true";
    }

    public int qtdFalta(Presenca presenca) {

        return fachada.qtdFaltas(presenca.getAluno().getLogin(), turma.getCodigo());
    }

    public List<Presenca> listarPresencaTurma() {
        return fachada.listarPresencaTurma(turma.getCodigo());
    }

    public List<String> listarHorarios() {
        String diaSemana = FormatData.verificarDia(FormatData.pegarDia());
        List<Horario> horariosDia = fachada.buscarHorario(diaSemana, turma.getCodigo());

        if (!horariosDia.isEmpty() && horarios.isEmpty()) {
            for (Horario horario : horariosDia) {
                horarios.add(horario.getHorarioInicial() + "-" + horario.getHorarioFinal());
            }
        }

        return horarios;
    }

    public String selecionarHorario() {

        this.status = fachada.listarPresencaPorHorario(new Date(), hora).size() <= 0;
        this.checkChamada = fachada.listarPresencaPorHorario(new Date(), hora).size() > 0;

        return "page-add-presenca?faces-redirect=true";
    }

    public List<Horario> buscarHorarioDoDia() {
        String dia = FormatData.verificarDia(FormatData.pegarDia());
        return fachada.buscarHorario(dia, turma.getCodigo());
    }

    public List<Horario> buscarHorarioDia(Turma turma) {
        String dia = FormatData.verificarDia(FormatData.pegarDia());
        return fachada.buscarHorario(dia, turma.getCodigo());
    }

    public String diaDaSemana() {
        return FormatData.verificarDia(FormatData.pegarDia());
    }

    public List<Turma> listarTurmasDia() {
        String dia = FormatData.verificarDia(FormatData.pegarDia());
        return fachada.listarTurmasHorario(PegarUsuarioSessao.pegarProfessorSessao().getLogin(), dia);
    }

    /*ALUNO*/
    public String paginaDesempenhoAluno(Aluno aluno) {
        this.aluno = aluno;
        return "page-aluno-detalhes?faces-redirect=true";
    }

    public int qtdFaltasAluno() {

        return fachada.qtdFaltas(aluno.getLogin(), turma.getCodigo());
    }

    public int qtdPresencasAluno() {

        return fachada.qtdPresencas(aluno.getLogin(), turma.getCodigo());
    }

    public List<Turma> horarioAluno() {
        String dia = FormatData.verificarDia(FormatData.pegarDia());

        List<Turma> turmas = new ArrayList<>();
       
        for (Turma turma1 : PegarUsuarioSessao.pegarAlunoSessao().getTurmas()) {

            for (Horario h : turma1.getHorarios()) {
                if (h.getDia().equalsIgnoreCase(dia)) {
                    turmas.add(turma1);
                    break;
                }
            }
        }

        return turmas;
    }

    public String paginaListarAlunos() {
        return "page-listar-alunos?faces-redirect=true";
    }

    public String paginaConfiguracoesTurma() {
        return "page-alterar-turma?faces-redirect=true";
    }

    public String paginaInicial() {
        return "page-inicial-turma?faces-redirect=true";
    }
}
