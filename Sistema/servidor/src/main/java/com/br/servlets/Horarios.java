package com.br.servlets;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.entidades.Professor;
import com.br.entidades.Turma;
import com.br.util.Servicos;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Fatinha de Sousa
 */
@WebServlet(name = "Horarios", urlPatterns = {"/Horarios"})
public class Horarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Dao daoAluno = new Dao();
        Aluno al = daoAluno.buscarAluno("aliu");
        
        List<Turma> turmas = new ArrayList();
        List<Turma> tAluno = Servicos.horarioAluno(al);
        
        for (Turma turma : tAluno) {
            Turma t = new Turma();
            t.setCategoria(turma.getCategoria());
            t.setCodigo(turma.getCodigo());
            t.setDataInicio(turma.getDataInicio());
            t.setDataTerminio(turma.getDataTerminio());
            t.setDescricao(turma.getDescricao());
            t.setNome(turma.getNome());
            
            Professor p = new Professor();
            p.setNome(turma.getProfessor().getNome());
            
            t.setProfessor(p);
            turmas.add(t);
        }
        
        JSONArray jSONArray = new JSONArray(turmas);
        
        OutputStream os = response.getOutputStream();
        os.write(jSONArray.toString().getBytes());

        os.flush();
        os.close();
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
