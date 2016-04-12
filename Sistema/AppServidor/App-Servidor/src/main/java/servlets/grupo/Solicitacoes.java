package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.ParticipaGrupo;
import com.br.util.FormatData;
import com.br.util.Solicitacao;
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
@WebServlet(name = "Solicitacoes", urlPatterns = {"/Solicitacoes"})
public class Solicitacoes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String login = request.getParameter("login");

        if (!login.equalsIgnoreCase("undefined")) {
            Dao dao = new Dao();
            List<ParticipaGrupo> list = dao.listarGruposPendentes(login);
            List<Solicitacao> grupos = new ArrayList();
            
            for (ParticipaGrupo grupo : list) {
                Solicitacao solicitacao = new Solicitacao();
                solicitacao.setAlunoLogin(grupo.getAluno().getLogin());
                solicitacao.setAlunoNome(grupo.getAluno().getNome());
                solicitacao.setCodigoGrupo(grupo.getGrupo().getCodigo());
                solicitacao.setDataCriacao(FormatData.parseDateString(grupo.getDataParticipacao()));
                solicitacao.setAceito(grupo.isAceito());
                solicitacao.setNomeGrupo(grupo.getGrupo().getNome());
                solicitacao.setDescricaoGrupo(grupo.getGrupo().getDescricao());
                
                grupos.add(solicitacao);
            }
            
            JSONArray jSONArray = new JSONArray(grupos);
            OutputStream os = response.getOutputStream();
            os.write(jSONArray.toString().getBytes());
            os.flush();
            os.close();
        }
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
