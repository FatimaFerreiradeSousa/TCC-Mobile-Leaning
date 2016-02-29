package com.br.servlets;

import com.br.dao.DaoAluno;
import com.br.entidades.Aluno;
import com.br.entidades.Turma;
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
@WebServlet(name = "Turmas", urlPatterns = {"/Turmas"})
public class Turmas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DaoAluno daoAluno = new DaoAluno();
        Aluno al = daoAluno.buscarAluno("aliu");

        List<Turma> list = new ArrayList<>();

        for (Turma turma : al.getTurmas()) {
            
            Turma t = new Turma();
            t.setNome(turma.getNome());
            t.setCategoria(turma.getCategoria());
            t.setCodigo(turma.getCodigo());
            t.setDataInicio(turma.getDataInicio());
            t.setDataTerminio(turma.getDataTerminio());
            t.setDescricao(turma.getDescricao());
            //t.setNotas(turma.getNotas());
            
            list.add(t);
        }

        JSONArray jSONArray = new JSONArray(list);
        
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
