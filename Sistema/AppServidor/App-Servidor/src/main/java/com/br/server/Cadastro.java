package com.br.server;

import com.br.util.UtilTest;
import com.br.dao.Dao;
import com.br.entidades.Aluno;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Fatinha de Sousa
 */
@WebServlet(name = "Cadastro", urlPatterns = {"/Cadastro"})
public class Cadastro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equals("POST")) {
            PrintWriter printWriter = response.getWriter();
            response.setContentType("text/html");
                
            String jsonString = UtilTest.streamToString(request.getInputStream());
            JSONObject jSONObject = UtilTest.getJSON(jsonString);

            Aluno aluno = new Aluno();
            aluno.setNome(jSONObject.getString("nome"));
            aluno.setSobrenome(jSONObject.getString("sobrenome"));
            aluno.setEmail(jSONObject.getString("email"));
            aluno.setLogin(jSONObject.getString("login"));
            aluno.setSenha(jSONObject.getString("senha"));
            String caminho
                    = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Imagens\\imagens_padrao\\perfil.png";
            aluno.setFoto(caminho);
            aluno.setDataParticipacao(new Date());

            Dao daoAluno = new Dao();

            if (daoAluno.buscarAluno(aluno.getLogin()) == null && daoAluno.buscarAlunoEmail(aluno.getEmail()) == null &&
                    daoAluno.buscarProfessor(aluno.getLogin()) == null && daoAluno.buscarProfessorEmail(aluno.getEmail()) == null) {

                daoAluno.salvarAluno(aluno);
                printWriter.write("true");
                printWriter.flush();
                printWriter.close();

            }else{
                printWriter.write("false");
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
