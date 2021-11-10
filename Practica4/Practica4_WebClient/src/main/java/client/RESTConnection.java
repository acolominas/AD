/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
        

    
    //https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
    //https://dzone.com/articles/how-to-implement-get-and-post-request-through-simp    
    private static String doPOSTConnection(String path, String query) throws IOException {
        try {            
           
            HttpURLConnection postConnection = (HttpURLConnection) new URL(url + path).openConnection();           
            postConnection.setRequestMethod("POST");                             
            postConnection.setDoOutput(true); // Triggers POST.
            postConnection.setRequestProperty("Accept-Charset", charset);
            postConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);        
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
        }catch (MalformedURLException e) {
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
    
    
    public static Boolean checkPassword(String username, String password) {            
        try {
            String queryString = String.format("username=%s&password=%s" 
                ,URLEncoder.encode(username, charset) 
                ,URLEncoder.encode(password, charset)            
            );
        
            String response = doPOSTConnection("/login",queryString);                     
            JSONObject json = new JSONObject(response);
            System.out.println(response); 
            return json.get("status").equals("OK");
        } catch (IOException | JSONException e){
            return false;
        
        }                  
    }
    
    public static Boolean createUser(String username, String password) {                    
        try {                                
            String queryString = String.format("username=%s&password=%s" 
                ,URLEncoder.encode(username, charset) 
                ,URLEncoder.encode(password, charset)            
            );

            String response = doPOSTConnection("/createUser",queryString);                          
            JSONObject json = new JSONObject(response);
            System.out.println(response); 
            return json.get("status").equals("OK");
        } catch (IOException | JSONException e){
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
    
    public static Boolean registerImage(String title,String description,String keywords,String author,String creator,String capture_date,String filename) {
        try {                                
            String queryString = String.format("title=%s&description=%s&keywords=%s&author=%s&creator=%s&capture=%s&filename=%s" 
                ,URLEncoder.encode(title, charset) 
                ,URLEncoder.encode(description, charset)
                ,URLEncoder.encode(keywords, charset)  
                ,URLEncoder.encode(author, charset) 
                ,URLEncoder.encode(creator, charset) 
                ,URLEncoder.encode(capture_date, charset)
                ,URLEncoder.encode(filename, charset)
            );   
            
            String response = doPOSTConnection("/register",queryString);                          
            JSONObject json = new JSONObject(response);
            System.out.println(response); 
            return json.get("status").equals("OK");
        } catch (IOException | JSONException e){
            return false;
        
        }    
    }
    
    public static Boolean modifyImage(String id,String title,String description,String keywords,String author,String creator,String storage_date,String capture_date,String filename) {
        try {                                
            String queryString = String.format("id=%s,title=%s&description=%s&keywords=%s&author=%s&creator=%s&storage=%s&capture=%s&filename=%s" 
                ,URLEncoder.encode(title, charset) 
                ,URLEncoder.encode(description, charset)
                ,URLEncoder.encode(keywords, charset)  
                ,URLEncoder.encode(author, charset) 
                ,URLEncoder.encode(creator, charset) 
                ,URLEncoder.encode(storage_date, charset)  
                ,URLEncoder.encode(capture_date, charset)
                ,URLEncoder.encode(filename, charset)
            );   
            
            String response = doPOSTConnection("/modify",queryString);                          
            JSONObject json = new JSONObject(response);
            System.out.println(response); 
            return json.get("status").equals("OK");
        } catch (IOException | JSONException e){
            return false;
        
        }    
    }
    
    public static JSONObject deleteImage(String id) { 
        JSONObject json = null;  
        try {
            String queryString = String.format("id=%s" 
                ,URLEncoder.encode(id, charset)                             
            );
            String response = doPOSTConnection("/delete",queryString);                                
            json = new JSONObject(response); 
            return json;
        } catch (IOException | JSONException e){
            return null;
        
        }     
    }
     
    
     public static JSONObject downloadImage(String image_id) {
        JSONObject json = null;  
        try {
            String queryString = String.format("image_id=%s" 
                ,URLEncoder.encode(image_id, charset)                             
            );
            String response = doPOSTConnection("/downloadImage",queryString);                                
            json = new JSONObject(response); 
            return json;
        } catch (IOException | JSONException e){
            return null;
        
        }
        
    }
    
    public static Boolean uploadImage(Part part,String filename) {
        JSONObject json = null;  
        try {
            
            InputStream inputStream = part.getInputStream();
	    byte[] fileContent = FileUtil.getFileContent(inputStream);
            String base64String = Base64.getEncoder().encodeToString(fileContent);
            
            String queryString = String.format("filename=%s&image=%s" 
                ,URLEncoder.encode(filename, charset)
                ,URLEncoder.encode(base64String, charset)                             
            );
            String response = doPOSTConnection("/uploadImage",queryString);                                        
            json = new JSONObject(response); 
            return json.get("status").equals("OK");
        } catch (IOException | JSONException e){
            return false;
        
        }
        
    }
    
    public static Boolean removeImage(String filename) {
        JSONObject json = null;  
        try {                     
            String queryString = String.format("filename=%s" 
                ,URLEncoder.encode(filename, charset)                                           
            );
            String response = doPOSTConnection("/removeImage",queryString);                                        
            json = new JSONObject(response);  
            return json.get("status").equals("OK");
        } catch (IOException | JSONException e){
            return false;
        
        }
        
    }
}
    
    