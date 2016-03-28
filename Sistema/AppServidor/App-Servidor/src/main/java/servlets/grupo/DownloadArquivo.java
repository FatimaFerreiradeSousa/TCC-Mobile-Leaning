package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Topico;
import com.br.util.ArquivoServices;
import com.br.util.TopicoJson;
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
@WebServlet(name = "DownloadArquivo", urlPatterns = {"/DownloadArquivo"})
public class DownloadArquivo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*response.setCharacterEncoding("uff-8");
        String caminho = request.getParameter("caminho");

        System.out.println("Caminho: " + caminho);
        /*
        if(!caminho.equalsIgnoreCase("undefined")){
            Dao dao = new Dao();
            System.out.println("Caminho: " +caminho);
            
            //Topico topico = dao.consultarTopico(Integer.parseInt(codigoTopico));
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("path", ArquivoServices.converteArquivoEmStringBase64(caminho));
            
            OutputStream os = response.getOutputStream();
            os.write(jSONObject.toString().getBytes());
            os.flush();
            os.close();
        }

        OutputStream os = response.getOutputStream();
        os.write("Arquivo Recebido".getBytes());
        os.flush();
        os.close();*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String json = UtilTest.streamToString(request.getInputStream());
        System.out.println("JSON RECEBIDO: " +json);

    }
}
