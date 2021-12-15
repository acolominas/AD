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
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.Part;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alumne
 */
public class RESTConnection {
    private static final String url = "https://pxfnmpq2xe.execute-api.eu-west-1.amazonaws.com/v1";                                 
    //private static final String url = System.getenv("API_Endpoint");
    private static final String charset = "UTF-8";
    private static String token = null;


    private static String doPOSTConnection(String path, String query) {
        try {

            System.out.println(url);
            HttpsURLConnection postConnection = (HttpsURLConnection) new URL(url + path).openConnection();
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
    
    private static String doDELETEConnection(String path, String query) {
        try {

            System.out.println(url);
            HttpsURLConnection postConnection = (HttpsURLConnection) new URL(url + path).openConnection();
            postConnection.setRequestMethod("DELETE");
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
            HttpsURLConnection getConnection = (HttpsURLConnection) new URL(url + path).openConnection();
            getConnection.setRequestMethod("GET");
            getConnection.setRequestProperty("Authorization", token);

            int responseCode = getConnection.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

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
            String response = doPOSTConnection("/users/token",user.toJSON());
            JSONObject json = new JSONObject(response);
            String new_token = json.get("id_token").toString();
            System.out.println(response);
            if (json.get("status").equals("success")) {
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
            String response = doPOSTConnection("/users",user.toJSON());
            JSONObject json = new JSONObject(response);
            System.out.println(response);
            return json.get("status").equals("success");
        } catch (JSONException e){
            return false;

        }
    }

    public static JSONArray listImages() {
        String response = doGETConnection("/images");
        JSONArray json = null;
        try {
            json = new JSONArray(response);
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
        String response = doGETConnection("/images/id/"+String.valueOf(id));
        JSONObject json = null;
        try {
            json = new JSONObject(response);
        } catch (JSONException e){

        }
        return json;
    }

    public static JSONObject searchByTitle(String title) {
        String response = doGETConnection("/images/title"+title);
        JSONObject json = null;
        try {
            json = new JSONObject(response);
        } catch (JSONException e){

        }
        return json;
    }

    public static JSONObject searchByAuthor(String author) {
        String response = doGETConnection("/images/author"+author);
        JSONObject json = null;
        try {
            json = new JSONObject(response);
        } catch (JSONException e){

        }
        return json;
    }

    public static JSONObject searchByCreaDate(String creaDate) {
        String response = doGETConnection("/images/creationdate"+creaDate);
        JSONObject json = null;
        try {
            json = new JSONObject(response);
        } catch (JSONException e){

        }
        return json;
    }

    public static JSONObject searchByKeywords(String keywords) {
        String response = doGETConnection("/images/keywords"+keywords);
        JSONObject json = null;
        try {
            json = new JSONObject(response);
        } catch (JSONException e){

        }
        return json;
    }

    public static Boolean registerImage(Image image) {
        try {
            String response = doPOSTConnection("/images",image.toJSON());
            JSONObject json = new JSONObject(response);
            System.out.println(response);
            return json.get("status").equals("success");
        } catch (JSONException e){
            return false;

        }
    }

    public static Boolean modifyImage(Image image) {
        try {
            String response = doPOSTConnection("/images",image.toJSON());
            JSONObject json = new JSONObject(response);
            System.out.println(response);
            return json.get("status").equals("success");
        } catch (JSONException e){
            return false;

        }
    }

    public static Boolean deleteImage(String image_id) {
        JSONObject json = null;
        QueryJSON query = new QueryJSON();
        try {
            query.id = image_id;
            String response = doDELETEConnection("/images",query.toJSON());
            json = new JSONObject(response);
            return json.get("status").equals("success");
        } catch (JSONException e){
            return null;

        }
    }
}
