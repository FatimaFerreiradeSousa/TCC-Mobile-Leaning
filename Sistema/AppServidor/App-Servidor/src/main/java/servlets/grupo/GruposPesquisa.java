package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.entidades.Grupo;
import com.br.entidades.ParticipaGrupo;
import com.br.entidades.Professor;
import com.br.util.GrupoJson;
import com.br.util.UtilTest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Fatinha de Sousa
 */
@WebServlet(name = "GruposPesquisa", urlPatterns = {"/GruposPesquisa"})
public class GruposPesquisa extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("ISO-8859-1");
        response.setCharacterEncoding("utf-8");
        String nome = request.getParameter("grupo");
        String aluno = request.getParameter("aluno");
        
        if (!nome.equalsIgnoreCase("undefined") && !aluno.equalsIgnoreCase("undefined")) {

            Dao dao = new Dao();

            List<Grupo> grupos = dao.pesquisarGrupoPorNome(nome);
            List<GrupoJson> temp = new ArrayList();

            if (grupos.size() > 0) {

                for (Grupo grupo : grupos) {

                    GrupoJson grupoJson = new GrupoJson();
                    grupoJson.setNome(grupo.getNome());
                    grupoJson.setCodigo(grupo.getCodigo());
                    grupoJson.setDescricao(grupo.getDescricao());
                    grupoJson.setProfessorNome(grupo.getProfessorGrupos().getNome());
                    grupoJson.setProfessorSobrenome(grupo.getProfessorGrupos().getSobrenome());
                    grupoJson.setStatus(dao.verificaSeJaEhMembro(aluno, grupo.getCodigo()));
                    temp.add(grupoJson);
                }
            }

            JSONArray jSONArray = new JSONArray(temp);
            OutputStream os = response.getOutputStream();
            os.write(jSONArray.toString().getBytes());
            os.flush();
            os.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("POST")) {
            request.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = response.getWriter();

            String json = UtilTest.streamToString(request.getInputStream());

            JSONObject jSONObject = UtilTest.getJSON(json);
            Dao dao = new Dao();
            ParticipaGrupo participaGrupo = new ParticipaGrupo();
            participaGrupo.setAceito(false);
            participaGrupo.setDataParticipacao(new Date());
            participaGrupo.setPontuacao(0);

            Aluno aluno = dao.buscarAluno(jSONObject.getString("aluno"));
            participaGrupo.setAluno(aluno);

            Grupo grupo = dao.consultarGrupo(jSONObject.getInt("grupo"));
            participaGrupo.setGrupo(grupo);

            if (dao.participaGrupo(participaGrupo)) {
                printWriter.write("Solicitacao enviada");
                printWriter.flush();
                printWriter.close();
            } else {
                printWriter.write("Erro desconhecido");
                printWriter.flush();
                printWriter.close();
            }
        }

    }
}
