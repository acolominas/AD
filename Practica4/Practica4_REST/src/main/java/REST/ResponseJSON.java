/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import App.Image;
import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author alumne
 */
public class ResponseJSON {
    public String status;
    public String message; 
    public List<Image> body;
    
    public ResponseJSON(String status, String message){
        this.status = status;
        this.message = message;
    }
    
    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    public void setBody(List<Image> body){
        this.body = body;
    }
}
