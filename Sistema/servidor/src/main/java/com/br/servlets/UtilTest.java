package com.br.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;


/**
 *
 * @author Diego
 */
public class UtilTest {

    private static JSONObject jSONObject;
    private static JSONParser parser;

    public UtilTest() {
    }
    
    public static JSONObject getJSON(String json) {
       
        jSONObject = new JSONObject(json);
        return jSONObject;
    }


    public static String streamToString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }

}
