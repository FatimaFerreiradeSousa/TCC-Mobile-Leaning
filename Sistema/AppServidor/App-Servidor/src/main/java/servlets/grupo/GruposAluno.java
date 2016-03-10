package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.entidades.Grupo;
import com.br.entidades.ParticipaGrupo;
import com.br.entidades.Professor;
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
@WebServlet(name = "GruposAluno", urlPatterns = {"/GruposAluno"})
public class GruposAluno extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");
        String login = request.getParameter("login");
        
        Dao dao = new Dao();
        
        List<ParticipaGrupo> grupos = dao.listarGruposAluno(login);
        List<ParticipaGrupo> temp = new ArrayList();
        
        for (ParticipaGrupo participaGrupo : grupos) {
            
            ParticipaGrupo grupo = new ParticipaGrupo();
            grupo.setAceito(participaGrupo.isAceito());
            grupo.setDataParticipacao(participaGrupo.getDataParticipacao());
            grupo.setId(participaGrupo.getId());
            grupo.setPontuacao(participaGrupo.getPontuacao());
            
            Aluno al = new Aluno();
            al.setNome(participaGrupo.getAluno().getNome());
            al.setSobrenome(participaGrupo.getAluno().getSobrenome());
            al.setLogin(participaGrupo.getAluno().getLogin());
            grupo.setAluno(al);
            
            Grupo g = new Grupo();
            g.setCodigo(participaGrupo.getGrupo().getCodigo());
            g.setNome(participaGrupo.getGrupo().getNome());
            g.setDescricao(participaGrupo.getGrupo().getDescricao());
            g.setDataCriacao(participaGrupo.getDataParticipacao());
            Professor professor = new Professor();
            professor.setNome(participaGrupo.getGrupo().getProfessorGrupos().getNome());
            professor.setSobrenome(participaGrupo.getGrupo().getProfessorGrupos().getSobrenome());
            g.setProfessorGrupos(professor);
            grupo.setGrupo(g);
            
            temp.add(grupo);
        }
        
        JSONArray jSONArray = new JSONArray(temp);
        OutputStream os = response.getOutputStream();
        os.write(jSONArray.toString().getBytes());

        os.flush();
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
