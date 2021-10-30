/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

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
    
    public Image(String id,String title,String description,String keywords,String author,String creator,String capture_date,String storage_date,String filename) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.author = author;
        this.creator = creator;
        this.capture_date = capture_date;
        this.storage_date = storage_date;
        this.filename = filename;
    }
    
    public Image() {
    }
}


