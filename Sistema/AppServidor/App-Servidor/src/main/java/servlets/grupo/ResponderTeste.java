package servlets.grupo;

import com.br.dao.Dao;
import com.br.entidades.Aluno;
import com.br.entidades.Grupo;
import com.br.entidades.ParticipaGrupo;
import com.br.entidades.Pergunta;
import com.br.entidades.Professor;
import com.br.entidades.RespondeExercicio;
import com.br.entidades.Resposta;
import com.br.entidades.Teste;
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

                for (Resposta resposta : p.getRespostas()) {
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

        if (request.getMethod().equalsIgnoreCase("POST")) {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();

            String json = UtilTest.streamToString(request.getInputStream());
            JSONObject jSONObject = UtilTest.getJSON(json);

            Dao dao = new Dao();
            RespondeExercicio respondeExercicio = new RespondeExercicio();
            Aluno aluno = new Aluno();
            aluno.setLogin(jSONObject.getString("aluno"));
            respondeExercicio.setAluno(aluno);

            Grupo grupo = new Grupo();
            grupo.setCodigo(jSONObject.getInt("grupo"));
            respondeExercicio.setGrupo(grupo);
            respondeExercicio.setCodTeste(jSONObject.getInt("teste"));
            respondeExercicio.setDataResposta(new Date());
            respondeExercicio.setNota((float) jSONObject.getDouble("nota"));
            respondeExercicio.setRespondido(true);

            ParticipaGrupo participaGrupo = dao.buscarParticipaGrupo(aluno.getLogin(), grupo.getCodigo());
            participaGrupo.setPontuacao(participaGrupo.getPontuacao() + respondeExercicio.getNota());

            if (dao.salvarRespondeTeste(respondeExercicio)) {
                if (dao.atualizarPontuacao(participaGrupo)) {
                    
                } else {
                    printWriter.write("Error!");
                    printWriter.flush();
                    printWriter.close();
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
