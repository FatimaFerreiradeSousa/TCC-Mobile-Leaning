package com.br.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Fatinha
 */
public class FormatData {

    private static SimpleDateFormat dateFormat;
    
    public static int pegarDia(){
        Date date = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        
        int diaDaSemana = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        
        return diaDaSemana;
    }
    
    public static String verificarDia(int diaDaSemana){
        
        switch (diaDaSemana){
            case 1:
                return "Domingo";
            case 2:
                    return "Segunda-Feira";
            case 3:
                return "Terça-Feira";
            case 4:
                return "Quarta-Feira";
            case 5:
                return "Quinta-Feira";
            case 6:
                return "Sexta-Feira";
            case 7:
                return "Domingo";
            default:
                return "Opção Inválida!";
        }
    }
    
    public static int pegarDiaDaSemana(Date data){
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
         
        dateFormat = new SimpleDateFormat("dd");
        int dia = Integer.parseInt(dateFormat.format(data));
        
        dateFormat = new SimpleDateFormat("MM");
        int mes = Integer.parseInt(dateFormat.format(data));
        
        dateFormat = new SimpleDateFormat("yyyy");
        int ano = Integer.parseInt(dateFormat.format(data));
        
        cal.set(ano, mes - 1, dia); 

        return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    public static String parseDateString(Date data){
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(data);
    }
    
    public static Date parseStringDate(String data) throws ParseException{
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.parse(data);
    }
    
    public static boolean verificarData(Date data){
        
        Date dateSystem = new Date();
        
        return data.before(dateSystem);
    }
}
