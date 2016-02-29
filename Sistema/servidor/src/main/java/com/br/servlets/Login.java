package com.br.servlets;

import com.br.dao.DaoAluno;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private static Aluno aluno = new Aluno();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DaoAluno daoAluno = new DaoAluno();
        Aluno al = daoAluno.loginAluno("aliu", "12345");
        JSONObject jSONObject = UtilTest.getJSONObject(al);
        
        OutputStream os = response.getOutputStream();
        os.write(jSONObject.toString().getBytes());

        os.flush();
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equals("POST")) {

            String jsonString = UtilTest.streamToString(request.getInputStream());
            JSONObject jSONObject = UtilTest.getJSON(jsonString);

            Aluno al = new Aluno();
            al.setLogin(jSONObject.getString("login"));
            al.setSenha(jSONObject.getString("senha"));

            DaoAluno daoAluno = new DaoAluno();
            aluno = daoAluno.loginAluno(al.getLogin(), al.getSenha());

            if (aluno != null) {
                System.out.println("Aluno Recebido: " + aluno.getNome());
            }
        }
    }
}
