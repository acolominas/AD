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
public class Image {
    public String id;
    public String title;
    public String description;
    public String keywords;
    public String author;
    public String creator;
    public String capture_date;
    public String storage_date;
    public String filename;
    
     public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }   
}


