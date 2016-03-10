package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.util.FotosServices;
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
@WebServlet(name = "MembrosGrupo", urlPatterns = {"/MembrosGrupo"})
public class MembrosGrupo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        
        Dao dao = new Dao();
        
        List<Aluno> alunos = new ArrayList();
        List<Aluno> membros = dao.listarMembros(codigo);
        
        for (Aluno aluno : membros) {
            
            Aluno al = new Aluno();
            al.setNome(aluno.getNome());
            al.setSobrenome(aluno.getSobrenome());
            al.setLogin(aluno.getLogin());
            al.setInstituicao(aluno.getInstituicao());
            al.setCurso(aluno.getCurso());
            al.setDescricao(aluno.getDescricao());
            al.setEmail(aluno.getEmail());
            al.setFoto(FotosServices.converteArquivoEmStringBase64(aluno.getFoto()));
            
            alunos.add(al);
        }
        
        JSONArray jSONArray = new JSONArray(alunos);
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
