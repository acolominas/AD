/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.google.gson.Gson;

/**
 *
 * @author alumne
 */
public class User {
    public String username;
    public String password; 
    
     public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }   
}
