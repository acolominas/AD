/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import com.google.gson.Gson;

/**
 *
 * @author alumne
 */
public class ResponseJSON {
    public String status;
    public String message; 
    public String body;
    
    public ResponseJSON(String status, String message){
        this.status = status;
        this.message = message;
    }
    
    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
