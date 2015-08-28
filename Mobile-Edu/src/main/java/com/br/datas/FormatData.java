package com.br.datas;

import com.br.entidades.Anotacao;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fatinha
 */
public class FormatData {

    private static SimpleDateFormat dateFormat;

    public static String pegarMes(Date data) {
        dateFormat = new SimpleDateFormat("MM");
        String mes = dateFormat.format(data);
        return pegarNomeMes(mes);
    }

    public static String pegarAno(Date data) {
        dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(data);
    }

    public static String pegarNomeMes(String mes) {
        String nomeMes = null;

        switch (mes) {
            case "01":
                nomeMes = "Janeiro";
                break;

            case "02":
                nomeMes = "Fevereiro";
                break;

            case "03":
                nomeMes = "Março";
                break;

            case "04":
                nomeMes = "Abril";
                break;

            case "05":
                nomeMes = "Maio";
                break;

            case "06":
                nomeMes = "Junho";
                break;

            case "07":
                nomeMes = "Julho";
                break;

            case "08":
                nomeMes = "Agosto";
                break;

            case "09":
                nomeMes = "Setembro";
                break;

            case "10":
                nomeMes = "Outubro";
                break;
                
            case "11":
                nomeMes = "Novembro";
                break;
                
            case "12":
                nomeMes = "Dezembro";
                break;
        }
        
        return nomeMes;
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
    
    public static List<Anotacao> comparaData(Date dataAtual, List<Anotacao> anotacoes){
        List<Anotacao> anotacoesAtuais = new ArrayList();
        
        int diaSemana = FormatData.pegarDiaDaSemana(dataAtual);
        
        if (diaSemana == 2) {
            for (Anotacao anotacao : anotacoes) {
                if (anotacao.getDataConclusao().compareTo(dataAtual) > 0 && anotacoesAtuais.size() < 4) {
                    anotacoesAtuais.add(anotacao);
                }
            }
        }
        
        return anotacoesAtuais;
    }
    
    public static String parseDateString(Date data){
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(data);
    }
    
    public static boolean verificarData(Date data){
        Date dateSystem = new Date();
        
        return data.before(dateSystem);
    }
}
