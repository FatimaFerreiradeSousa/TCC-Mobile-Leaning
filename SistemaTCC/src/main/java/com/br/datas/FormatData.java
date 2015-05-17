package com.br.datas;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
