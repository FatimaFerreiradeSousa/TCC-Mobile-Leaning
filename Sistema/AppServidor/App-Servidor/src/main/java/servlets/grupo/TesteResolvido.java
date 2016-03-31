package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Pergunta;
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
import org.json.JSONObject;

/**
 *
 * @author Fatinha de Sousa
 */
@WebServlet(name = "TesteResolvido", urlPatterns = {"/TesteResolvido"})
public class TesteResolvido extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setCharacterEncoding("utf-8");
        String codigoTeste = request.getParameter("teste");
        
        if(!codigoTeste.equalsIgnoreCase("undefined")){
            Dao dao = new Dao();
            Teste teste = dao.consultarTeste(Integer.parseInt(codigoTeste));
            List<Pergunta> perguntas = new ArrayList();
            List<Pergunta> temp = teste.getQuestoesExercicios();
            
            for(Pergunta p : temp){
                Pergunta pergunta = new Pergunta();
                pergunta.setCategoria(p.getCategoria());
                pergunta.setEnunciado(p.getEnunciado());
                pergunta.setPeso(p.getPeso());
                pergunta.setQtdRespostas(p.getQtdRespostas());
                
                List<Resposta> respostas = new ArrayList();
                List<Resposta> aux = p.getRespostas();
                
                for(Resposta r : aux){
                    Resposta resposta = new Resposta();
                    resposta.setConteudo(r.getConteudo());
                    resposta.setNumero(r.getNumero());
                    resposta.setRespostaCerta(r.getRespostaCerta());
                    
                    respostas.add(resposta);
                }
                
                pergunta.setRespostas(respostas);
                perguntas.add(pergunta);
            }
            
            JSONArray jSONArray = new JSONArray(perguntas);
            OutputStream os = response.getOutputStream();
            os.write(jSONArray.toString().getBytes());
            os.flush();
            os.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
