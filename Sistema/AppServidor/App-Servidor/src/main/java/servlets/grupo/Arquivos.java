package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Topico;
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
@WebServlet(name = "Arquivos", urlPatterns = {"/Arquivos"})
public class Arquivos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int codigo = Integer.parseInt(request.getParameter("grupo"));
        
        Dao dao = new Dao();
        List<Topico> topicos = dao.listarArquivos(codigo);
        List<Topico> temp = new ArrayList();
        
        for (Topico topico : topicos) {
            
            Topico t = new Topico();
            t.setCaminho(topico.getCaminho());
            t.setCodigo(topico.getCodigo());
            t.setConteudo(topico.getConteudo());
            t.setDataCriacao(topico.getDataCriacao());
            t.setLoginUsuario(topico.getLoginUsuario());
            t.setNome(topico.getNome());
            t.setTipo(topico.getTipo());
            //t.setComentarios(topico.getComentarios());
            
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
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
