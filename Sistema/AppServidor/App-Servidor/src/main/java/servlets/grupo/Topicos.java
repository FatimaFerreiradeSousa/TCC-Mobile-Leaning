package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Grupo;
import com.br.entidades.Pessoa;
import com.br.entidades.Topico;
import com.br.util.FormatData;
import com.br.util.FotosServices;
import com.br.util.Servicos;
import com.br.util.TopicoAux;
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
@WebServlet(name = "Topicos", urlPatterns = {"/Topicos"})
public class Topicos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");

        int codigo = Integer.parseInt(request.getParameter("grupo"));

        Dao dao = new Dao();
        List<Topico> topicos = dao.listarTopicos(codigo);
        List<TopicoAux> temp = new ArrayList();

        for (Topico topico : topicos) {

            TopicoAux t = new TopicoAux();
            t.setCaminho(topico.getCaminho());
            t.setCodigo(topico.getCodigo());
            t.setConteudo(topico.getConteudo());
            t.setDataCriacao(FormatData.parseDateString(topico.getDataCriacao()));
            t.setLoginUsuario(topico.getLoginUsuario());
            t.setNome(topico.getNome());
            t.setTipo(topico.getTipo());

            // t.setComentarios(topico.getComentarios());
            Pessoa pessoa = new Pessoa();
            pessoa = Servicos.buscarUsuario(topico.getLoginUsuario());

            t.setNomeUsuario(pessoa.getNome());
            t.setSobrenomeUsuario(pessoa.getSobrenome());
            t.setFotoUsuario(FotosServices.converteArquivoEmStringBase64(pessoa.getFoto()));

            t.setCodigoGrupo(topico.getGrupo().getCodigo());
            temp.add(t);
        }

        JSONArray jSONArray = new JSONArray(temp);
        OutputStream os = response.getOutputStream();
        os.write(jSONArray.toString().getBytes());

        os.flush();
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equals("POST")) {

            PrintWriter printWriter = response.getWriter();
            response.setContentType("text/html");

            Dao dao = new Dao();

            String json = UtilTest.streamToString(request.getInputStream());
            JSONObject jSONObject = UtilTest.getJSON(json);

            Topico topico = new Topico();
            topico.setConteudo(jSONObject.getString("conteudo"));
            topico.setDataCriacao(new Date());
            topico.setLoginUsuario(jSONObject.getString("loginUsuario"));
            topico.setTipo("Publicacao");

            Grupo grupo = dao.consultarGrupo(Integer.parseInt(jSONObject.getString("grupoCod")));
            topico.setGrupo(grupo);

            if (topico.getConteudo() != null) {
                dao.salvarTopico(topico);
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
}
