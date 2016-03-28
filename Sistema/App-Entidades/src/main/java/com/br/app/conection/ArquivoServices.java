package com.br.app.conection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;

/**
 *
 * @author Fatinha de Sousa
 */
public class ArquivoServices {

    /*Recebe uma string no formato base 64 e converte em arquivo do disco*/
    public static String converteStringBase64EmArquivo(String imageDataString, int grupo, String nomeArquivo) throws FileNotFoundException, IOException {
        byte[] imageByteArray = decodeImage(imageDataString);

        String caminho = "C:\\Users\\Fatinha de Sousa\\Documents\\Repositorios\\TCC-Mobile-Learning\\Arquivos\\" + grupo + "\\";

        File dir = new File(caminho);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String path = caminho + nomeArquivo;
        // Escreve um array de bytes de imagem em sistema de arquivos
        FileOutputStream imageOutFile = new FileOutputStream(
                path);
        imageOutFile.write(imageByteArray);
        imageOutFile.close();

        return caminho;
    }


    /*Converter o arquivo local em string base 64*/
    public static String converteArquivoEmStringBase64(String caminho) throws IOException {
        File file = new File(caminho);
        String imageDataString = null;
        try {
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            imageDataString = ArquivoServices.encodeImage(imageData);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArquivoServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return imageDataString;
    }

    public static String encodeImage(byte[] imageByteArray) {
        return Base64.getEncoder().encodeToString(imageByteArray);
    }

    public static byte[] decodeImage(String imageDataString) {
        return Base64.getDecoder().decode(imageDataString);
    }
}
