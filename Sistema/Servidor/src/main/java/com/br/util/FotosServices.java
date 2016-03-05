package com.br.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;
import java.util.Date;
/**
 *
 * @author Fatinha de Sousa
 */
public class FotosServices {

    /*Recebe uma string no formato base 64 e converte em arquivo do disco*/
    public static String converteStringBase64EmArquivo(String imageDataString) {
        byte[] imageByteArray = decodeImage(imageDataString);

        Date date = new Date();
        long i = System.currentTimeMillis();
        String path = String.valueOf(i);
        String caminho = "D:\\Documents\\NetBeansProjects\\MsgServerApp\\src\\fotos\\"+path+".jpeg";
        // Escreve um array de bytes de imagem em sistema de arquivos
        try {
            FileOutputStream imageOutFile = new FileOutputStream(
                    caminho);
            imageOutFile.write(imageByteArray);
            imageOutFile.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FotosServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FotosServices.class.getName()).log(Level.SEVERE, null, ex);
        }
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

            imageDataString = FotosServices.encodeImage(imageData);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FotosServices.class.getName()).log(Level.SEVERE, null, ex);
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
