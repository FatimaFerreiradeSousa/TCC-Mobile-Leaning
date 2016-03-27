package servlets.turma;

import com.br.dao.Dao;
import com.br.entidades.Presenca;
import com.br.util.FormatData;
import com.br.util.PresencaJson;
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
@WebServlet(name = "Presencas", urlPatterns = {"/Presencas"})
public class Presencas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String login = request.getParameter("login");
        String turma = request.getParameter("turma");

        if (!login.equalsIgnoreCase("undefined") && !turma.equalsIgnoreCase("undefined")) {
            Dao dao = new Dao();
            List<PresencaJson> presencasAluno = new ArrayList();
            List<Presenca> temp = dao.listarPresencasAluno(login, turma);

            for (Presenca p : temp) {
                PresencaJson presencaJson = new PresencaJson();
                presencaJson.setDataPreseca(FormatData.parseDateString(p.getDataPreseca()));
                presencaJson.setHoraAula(p.getHoraAula());
                presencaJson.setStatus(p.isStatus());

                presencasAluno.add(presencaJson);
            }

            JSONArray jSONArray = new JSONArray(presencasAluno);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(jSONArray.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que mostra o historico de presencas de um aluno";
    }// </editor-fold>
}
