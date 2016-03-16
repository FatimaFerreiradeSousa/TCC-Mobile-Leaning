package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Teste;
import com.br.util.FormatData;
import java.io.IOException;
import java.io.OutputStream;
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
@WebServlet(name = "VerTeste", urlPatterns = {"/VerTeste"})
public class VerTeste extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String codigo = request.getParameter("teste");
        String loginAluno = request.getParameter("aluno");

        if (!codigo.equalsIgnoreCase("undefined") && !loginAluno.equalsIgnoreCase("undefined")) {

            Dao dao = new Dao();
            Teste teste = dao.consultarTeste(Integer.parseInt(codigo));
            boolean respondeTeste = dao.respondeTeste(Integer.parseInt(codigo), loginAluno);
            boolean date = FormatData.verificarData(teste.getDataEntrega());

            JSONObject jSONObject = new JSONObject();

            jSONObject.put("codigo", teste.getCodigo());
            jSONObject.put("assunto", teste.getAssunto());
            jSONObject.put("categoria", teste.getCategoria());
            jSONObject.put("dataEntrega", FormatData.parseDateString(teste.getDataEntrega()));
            jSONObject.put("disciplina", teste.getDisciplina());
            jSONObject.put("professor", teste.getProfessor().getNome() + " " + teste.getProfessor().getSobrenome());
            jSONObject.put("qtdPerguntas", teste.getQtdPerguntas());

            if (respondeTeste == false) {
                jSONObject.put("disponivel", 1);
            } else{
                if(date == true){
                    jSONObject.put("disponivel", 2);
                }else{
                    jSONObject.put("disponivel", 0);
                }
            }
            
            OutputStream os = response.getOutputStream();
            os.write(jSONObject.toString().getBytes());

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
