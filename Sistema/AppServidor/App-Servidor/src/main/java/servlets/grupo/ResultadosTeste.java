package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.entidades.RespondeExercicio;
import com.br.util.FormatData;
import com.br.util.FotosServices;
import com.br.util.RespondeExercicioAux;
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
@WebServlet(name = "ResultadosTeste", urlPatterns = {"/ResultadosTeste"})
public class ResultadosTeste extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("ISO-8859-1");
        
        int teste = Integer.parseInt(request.getParameter("teste"));
        
        Dao dao = new Dao();
        
        List<RespondeExercicio> testes = dao.resultados(teste);
        List<RespondeExercicioAux> temp = new ArrayList();
        
        for (RespondeExercicio respondeExercicio : testes) {
            
            RespondeExercicioAux aux = new RespondeExercicioAux();
            aux.setId(respondeExercicio.getId());
            aux.setDataResposta(FormatData.parseDateString(respondeExercicio.getDataResposta()));
            aux.setNota(respondeExercicio.getNota());
            aux.setRespondido(respondeExercicio.isRespondido());
            
            Aluno aluno = respondeExercicio.getAluno();
            aux.setAlunoLogin(aluno.getLogin());
            aux.setAlunoNome(aluno.getNome());
            aux.setAlunoSobrenome(aluno.getSobrenome());
            aux.setFotoAluno(FotosServices.converteArquivoEmStringBase64(aluno.getFoto()));
            
            temp.add(aux);
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
}
