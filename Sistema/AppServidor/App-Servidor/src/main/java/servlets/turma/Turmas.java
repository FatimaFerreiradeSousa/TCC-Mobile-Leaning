package servlets.turma;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.entidades.Professor;
import com.br.entidades.Turma;
import com.br.util.FormatData;
import com.br.util.TurmaJson;
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

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");
        String login = request.getParameter("loginAl");
        
        if (!login.equalsIgnoreCase("undefined")) {
            Dao daoAluno = new Dao();
            Aluno al = daoAluno.buscarAluno(login);

            List<TurmaJson> turmas = new ArrayList();
            List<Turma> tAluno = al.getTurmas();

            for (Turma turma : tAluno) {
                TurmaJson turmaJson = new TurmaJson();
                turmaJson.setCategoria(turma.getCategoria());
                turmaJson.setCodigo(turma.getCodigo());
                turmaJson.setDataInicio(FormatData.parseDateString(turma.getDataInicio()));
                turmaJson.setDataTerminio(FormatData.parseDateString(turma.getDataTerminio()));
                turmaJson.setDescricao(turma.getDescricao());
                turmaJson.setNome(turma.getNome());
                turmaJson.setProfessorNome(turma.getProfessor().getNome());
                turmaJson.setProfessoSobrenome(turma.getProfessor().getSobrenome());
                turmaJson.setQtdAlunos(turma.getAlunos().size());

                turmas.add(turmaJson);
            }

            JSONArray jSONArray = new JSONArray(turmas);

            OutputStream os = response.getOutputStream();
            os.write(jSONArray.toString().getBytes());

            os.flush();
            os.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
