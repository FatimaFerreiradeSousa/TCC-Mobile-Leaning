package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Comentario;
import com.br.entidades.Pessoa;
import com.br.entidades.Topico;
import com.br.util.ComentarioJson;
import com.br.util.FormatData;
import com.br.util.FotosServices;
import com.br.util.Servicos;
import com.br.util.UtilTest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Fatinha de Sousa
 */
@WebServlet(name = "Comentarios", urlPatterns = {"/Comentarios"})
public class Comentarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");

        String aux = request.getParameter("topico");

        if (!aux.equalsIgnoreCase("undefined")) {
            int topicoCod = Integer.parseInt(aux);
            Dao dao = new Dao();

            List<Comentario> comentarios = dao.comentariosTopico(topicoCod);
            List<ComentarioJson> temp = new ArrayList();

            for (Comentario comentario : comentarios) {

                ComentarioJson c = new ComentarioJson();
                c.setCodigo(comentario.getCodigo());
                c.setConteudo(comentario.getConteudo());
                c.setDataComentario(FormatData.parseDateString(comentario.getDataComentario()));
                c.setLoginUsuario(comentario.getLoginUsuario());

                Pessoa pessoa = new Pessoa();
                pessoa = Servicos.buscarUsuario(comentario.getLoginUsuario());

                c.setNomeUsuario(pessoa.getNome());
                c.setSobrenomeUsuario(pessoa.getSobrenome());
                c.setFotoUsuario(FotosServices.converteArquivoEmStringBase64(pessoa.getFoto()));

                temp.add(c);
            }

            JSONArray jSONArray = new JSONArray(temp);

            OutputStream os = response.getOutputStream();
            os.write(jSONArray.toString().getBytes());

            os.flush();
            os.close();
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
            comentario.setConteudo(jSONObject.getString("conteudo"));
            comentario.setDataComentario(new Date());
            comentario.setLoginUsuario(jSONObject.getString("usuario"));

            Topico topico = dao.consultarTopico(Integer.parseInt(jSONObject.getString("topico")));
            comentario.setTopico(topico);

            if (dao.salvarComentario(comentario)) {
                printWriter.write("true");
                printWriter.flush();
                printWriter.close();
            } else {
                printWriter.write("false");
                printWriter.flush();
                printWriter.close();
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
