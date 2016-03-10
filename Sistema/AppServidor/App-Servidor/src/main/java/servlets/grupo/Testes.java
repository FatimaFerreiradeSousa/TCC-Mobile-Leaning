package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Grupo;
import com.br.entidades.Professor;
import com.br.entidades.Teste;
import com.br.util.FormatData;
import com.br.util.TesteAux;
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
@WebServlet(name = "Testes", urlPatterns = {"/Testes"})
public class Testes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");
        
        int codigo = Integer.parseInt(request.getParameter("grupo"));
        Dao dao = new Dao();
        
        Grupo grupo = dao.consultarGrupo(codigo);
        List<TesteAux> testes = new ArrayList();
        
        for (Teste teste : grupo.getTestesGrupo()) {
            
            TesteAux t = new TesteAux();
            t.setAssunto(teste.getAssunto());
            t.setCategoria(teste.getCategoria());
            t.setDisciplina(teste.getDisciplina());
            t.setCodigo(teste.getCodigo());
            t.setDataEntrega(FormatData.parseDateString(teste.getDataEntrega()));
            t.setDisponivel(teste.isDisponivel());
            t.setQtdPerguntas(teste.getQtdPerguntas());
            
            Professor professor = new Professor();
            professor.setNome(teste.getProfessor().getNome());
            professor.setSobrenome(teste.getProfessor().getSobrenome());
            
            t.setNomeProfessor(professor.getNome());
            t.setSobrenomeProfessor(professor.getSobrenome());
            
            t.setQtdAlunos(dao.resultados(teste.getCodigo()).size());
            
            testes.add(t);
        }
        
        JSONArray jSONArray = new JSONArray(testes);
        OutputStream os = response.getOutputStream();
        os.write(jSONArray.toString().getBytes());

        os.flush();
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
