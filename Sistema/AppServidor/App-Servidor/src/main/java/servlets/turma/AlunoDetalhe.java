package servlets.turma;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.util.UtilTest;
import java.io.IOException;
import java.io.OutputStream;
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
@WebServlet(name = "AlunoDetalhe", urlPatterns = {"/AlunoDetalhe"})
public class AlunoDetalhe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");

        String login = request.getParameter("login");
        String turma = request.getParameter("turma");

        if (!login.equalsIgnoreCase("undefined") && !turma.equalsIgnoreCase("undefined")) {

            Dao dao = new Dao();

            Aluno aluno = dao.buscarAluno(login);
            int presenca = dao.qtdPresencas(login, turma);
            int faltas = dao.qtdFaltas(login, turma);

            JSONObject jSONObject = UtilTest.getJSONAluno(aluno, presenca, faltas);

            OutputStream os = response.getOutputStream();
            os.write(jSONObject.toString().getBytes());

            os.flush();
            os.close();
        }
    }
}
