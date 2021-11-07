/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author alumne
 */
public class RESTConnection {
    private static final String url = "http://localhost:8080/Practica4_REST/rest";
    private static final String charset = "UTF-8";
    
    private static String getResponseBody(){
        String contentType = connection.getHeaderField("Content-Type");
        String charset = null;

        for (String param : contentType.replace(" ", "").split(";")) {
            if (param.startsWith("charset=")) {
                charset = param.split("=", 2)[1];
                break;
            }
        }

        if (charset != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))) {
                for (String line; (line = reader.readLine()) != null;) {
                // ... System.out.println(line)?
                }
            }
        } else {
        // It's likely binary content, use InputStream/OutputStream.
        }
    }
    
    //https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
        
    
    public static Boolean checkPassword(String username, String password) throws MalformedURLException, IOException {
        URLConnection connection = new URL(url+"/login").openConnection();                
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);       
        
        String query = String.format("username=%s&password=%s",
                URLEncoder.encode(username, charset),
                URLEncoder.encode(password, charset)
        );
        
        try (OutputStream output = connection.getOutputStream()) {
            System.out.println(query.getBytes(charset));
            output.write(query.getBytes(charset));
            return true;
        }     
        catch (Exception ex) {
                return false;
        } 
    }
}
    
    