package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Topico;
import com.br.util.UtilTest;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RemoverTopico", urlPatterns = {"/RemoverTopico"})
public class RemoverTopico extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getMethod().equalsIgnoreCase("POST")){
            PrintWriter printWriter = response.getWriter();
            response.setContentType("text/html");
            
            String json = UtilTest.streamToString(request.getInputStream());
            JSONObject jSONObject = UtilTest.getJSON(json);
            
            Dao dao = new Dao();
            Topico topico = new Topico();
            topico.setCodigo(jSONObject.getInt("codigo"));
            topico.setConteudo(jSONObject.getString("conteudo"));
            topico.setLoginUsuario(jSONObject.getString("loginUsuario"));
            
            if(dao.removerTopico(topico)){
                printWriter.write("Removido!");
                printWriter.flush();
                printWriter.close();
            }else{
                printWriter.write("Erro!");
                printWriter.flush();
                printWriter.close();
            }
        }
        
    }
}
