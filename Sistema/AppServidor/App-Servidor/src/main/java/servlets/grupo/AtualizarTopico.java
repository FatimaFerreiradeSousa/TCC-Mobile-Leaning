package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Grupo;
import com.br.entidades.Topico;
import com.br.util.FormatData;
import com.br.util.UtilTest;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AtualizarTopico", urlPatterns = {"/AtualizarTopico"})
public class AtualizarTopico extends HttpServlet {

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
            topico.setTipo(jSONObject.getString("tipo"));
            try {
                topico.setDataCriacao(FormatData.parseStringDate(jSONObject.getString("dataCriacao")));
            } catch (ParseException ex) {
                Logger.getLogger(AtualizarTopico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Grupo g = dao.consultarGrupo(Integer.parseInt(jSONObject.getString("codigoGrupo")));
            topico.setGrupo(g);
            
            if(dao.alterarTopico(topico)){
                printWriter.write("Alterado!");
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
