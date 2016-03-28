package com.br.app.conection;

import com.br.dao.Dao;
import com.br.entidades.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Fatinha de Sousa
 */
public class App {
    
    public static void main(String[] args) throws IOException {
        
        String doc = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Arquivos\\1 - História 1\\Morrer jovem é como interromper uma música.docx";
        String base64 = ArquivoServices.converteArquivoEmStringBase64(doc);
        
        System.out.println("Gravando arquivo em disco");
        System.out.println("ARQUIVO: " +ArquivoServices.converteStringBase64EmArquivo(base64, 1, "Morrer jovem.docx"));
        
        
    }
    
}
