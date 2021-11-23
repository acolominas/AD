/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alumne
 */
public class RESTConnection {
    private static final String url = "http://localhost:8080/Practica4_REST/rest";
    private static final String charset = "UTF-8";
    private static String token = null;
        
  
    private static String doPOSTConnection(String path, String query) {
        try {            
           
            HttpURLConnection postConnection = (HttpURLConnection) new URL(url + path).openConnection();           
            postConnection.setRequestMethod("POST");                             
            postConnection.setDoOutput(true); // Triggers POST.
            postConnection.setRequestProperty("Accept-Charset", charset);
            postConnection.setRequestProperty("Content-Type", "application/json;charset=" + charset);
            postConnection.setRequestProperty("Authorization", token);
            OutputStream output = postConnection.getOutputStream();          
            output.write(query.getBytes(charset));
            output.close();            
            InputStream  inputStream = postConnection.getInputStream();            
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, charset));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = responseReader.readLine()) != null) {
                response.append(inputLine);
            }            
            responseReader.close();   
            return response.toString();
        }catch (IOException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                System.out.println("oks");
                return null;
                
        }
        
    }
    
    private static String doGETConnection(String path){
        try {
            HttpURLConnection getConnection = (HttpURLConnection) new URL(url + path).openConnection();           
            getConnection.setRequestMethod("GET"); 
            getConnection.setRequestProperty("Authorization", token);
                    
            int responseCode = getConnection.getResponseCode();  
            
            if (responseCode == HttpURLConnection.HTTP_OK) {               

                InputStream  inputStream = getConnection.getInputStream();            
                BufferedReader responseReader = new BufferedReader(
                        new InputStreamReader(inputStream, charset)
                );
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = responseReader.readLine()) != null) {
                    response.append(inputLine);
                }
                responseReader.close();   
                return response.toString();
            }
            else {
               return null;                     
            }
         
        }catch (MalformedURLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                System.out.println("oks");
                return null;
                
        } catch (IOException ex) {
            Logger.getLogger(RESTConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }             
    } 
    
    public static void setToken(String new_token) {
        token = new_token;
    }
    
    //return token
    public static String login(User user) {            
        try {          
            String response = doPOSTConnection("/login",user.toJSON());                     
            JSONObject json = new JSONObject(response);
            String new_token = json.get("body").toString();
            System.out.println(response);
            if (json.get("status").equals("OK")) {
                return new_token;
            }
            else return null;
        } catch (JSONException e){
            System.out.println("Peta"); 
            return null;
        
        }                  
    }
    
    public static Boolean createUser(User user) {                    
        try {                          
            String response = doPOSTConnection("/createUser",user.toJSON());                          
            JSONObject json = new JSONObject(response);
            System.out.println(response); 
            return json.get("status").equals("OK");
        } catch (JSONException e){
            return false;
        
        }                    
    }
    
    public static JSONObject listImages() {   
        String response = doGETConnection("/list"); 
        JSONObject json = null;
        try {
            json = new JSONObject(response); 
        } catch (JSONException e){
            
        }
        return json;
    }
    
       public static JSONObject searchImages(Image image) {                                                          
        String response = doPOSTConnection("/searchImages/",image.toJSON());
        JSONObject json = null;
        try {
            json = new JSONObject(response); 
        } catch (JSONException e){
        
        }
        return json;
    }
    
    public static JSONObject searchById(int id) {                                                          
        String response = doGETConnection("/searchID/"+String.valueOf(id)); 
        JSONObject json = null;
        try {
            json = new JSONObject(response); 
        } catch (JSONException e){
        
        }
        return json;
    }
    
    public static JSONObject searchByTitle(String title) {                                                          
        String response = doGETConnection("/searchTitle/"+title); 
        JSONObject json = null;        
        try {
            json = new JSONObject(response); 
        } catch (JSONException e){
        
        }       
        return json;
    }
    
    public static JSONObject searchByAuthor(String author) {         
        String response = doGETConnection("/searchAuthor/"+author); 
        JSONObject json = null;
        try {
            json = new JSONObject(response); 
        } catch (JSONException e){
        
        }       
        return json;
    } 
    
    public static JSONObject searchByCreaDate(String creaDate) {       
        String response = doGETConnection("/searchCreationDate/"+creaDate); 
        JSONObject json = null;
        try {
            json = new JSONObject(response); 
        } catch (JSONException e){
        
        }
        return json;
    }
    
    public static JSONObject searchByKeywords(String keywords) {                                                              
        String response = doGETConnection("/searchKeywords/"+keywords); 
        JSONObject json = null;
        try {                    
            json = new JSONObject(response); 
        } catch (JSONException e){
        
        }
        return json;
    }
    
    public static Boolean registerImage(Image image) {
        try {                               
            String response = doPOSTConnection("/register",image.toJSON());                          
            JSONObject json = new JSONObject(response);
            System.out.println(response); 
            return json.get("status").equals("OK");
        } catch (JSONException e){
            return false;
        
        }    
    }
    
    public static Boolean modifyImage(Image image) {
        try {                             
            String response = doPOSTConnection("/modify",image.toJSON());                          
            JSONObject json = new JSONObject(response);
            System.out.println(response); 
            return json.get("status").equals("OK");
        } catch (JSONException e){
            return false;
        
        }    
    }
    
    public static JSONObject deleteImage(String image_id) { 
        JSONObject json = null;  
        QueryJSON query = new QueryJSON();
        try {
            query.image_id = image_id;            
            String response = doPOSTConnection("/delete",query.toJSON());                                
            json = new JSONObject(response); 
            return json;
        } catch (JSONException e){
            return null;
        
        }     
    }
     
    
    public static JSONObject downloadImage(String image_id) {
        JSONObject json = null;  
        QueryJSON query = new QueryJSON();
        try {
            query.image_id = image_id; 
            String response = doPOSTConnection("/downloadImage",query.toJSON());                                
            json = new JSONObject(response); 
            return json;
        } catch (JSONException e){
            return null;
        
        }
        
    }
    
    public static Boolean uploadImage(Part part,String filename) {
        JSONObject json = null;
        QueryJSON query = new QueryJSON();
        try {
            
            InputStream inputStream = part.getInputStream();
	    byte[] fileContent = FileUtil.getFileContent(inputStream);
            String base64String = Base64.getEncoder().encodeToString(fileContent);
            query.filename = filename;
            query.image = base64String;            
            
            String response = doPOSTConnection("/uploadImage",query.toJSON());                                        
            json = new JSONObject(response); 
            return json.get("status").equals("OK");
        } catch (IOException | JSONException e){
            return false;
        
        }
        
    }
    
    public static Boolean removeImage(String id) {
        JSONObject json = null;  
        QueryJSON query = new QueryJSON();
        try {                     
            
            query.image_id = id;
            
            String response = doPOSTConnection("/removeImage",query.toJSON());                                        
            json = new JSONObject(response);  
            return json.get("status").equals("OK");
        } catch (JSONException e){
            return false;
        
        }
        
    }
}
    
    