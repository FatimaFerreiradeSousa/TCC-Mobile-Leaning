package com.br.server;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.util.UtilTest;
import java.io.IOException;
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
@WebServlet(name = "RecuperarSenha", urlPatterns = {"/RecuperarSenha"})
public class RecuperarSenha extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        if (request.getMethod().equalsIgnoreCase("post")) {

            String json = UtilTest.streamToString(request.getInputStream());
            JSONObject jSONObject = UtilTest.getJSON(json);

            Dao dao = new Dao();
            Aluno aluno = dao.buscarAluno(jSONObject.getString("login"));

            if (aluno != null) {
                RecuperarEmail recuperarEmail = new RecuperarEmail();
                recuperarEmail.enviarEmail(aluno.getEmail(), aluno.getSenha());
                printWriter.write("Um email contendo sua senha foi enviado...");
                printWriter.flush();
                printWriter.close();
            }else{
                printWriter.write("Nenhum usuário encontrado");
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
