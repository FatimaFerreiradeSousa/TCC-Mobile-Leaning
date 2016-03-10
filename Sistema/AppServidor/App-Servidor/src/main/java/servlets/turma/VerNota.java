package servlets.turma;

import com.br.dao.Dao;
import com.br.entidades.Nota;
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
@WebServlet(name = "VerNota", urlPatterns = {"/VerNota"})
public class VerNota extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");
        
        String id = request.getParameter("codigo");
        
        Dao dao = new Dao();
        Nota nota = dao.buscarNota(Integer.parseInt(id));
        
        JSONObject jSONObject = UtilTest.getJSONNota(nota);
        
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
