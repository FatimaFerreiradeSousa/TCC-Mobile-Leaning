package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.ParticipaGrupo;
import com.br.util.FotosServices;
import com.br.util.ParticipaGrupoJson;
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
@WebServlet(name = "Ranking", urlPatterns = {"/Ranking"})
public class Ranking extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        String codigoGrupo = request.getParameter("grupo");
        
        if(!codigoGrupo.equalsIgnoreCase("undefined")){
            Dao dao = new Dao();
            List<ParticipaGrupoJson> grupoJsons = new ArrayList();
            List<ParticipaGrupo> temp = dao.listarRancking(Integer.parseInt(codigoGrupo));
            
            for(ParticipaGrupo p : temp){
                ParticipaGrupoJson grupoJson = new ParticipaGrupoJson();
                grupoJson.setAlunoNome(p.getAluno().getNome());
                grupoJson.setAlunoSobrenome(p.getAluno().getSobrenome());
                grupoJson.setFotoAluno(FotosServices.converteArquivoEmStringBase64(p.getAluno().getFoto()));
                grupoJson.setPontuacao(p.getPontuacao());
                
                grupoJsons.add(grupoJson);
            }
            
            JSONArray jSONArray = new JSONArray(grupoJsons);
            OutputStream os = response.getOutputStream();
            os.write(jSONArray.toString().getBytes());
            os.flush();
            os.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que lista o ranking de um grupo.";
    }// </editor-fold>
}
