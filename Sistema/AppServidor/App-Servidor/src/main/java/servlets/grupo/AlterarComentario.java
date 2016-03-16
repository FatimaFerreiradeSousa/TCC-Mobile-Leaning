package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Comentario;
import com.br.util.FormatData;
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
@WebServlet(name = "AlterarComentario", urlPatterns = {"/AlterarComentario"})
public class AlterarComentario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String coment = request.getParameter("comentario");

        if (!coment.equalsIgnoreCase("undefined")) {
            Dao dao = new Dao();

            int codigo = Integer.parseInt(coment);
            Comentario comentario = dao.consultarComentario(codigo);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("codigo", comentario.getCodigo());
            jSONObject.put("conteudo", comentario.getConteudo());
            jSONObject.put("loginUsuario", comentario.getLoginUsuario());
            jSONObject.put("data", FormatData.parseDateString(comentario.getDataComentario()));

            OutputStream os = response.getOutputStream();
            os.write(jSONObject.toString().getBytes());

            os.flush();
            os.close();

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("POST")) {

            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();

            String json = UtilTest.streamToString(request.getInputStream());
            JSONObject jSONObject = UtilTest.getJSON(json);

            Dao dao = new Dao();
            Comentario comentario = dao.consultarComentario(jSONObject.getInt("codigo"));
            comentario.setConteudo(jSONObject.getString("conteudo"));

            if (dao.atualizarComentario(comentario)) {
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
