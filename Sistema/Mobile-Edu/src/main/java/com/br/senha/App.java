package com.br.senha;

import com.br.datas.FormatData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Fatinha de Sousa
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Dia: " +FormatData.verificarDia(FormatData.pegarDia()));
    }
}
