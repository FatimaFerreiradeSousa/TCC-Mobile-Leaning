package servlets.grupo;

import com.br.dao.Dao;
import com.br.util.UtilTest;
import java.io.IOException;
import java.io.OutputStream;
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
@WebServlet(name = "RemoverSolicitacao", urlPatterns = {"/RemoverSolicitacao"})
public class RemoverSolicitacao extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        String aluno = request.getParameter("login");
        String grupo = request.getParameter("grupo");
        
        if(!aluno.equalsIgnoreCase("undefined") && !grupo.equalsIgnoreCase("grupo")){
            Dao dao = new Dao();
            boolean opcao = dao.verificaSolicitacao(aluno, Integer.parseInt(grupo));
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("opcao", opcao);
            
            OutputStream os = response.getOutputStream();
            os.write(jSONObject.toString().getBytes());
            os.flush();
            os.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getMethod().equalsIgnoreCase("POST")){
            request.setCharacterEncoding("utf-8");
            PrintWriter printWriter = response.getWriter();
            String json = UtilTest.streamToString(request.getInputStream());
            
            JSONObject jSONObject = UtilTest.getJSON(json);
            Dao dao = new Dao();

            if (dao.removerSolicitacao(jSONObject.getString("aluno"), jSONObject.getInt("grupo"))) {
                printWriter.write("Solicitacao Removida!");
                printWriter.flush();
                printWriter.close();
            }else{
                printWriter.write("Erro desconhecido");
                printWriter.flush();
                printWriter.close();
            }
        }
    }
}
