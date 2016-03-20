package servlets.grupo;

import com.br.dao.Dao;
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
@WebServlet(name = "VerificarMembro", urlPatterns = {"/VerificarMembro"})
public class VerificarMembro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        String aluno = request.getParameter("login");
        String grupo = request.getParameter("grupo");
        
        if(!aluno.equalsIgnoreCase("undefined") && !grupo.equalsIgnoreCase("undefined")){
            Dao dao = new Dao();
            boolean codigo = dao.verificaSeJaEhMembro(aluno, Integer.parseInt(grupo));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("codigo", codigo);
            
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(jSONObject.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
