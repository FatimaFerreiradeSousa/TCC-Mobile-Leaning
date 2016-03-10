package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.entidades.Grupo;
import com.br.util.UtilTest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
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
@WebServlet(name = "GrupoSelect", urlPatterns = {"/GrupoSelect"})
public class GrupoSelect extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        
        Dao dao = new Dao();
        Grupo grupo = dao.consultarGrupo(codigo);
        List<Aluno> membros = dao.listarMembros(codigo);
        
        JSONObject jSONObject = UtilTest.getJSONGrupo(grupo, membros.size());
        
        OutputStream os = response.getOutputStream();
        os.write(jSONObject.toString().getBytes());

        os.flush();
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
