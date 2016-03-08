package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Comentario;
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
@WebServlet(name = "Comentarios", urlPatterns = {"/Comentarios"})
public class Comentarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int topicoCod = Integer.parseInt(request.getParameter("topico"));
        Dao dao = new Dao();
        
        List<Comentario> comentarios = dao.comentariosTopico(topicoCod);
        List<Comentario> temp = new ArrayList();
        
        for (Comentario comentario : comentarios) {
            
            Comentario c = new Comentario();
            c.setCodigo(comentario.getCodigo());
            c.setConteudo(comentario.getConteudo());
            c.setDataComentario(comentario.getDataComentario());
            c.setLoginUsuario(comentario.getLoginUsuario());
            
            temp.add(c);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
