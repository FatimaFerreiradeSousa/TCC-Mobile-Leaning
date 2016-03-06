package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Grupo;
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
@WebServlet(name = "GruposPesquisa", urlPatterns = {"/GruposPesquisa"})
public class GruposPesquisa extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("grupo");
        
        Dao dao = new Dao();
        
        List<Grupo> grupos = dao.pesquisarGrupoPorNome(nome);
        List<Grupo> temp = new ArrayList();
        
        for (Grupo grupo : grupos) {

            Grupo aux = new Grupo();
            aux.setNome(grupo.getNome());
            aux.setCodigo(grupo.getCodigo());
            
            Professor professor = new Professor();
            professor.setNome(grupo.getProfessorGrupos().getNome());
            professor.setSobrenome(grupo.getProfessorGrupos().getSobrenome());
            aux.setProfessorGrupos(professor);

            temp.add(aux);
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
