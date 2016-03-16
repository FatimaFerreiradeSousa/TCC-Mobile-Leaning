package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Pergunta;
import com.br.entidades.Professor;
import com.br.entidades.Resposta;
import com.br.entidades.Teste;
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
@WebServlet(name = "ResponderTeste", urlPatterns = {"/ResponderTeste"})
public class ResponderTeste extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String codigo = request.getParameter("teste");

        if (!codigo.equalsIgnoreCase("undefined")) {
            
            Dao dao = new Dao();
            Teste teste = dao.consultarTeste(Integer.parseInt(codigo));
            List<Pergunta> perguntas = teste.getQuestoesExercicios();
            List<Pergunta> temp = new ArrayList();
            
            for (Pergunta p : perguntas) {
                
                Pergunta pergunta = new Pergunta();
                pergunta.setCategoria(p.getCategoria());
                pergunta.setCodigo(p.getCodigo());
                pergunta.setEnunciado(p.getEnunciado());
                pergunta.setPeso(p.getPeso());
                pergunta.setQtdRespostas(p.getQtdRespostas());
                
                Professor professor = new Professor();
                professor.setNome(p.getProfessor().getNome());
                professor.setSobrenome(p.getProfessor().getSobrenome());
                pergunta.setProfessor(professor);
                
                List<Resposta> respostas = new ArrayList();
                
                for(Resposta resposta: p.getRespostas()){
                    Resposta r = new Resposta();
                    r.setConteudo(resposta.getConteudo());
                    r.setNumero(resposta.getNumero());
                    r.setRespostaCerta(resposta.getRespostaCerta());
                    respostas.add(r);
                }
                
                pergunta.setRespostas(respostas);
                temp.add(pergunta);
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
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
