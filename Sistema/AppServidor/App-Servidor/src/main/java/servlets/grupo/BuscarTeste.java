package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Teste;
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
@WebServlet(name = "BuscarTeste", urlPatterns = {"/BuscarTeste"})
public class BuscarTeste extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setCharacterEncoding("utf-8");
        String codigo = request.getParameter("teste");
        
        if(!codigo.equalsIgnoreCase("undefined")){
            Dao dao = new Dao();
            Teste teste = dao.consultarTeste(Integer.parseInt(codigo));
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("codigo", teste.getCodigo());
            jSONObject.put("assunto", teste.getAssunto());
            jSONObject.put("perguntas", teste.getQtdPerguntas());
            jSONObject.put("professorNome", teste.getProfessor().getNome());
            jSONObject.put("professorSobrenome", teste.getProfessor().getSobrenome());
            
            OutputStream os = response.getOutputStream();
            os.write(jSONObject.toString().getBytes());
            os.flush();
            os.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
