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
    private String id;
    private String title;
    private String description;
    private String keywords;
    private String author;
    private String creator;
    private String capture_date;
    private String storage_date;
    private String filename;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCapture_date() {
        return capture_date;
    }

    public void setCapture_date(String capture_date) {
        this.capture_date = capture_date;
    }

    public String getStorage_date() {
        return storage_date;
    }

    public void setStorage_date(String storage_date) {
        this.storage_date = storage_date;
    }

    public String getFilename() {
        return filename;
    }

    /**
     *
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    } 
    
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


