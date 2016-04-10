package com.br.server;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.util.FormatData;
import com.br.util.FotosServices;
import com.br.util.UtilTest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AtualizarAluno", urlPatterns = {"/AtualizarAluno"})
public class AtualizarAluno extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("ISO-8859-1");
        String login = request.getParameter("login");

        if (!login.equalsIgnoreCase("undefined")) {
            Dao dao = new Dao();
            Aluno aluno = dao.buscarAluno(login);
            JSONObject jSONObject = new JSONObject();

            jSONObject.put("curso", aluno.getCurso());
            jSONObject.put("dataParticipacao", FormatData.parseDateString(aluno.getDataParticipacao()));
            jSONObject.put("descricao", aluno.getDescricao());
            jSONObject.put("email", aluno.getEmail());
            jSONObject.put("foto", FotosServices.converteArquivoEmStringBase64(aluno.getFoto()));
            jSONObject.put("instituicao", aluno.getInstituicao());
            jSONObject.put("login", aluno.getLogin());
            jSONObject.put("senha", aluno.getSenha());
            jSONObject.put("nome", aluno.getNome());
            jSONObject.put("sobrenome", aluno.getSobrenome());

            OutputStream os = response.getOutputStream();
            os.write(jSONObject.toString().getBytes());

            os.flush();
            os.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("post")) {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();

            String json = UtilTest.streamToString(request.getInputStream());
            JSONObject jSONObject = UtilTest.getJSON(json);
            Aluno aluno = new Aluno();

            aluno.setCurso(jSONObject.getString("curso"));
            try {
                aluno.setDataParticipacao(FormatData.parseStringDate(jSONObject.getString("dataParticipacao")));
            } catch (ParseException ex) {
                Logger.getLogger(AtualizarAluno.class.getName()).log(Level.SEVERE, null, ex);
            }
            aluno.setDescricao(jSONObject.getString("descricao"));
            aluno.setEmail(jSONObject.getString("email"));
            aluno.setInstituicao(jSONObject.getString("instituicao"));
            aluno.setLogin(jSONObject.getString("login"));
            aluno.setNome(jSONObject.getString("nome"));
            aluno.setSobrenome(jSONObject.getString("sobrenome"));
            aluno.setSenha(jSONObject.getString("senha"));
            aluno.setFoto(FotosServices.converteStringBase64EmArquivo(jSONObject.getString("foto"), aluno.getLogin()));

            Dao dao = new Dao();
            if (dao.atualizarAluno(aluno)) {
                printWriter.write("Atualizado Com Sucesso");
                printWriter.flush();
                printWriter.close();
            }else{
                printWriter.write("Erro!");
                printWriter.flush();
                printWriter.close();
            }
        }
    }
}
