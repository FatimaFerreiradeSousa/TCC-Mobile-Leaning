package app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Fatinha de Sousa
 */
public class App {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        Date date = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        
        int diaDaSemana = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        
        System.out.println("Dia da semana: " +diaDaSemana);
    }
}
