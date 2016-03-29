package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Topico;
import com.br.util.ArquivoServices;
import com.br.util.TopicoJson;
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
@WebServlet(name = "DownloadArquivo", urlPatterns = {"/DownloadArquivo"})
public class DownloadArquivo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*response.setCharacterEncoding("uff-8");
        String caminho = request.getParameter("caminho");

        System.out.println("Caminho: " + caminho);
        */
        //if(!caminho.equalsIgnoreCase("undefined")){
            Dao dao = new Dao();
            System.out.println("GET");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("path", "");
            String arquivoBase64 = ArquivoServices.converteArquivoEmStringBase64("C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Arquivos\\1\\885446.pdf");
            System.out.println(arquivoBase64);
            OutputStream os = response.getOutputStream();
            os.write(arquivoBase64.getBytes());
            os.flush();
            os.close();
        //}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
