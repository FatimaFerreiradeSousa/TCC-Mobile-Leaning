package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Grupo;
import com.br.entidades.ParticipaGrupo;
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
@WebServlet(name = "AlunoInfo", urlPatterns = {"/AlunoInfo"})
public class AlunoInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");
        
        String login = request.getParameter("login");
        int grupo = Integer.parseInt(request.getParameter("grupo"));
                
        Dao dao = new Dao();
        
        ParticipaGrupo participaGrupo = dao.buscarParticipaGrupo(login, grupo);
        int testesRespondidos = dao.listarExcerciciosAluno(login, grupo).size();
        int publicacoesFeitas = dao.listarTopicosUsuario(login, grupo).size();
        
        Grupo g = dao.consultarGrupo(grupo);
        int testesGrupo = g.getTestesGrupo().size();
        
        JSONObject jSONObject = UtilTest.getJSONAlunoInfo(participaGrupo, testesRespondidos, publicacoesFeitas, testesGrupo);
        
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
