package com.br.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Fatinha de Sousa
 */

@Entity
public class Aluno extends Pessoa implements Serializable{
    
    private String curso;
    private String pontuacao;
    @OneToMany
    private List<Topico> topicosCriados;
    @ManyToMany
    private List<Comentario> comentariosTopico;
    @ManyToMany
    private List<RespondeExercicio> respondeExercicio;
    
    public Aluno(){
    
    }

    public Aluno(String curso, String pontuacao, String email, String login, String senha, 
            String nome, String instituicao, String foto, String descricao, Date dataParticipacao) {
        super(email, login, senha, nome, instituicao, foto, descricao, dataParticipacao);
        this.curso = curso;
        this.pontuacao = pontuacao;
        this.topicosCriados = new ArrayList();
        this.comentariosTopico = new ArrayList();
        this.respondeExercicio = new ArrayList();
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }

    public List<Topico> getTopicosCriados() {
        return topicosCriados;
    }

    public void setTopicosCriados(List<Topico> topicosCriados) {
        this.topicosCriados = topicosCriados;
    }

    public List<Comentario> getComentariosTopico() {
        return comentariosTopico;
    }

    public void setComentariosTopico(List<Comentario> comentariosTopico) {
        this.comentariosTopico = comentariosTopico;
    }

    public List<RespondeExercicio> getRespondeExercicio() {
        return respondeExercicio;
    }

    public void setRespondeExercicio(List<RespondeExercicio> respondeExercicio) {
        this.respondeExercicio = respondeExercicio;
    }
}
