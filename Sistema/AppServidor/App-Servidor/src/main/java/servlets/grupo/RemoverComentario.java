package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Comentario;
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
@WebServlet(name = "RemoverComentario", urlPatterns = {"/RemoverComentario"})
public class RemoverComentario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RemoverComentario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoverComentario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("POST")) {

            PrintWriter printWriter = response.getWriter();
            response.setContentType("text/html");

            String json = UtilTest.streamToString(request.getInputStream());
            JSONObject jSONObject = UtilTest.getJSON(json);

            Dao dao = new Dao();

            Comentario comentario = new Comentario();
            comentario.setCodigo(jSONObject.getInt("codigo"));
            comentario.setConteudo(jSONObject.getString("conteudo"));
            comentario.setLoginUsuario(jSONObject.getString("nomeUsuario"));

            if (dao.removerComentarios(comentario)) {
                printWriter.write("Removido!");
                printWriter.flush();
                printWriter.close();
            } else {
                printWriter.write("Erro!");
                printWriter.flush();
                printWriter.close();
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Remover comentarios de um grupo!";
    }// </editor-fold>

}
