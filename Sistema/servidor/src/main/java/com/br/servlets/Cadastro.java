package com.br.servlets;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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

        OutputStream os = response.getOutputStream();
        os.write("Salvo Com Sucesso".getBytes());
        os.flush();
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equals("POST")) {
            
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

            Dao daoAluno = new Dao();
            daoAluno.salvarAluno(aluno);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
