package servlets.turma;

import com.br.dao.Dao;
import com.br.entidades.Horario;
import com.br.entidades.Turma;
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
@WebServlet(name = "HorariosTurma", urlPatterns = {"/HorariosTurma"})
public class HorariosTurma extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String cod = request.getParameter("codigo");
        String dia = request.getParameter("dia");
        
        Dao dao = new Dao();
        List<Horario> horariosTurma = dao.consultarHorario(dia, cod);

        List<Horario> horarios = new ArrayList<>();

        for (Horario horario : horariosTurma) {

            Horario h = new Horario();
            h.setDia(horario.getDia());
            h.setHorarioFinal(horario.getHorarioFinal());
            h.setHorarioInicial(horario.getHorarioInicial());
            h.setId(horario.getId());
            
            horarios.add(h);
        }

        JSONArray jsonArray = new JSONArray(horarios);

        OutputStream os = response.getOutputStream();
        os.write(jsonArray.toString().getBytes());

        os.flush();
        os.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
