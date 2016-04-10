package servlets.grupo;

import com.br.dao.Dao;
import com.br.util.ArquivoServices;
import com.br.util.UtilTest;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

/**
 *
 * @author Fatinha de Sousa
 */
@WebServlet(name = "DownloadArquivo", urlPatterns = {"/DownloadArquivo"})
@MultipartConfig(maxFileSize = 10 * 1024 * 1024, maxRequestSize = 20 * 1024 * 1024, fileSizeThreshold = 5 * 1024 * 1024)
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

        if (request.getMethod().equalsIgnoreCase("POST")) {

            String json = UtilTest.streamToString(request.getInputStream());
            //System.out.println("JSON Recebido: " +json);
            String contextPath = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Arquivos\\";
            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

            if (!isMultipartContent) {
                System.out.println("Erro");
            } else {
                
                System.out.println("Arquivo Recebido");
                
            }
        }
    }
}
