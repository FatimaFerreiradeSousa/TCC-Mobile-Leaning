package servlets.turma;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.entidades.Turma;
import com.br.util.FotosServices;
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
@WebServlet(name = "AlunosTurma", urlPatterns = {"/AlunosTurma"})
public class AlunosTurma extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");
        String cod = request.getParameter("codigo");

        Dao dao = new Dao();
        Turma turma = dao.buscarTurma(cod);

        List<Aluno> alunos = new ArrayList<>();

        for (Aluno aluno : turma.getAlunos()) {

            Aluno al = new Aluno();
            al.setCurso(aluno.getCurso());
            al.setDataParticipacao(aluno.getDataParticipacao());
            al.setDescricao(aluno.getDescricao());
            al.setEmail(aluno.getEmail());
            al.setFoto(FotosServices.converteArquivoEmStringBase64(aluno.getFoto()));
            al.setInstituicao(aluno.getInstituicao());
            al.setLogin(aluno.getLogin());
            al.setNome(aluno.getNome());
            al.setSenha(aluno.getSenha());
            al.setSobrenome(aluno.getSobrenome());

            alunos.add(al);
        }

        JSONArray jsonArray = new JSONArray(alunos);

        OutputStream os = response.getOutputStream();
        os.write(jsonArray.toString().getBytes());

        os.flush();
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
