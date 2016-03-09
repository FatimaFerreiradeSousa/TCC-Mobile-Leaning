package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Grupo;
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
@WebServlet(name = "GrupoSelect", urlPatterns = {"/GrupoSelect"})
public class GrupoSelect extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setCharacterEncoding("UTF-8");
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        
        Dao dao = new Dao();
        Grupo grupo = dao.consultarGrupo(codigo);
        
        JSONObject jSONObject = UtilTest.getJSONGrupo(grupo);
        
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
